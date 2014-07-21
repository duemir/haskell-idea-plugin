package org.jetbrains.cabal.highlight

import com.intellij.lang.annotation.*
import com.intellij.psi.PsiElement
import org.jetbrains.cabal.psi.*
import org.jetbrains.haskell.highlight.HaskellHighlighter
import org.jetbrains.cabal.parser.Checkable

public class CabalAnnotator() : Annotator {

    public override fun annotate(element: PsiElement, holder: AnnotationHolder): Unit {
        fun keyword(e : PsiElement) {
            holder.createInfoAnnotation(e, null)?.setTextAttributes(HaskellHighlighter.KEYWORD_VALUE)
        }

        if ((element is Checkable) && (!element.isValidPSIElem() || (element.isValidValue() != null))) {
            val annotation = holder.createErrorAnnotation(element.getNode()!!, "something is wrong")
        }

        if ((element is PropertyKey) || (element is SectionType)) {
            keyword(element)
        }

        if (element is Executable) {
           keyword(element.getFirstChild()!!)
        }
        if (element is TestSuite) {
            keyword(element.getFirstChild()!!)
        }

    }

}
