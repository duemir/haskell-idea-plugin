package org.jetbrains.haskell.parser

import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import com.intellij.lang.PsiBuilder
import com.intellij.lang.ASTNode

import java.util.ArrayList
import org.jaxen.expr.Expr
import java.util.LinkedList
import java.util.HashMap
import org.jetbrains.haskell.parser.token.*
import org.jetbrains.haskell.parser.lexer.*
import org.jetbrains.haskell.parser.rules.*
import org.jetbrains.haskell.psi.*


public fun inParentheses(rule : Rule) : Rule {
    return LEFT_PAREN + rule + RIGHT_PAREN
}


public class HaskellParser(root: IElementType, builder: PsiBuilder) : BaseParser(root, builder) {

    class object {
        private val FQ_NAME = RuleBasedElementType("FQ name", FqName) {
            notEmptyList(TYPE_OR_CONS, DOT)
        }

        val aPrimitiveType : Rule = rule(TYPE) {
            val noBangType = ID or
                             TYPE_OR_CONS or
                             (LEFT_BRACKET + aType + RIGHT_BRACKET) or
                             inParentheses(aType) or
                             (LEFT_PAREN + RIGHT_PAREN)
            maybe(EXCLAMATION) + noBangType
        }

        val aApplicationType : Rule = rule(APPLICATION_TYPE) {
            (aPrimitiveType + aApplicationType) or aPrimitiveType
        }

        private val aArrowType : Rule = rule(ARROW_TYPE) {
            aApplicationType + RIGHT_ARROW + aType
        }

        val aType : Rule = lazy {
            aArrowType or aApplicationType
        }


        val anExpression: Rule = lazy {
            aList(anAtomExpression, null)
        }

        val listLiteral = lazy{
            (LEFT_BRACKET + aList(anExpression, COMMA) + RIGHT_BRACKET)
        }

        val anAtomExpression: Rule = lazy {
            UNDERSCORE or
            COLON or
            STRING or
            NUMBER or
            ID or
            OPERATOR or
            DOLLAR or
            rule(CONSTRUCTOR, { TYPE_OR_CONS }) or
            inParentheses(anExpression) or
            listLiteral
        }

        val expressionList = aList(anAtomExpression, null)


        val aGuard = lazy {
            VERTICAL_BAR + anExpression + EQUALS + anExpression
        }

        val aFunctionBody = rule(FUNCTION_BODY) {
            val rhs = (EQUALS + anExpression) or notEmptyList(aGuard)
            ID + expressionList + rhs
        }

        val INSTANCE_BODY = lazy {
            aFunctionBody
        }

        val INSTANCE_DECLARATION = RuleBasedElementType("Instance declaration", InstanceDeclaration) {
            val body = VIRTUAL_LEFT_PAREN + INSTANCE_BODY + VIRTUAL_RIGHT_PAREN

            INSTANCE_KW + TYPE_OR_CONS + aList(aType, null) + WHERE_KW + body
        }

        private val aModuleName = rule(MODULE_NAME) {
            notEmptyList(TYPE_OR_CONS, DOT)
        }

        private val aModuleExports = rule(MODULE_EXPORTS) {
            LEFT_PAREN + aList(anExport, COMMA) + maybe(COMMA) + RIGHT_PAREN
        }


        val anExport = lazy {
            val symbolExport = rule(SYMBOL_EXPORT) {
                ID or TYPE_OR_CONS or inParentheses(OPERATOR)
            }

            val qcnameExt = maybe(TYPE_KW) + symbolExport

            val qcnames = notEmptyList(qcnameExt, COMMA)

            val exportSubspec = inParentheses(maybe((DOT_DOT) or qcnames))

            (qcnameExt + maybe(exportSubspec)) or
            (MODULE_KW + aModuleName)
        }

        val aImportAsPart = rule(IMPORT_AS_PART) {
            AS_KW + FQ_NAME
        }

        val VALUE_DECLARATION = RuleBasedElementType("Function declaration", ValueDeclaration) {
            val name = rule(NAME, { ID })
            notEmptyList(name, COMMA) + DOUBLE_COLON + aType
        }

        val anImport = rule(IMPORT) {
            IMPORT_KW + maybe(QUALIFIED_KW) + aModuleName + maybe(aImportAsPart) +
            maybe(HIDING_KW) + maybe(aModuleExports)
        }

        val typedBinding = lazy {
            rule(NAME, { ID }) + DOUBLE_COLON + aType
        }

        val extendedConstructor = lazy {
            LEFT_BRACE + aList(typedBinding, COMMA) + RIGHT_BRACE
        }

        val aConstructor = rule(CONSTRUCTOR_DECLARATION) {
            rule(NAME, { TYPE_OR_CONS }) +
            (extendedConstructor or aList(aType, null))
        }

        val aDataDeclaration = rule(DATA_DECLARATION) {
            val derivingSection = DERIVING_KW + ((LEFT_PAREN + notEmptyList(TYPE_OR_CONS, COMMA) + RIGHT_PAREN) or TYPE_OR_CONS)
            val data_or_newtype = DATA_KW or NEWTYPE_KW
            val dataName = rule(NAME, { TYPE_OR_CONS })
            data_or_newtype + dataName + aList(ID) + EQUALS + aList(aConstructor, VERTICAL_BAR) + maybe(derivingSection)
        }

        val SOME_ID = RuleBasedElementType("Some id", SomeId) {
            ID or TYPE_OR_CONS or OPERATOR
        }

        val aModuleHeader = rule(MODULE_HEADER) {
            (aList(VIRTUAL_SEMICOLON, null) + MODULE_KW + FQ_NAME + maybe(aModuleExports) + WHERE_KW)
        }



    }

    public fun parse(): ASTNode {
        return parseInternal(root)
    }


    fun parseInternal(root: IElementType): ASTNode {
        val rootMarker = mark()

        parseModule();

        rootMarker.done(root)
        return builder.getTreeBuilt()!!
    }


    fun parseModule() = start(MODULE) {
        val result = aModuleHeader.parse(builder)

        if (result) {
            val rule = VIRTUAL_SEMICOLON or
                       anImport or
                       aDataDeclaration or
                       INSTANCE_DECLARATION or
            VALUE_DECLARATION or
                       aFunctionBody

            while (!builder.eof()) {

                if (!rule.parse(builder)) {
                    while (builder.getTokenType() != VIRTUAL_SEMICOLON &&
                           builder.getTokenType() != VIRTUAL_RIGHT_PAREN &&
                           !builder.eof()) {

                        SOME_ID.parse(builder) || start(HASKELL_TOKEN) {
                            builder.advanceLexer()
                            true
                        }
                    }
                    builder.advanceLexer()
                }
            }
        }
        while (!builder.eof()) {
            SOME_ID.parse(builder) || start(HASKELL_TOKEN) {
                builder.advanceLexer()
                true
            }
        }
        true
    }

}