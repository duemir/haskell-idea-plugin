package org.jetbrains.haskell.parser.token

import org.jetbrains.haskell.parser.HaskellCompositeElementType
import org.jetbrains.haskell.psi.Module
import org.jetbrains.haskell.psi.Import
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.TokenType
import org.jetbrains.haskell.psi.*

/**
 * Created by atsky on 3/14/14.
 */
val ARROW_TYPE              = HaskellCompositeElementType("Arrow type", ArrowType)
val APPLICATION_TYPE        = HaskellCompositeElementType("Applycation type")
val CLASS_DECLARATION       = HaskellCompositeElementType("Class declaration")
val CONSTRUCTOR             = HaskellCompositeElementType("Cunstructor", Constructor)
val CONSTRUCTOR_DECLARATION = HaskellCompositeElementType("Cunstructor declaration", ConstructorDeclaration)
val DATA_DECLARATION        = HaskellCompositeElementType("Data declaration", DataDeclaration)
val IMPORT                  = HaskellCompositeElementType("Import", Import)
val IMPORT_AS_PART          = HaskellCompositeElementType("Import as part")
val SYMBOL_EXPORT           = HaskellCompositeElementType("Symbol export", SymbolExport)
val INSTANCE_DECLARATION    = HaskellCompositeElementType("Instance declaration")
val FUNCTION_BODY           = HaskellCompositeElementType("Function body")

val MODULE_NAME             = HaskellCompositeElementType("Module name", ModuleName)
val MODULE_EXPORTS          = HaskellCompositeElementType("Module exports", ModuleExports)
val MODULE                  = HaskellCompositeElementType("Module", Module)
val MODULE_HEADER           = HaskellCompositeElementType("Module header")
val NAME                    = HaskellCompositeElementType("Name", Name)
val TYPE                    = HaskellCompositeElementType("Type")
val HASKELL_TOKEN           = HaskellCompositeElementType("TOKEN")
