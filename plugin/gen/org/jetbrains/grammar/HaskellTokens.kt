package org.jetbrains.grammar

import com.intellij.psi.tree.IElementType
import org.jetbrains.haskell.parser.HaskellCompositeElementType
import org.jetbrains.haskell.psi.*


public val STRING_LITERAL : IElementType = HaskellCompositeElementType("StringLiteral", ::StringLiteral)
public val APPLICATION_TYPE : IElementType = HaskellCompositeElementType("ApplicationType", ::ApplicationType)
public val LET_EXPRESSION : IElementType = HaskellCompositeElementType("LetExpression", ::LetExpression)
public val IMPORT : IElementType = HaskellCompositeElementType("Import", ::Import)
public val Q_NAME_EXPRESSION : IElementType = HaskellCompositeElementType("QNameExpression", ::QNameExpression)
public val SIGNATURE_DECLARATION : IElementType = HaskellCompositeElementType("SignatureDeclaration", ::SignatureDeclaration)
public val Q_VAR : IElementType = HaskellCompositeElementType("QVar", ::QVar)
public val DATA_DECLARATION : IElementType = HaskellCompositeElementType("DataDeclaration", ::DataDeclaration)
public val CONSTRUCTOR_DECLARATION : IElementType = HaskellCompositeElementType("ConstructorDeclaration", ::ConstructorDeclaration)
public val MODULE_NAME : IElementType = HaskellCompositeElementType("ModuleName", ::ModuleName)
public val MODULE : IElementType = HaskellCompositeElementType("Module", ::Module)
public val Q_CON : IElementType = HaskellCompositeElementType("QCon", ::QCon)
public val TYPE_VARIABLE : IElementType = HaskellCompositeElementType("TypeVariable", ::TypeVariable)
public val MODULE_EXPORTS : IElementType = HaskellCompositeElementType("ModuleExports", ::ModuleExports)
public val APPLICATION : IElementType = HaskellCompositeElementType("Application", ::Application)
public val RIGHT_HAND_SIDE : IElementType = HaskellCompositeElementType("RightHandSide", ::RightHandSide)
public val ARROW_TYPE : IElementType = HaskellCompositeElementType("ArrowType", ::ArrowType)
public val CASE_EXPRESSION : IElementType = HaskellCompositeElementType("CaseExpression", ::CaseExpression)
public val CLASS_DECLARATION : IElementType = HaskellCompositeElementType("ClassDeclaration", ::ClassDeclaration)
public val VALUE_DEFINITION : IElementType = HaskellCompositeElementType("ValueDefinition", ::ValueDefinition)
public val DO_EXPRESSION : IElementType = HaskellCompositeElementType("DoExpression", ::DoExpression)
public val INSTANCE_DECLARATION : IElementType = HaskellCompositeElementType("InstanceDeclaration", ::InstanceDeclaration)
public val IMPORT_AS_PART : IElementType = HaskellCompositeElementType("ImportAsPart", ::ImportAsPart)