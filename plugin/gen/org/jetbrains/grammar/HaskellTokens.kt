package org.jetbrains.grammar

import com.intellij.psi.tree.IElementType
import org.jetbrains.haskell.parser.HaskellCompositeElementType
import org.jetbrains.haskell.psi.*


object HaskellTokens {
  val MODULE = HaskellCompositeElementType("module", ::Module)
  val MAYBEDOCHEADER = HaskellCompositeElementType("maybedocheader")
  val MISSING_MODULE_KEYWORD = HaskellCompositeElementType("missing_module_keyword")
  val MAYBEMODWARNING = HaskellCompositeElementType("maybemodwarning")
  val BODY = HaskellCompositeElementType("body")
  val BODY2 = HaskellCompositeElementType("body2")
  val TOP = HaskellCompositeElementType("top")
  val CVTOPDECLS = HaskellCompositeElementType("cvtopdecls")
  val HEADER = HaskellCompositeElementType("header")
  val HEADER_BODY = HaskellCompositeElementType("header_body")
  val HEADER_BODY2 = HaskellCompositeElementType("header_body2")
  val MAYBEEXPORTS = HaskellCompositeElementType("maybeexports")
  val EXPORTLIST = HaskellCompositeElementType("exportlist")
  val EXPORTLIST1 = HaskellCompositeElementType("exportlist1")
  val EXPDOCLIST = HaskellCompositeElementType("expdoclist")
  val EXP_DOC = HaskellCompositeElementType("exp_doc")
  val EXPORT = HaskellCompositeElementType("export")
  val EXPORT_SUBSPEC = HaskellCompositeElementType("export_subspec")
  val QCNAMES = HaskellCompositeElementType("qcnames")
  val QCNAME_EXT = HaskellCompositeElementType("qcname_ext")
  val QCNAME = HaskellCompositeElementType("qcname")
  val IMPORTDECLS = HaskellCompositeElementType("importdecls")
  val IMPORTDECL = HaskellCompositeElementType("importdecl")
  val MAYBE_SRC = HaskellCompositeElementType("maybe_src")
  val MAYBE_SAFE = HaskellCompositeElementType("maybe_safe")
  val MAYBE_PKG = HaskellCompositeElementType("maybe_pkg")
  val OPTQUALIFIED = HaskellCompositeElementType("optqualified")
  val MAYBEAS = HaskellCompositeElementType("maybeas")
  val MAYBEIMPSPEC = HaskellCompositeElementType("maybeimpspec")
  val IMPSPEC = HaskellCompositeElementType("impspec")
  val PREC = HaskellCompositeElementType("prec")
  val INFIX = HaskellCompositeElementType("infix")
  val OPS = HaskellCompositeElementType("ops")
  val TOPDECLS = HaskellCompositeElementType("topdecls")
  val TOPDECL = HaskellCompositeElementType("topdecl")
  val CL_DECL = HaskellCompositeElementType("cl_decl")
  val TY_DECL = HaskellCompositeElementType("ty_decl")
  val INST_DECL = HaskellCompositeElementType("inst_decl")
  val OVERLAP_PRAGMA = HaskellCompositeElementType("overlap_pragma")
  val WHERE_TYPE_FAMILY = HaskellCompositeElementType("where_type_family")
  val TY_FAM_INST_EQN_LIST = HaskellCompositeElementType("ty_fam_inst_eqn_list")
  val TY_FAM_INST_EQNS = HaskellCompositeElementType("ty_fam_inst_eqns")
  val TY_FAM_INST_EQN = HaskellCompositeElementType("ty_fam_inst_eqn")
  val AT_DECL_CLS = HaskellCompositeElementType("at_decl_cls")
  val OPT_FAMILY = HaskellCompositeElementType("opt_family")
  val AT_DECL_INST = HaskellCompositeElementType("at_decl_inst")
  val DATA_OR_NEWTYPE = HaskellCompositeElementType("data_or_newtype")
  val OPT_KIND_SIG = HaskellCompositeElementType("opt_kind_sig")
  val TYCL_HDR = HaskellCompositeElementType("tycl_hdr")
  val CAPI_CTYPE = HaskellCompositeElementType("capi_ctype")
  val STAND_ALONE_DERIVING = HaskellCompositeElementType("stand_alone_deriving")
  val ROLE_ANNOT = HaskellCompositeElementType("role_annot")
  val MAYBE_ROLES = HaskellCompositeElementType("maybe_roles")
  val ROLES = HaskellCompositeElementType("roles")
  val ROLE = HaskellCompositeElementType("role")
  val PATTERN_SYNONYM_DECL = HaskellCompositeElementType("pattern_synonym_decl")
  val WHERE_DECLS = HaskellCompositeElementType("where_decls")
  val VARS0 = HaskellCompositeElementType("vars0")
  val DECL_CLS = HaskellCompositeElementType("decl_cls")
  val DECLS_CLS = HaskellCompositeElementType("decls_cls")
  val DECLLIST_CLS = HaskellCompositeElementType("decllist_cls")
  val WHERE_CLS = HaskellCompositeElementType("where_cls")
  val DECL_INST = HaskellCompositeElementType("decl_inst")
  val DECLS_INST = HaskellCompositeElementType("decls_inst")
  val DECLLIST_INST = HaskellCompositeElementType("decllist_inst")
  val WHERE_INST = HaskellCompositeElementType("where_inst")
  val DECLS = HaskellCompositeElementType("decls")
  val DECLLIST = HaskellCompositeElementType("decllist")
  val STRINGS = HaskellCompositeElementType("strings")
  val STRINGLIST = HaskellCompositeElementType("stringlist")
  val CLOSE = HaskellCompositeElementType("close")
  val MODID = HaskellCompositeElementType("modid")
  val COMMAS = HaskellCompositeElementType("commas")
  val MODULEHEADER = HaskellCompositeElementType("moduleheader")
}
