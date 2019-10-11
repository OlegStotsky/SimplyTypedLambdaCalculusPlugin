// This is a generated file. Not intended for manual editing.
package com.olegstotsky.simplytypedlambdacalculus.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.olegstotsky.simplytypedlambdacalculus.psi.impl.*;

public interface SimpleTypes {

  IElementType IDENTIFIER_LIST = new SimplyTypedLambdaCalculusElementType("IDENTIFIER_LIST");

  IElementType ARROW = new SimplyTypedLambdaCalculusTokenType("->");
  IElementType BACKSLASH = new SimplyTypedLambdaCalculusTokenType("\\");
  IElementType ID = new SimplyTypedLambdaCalculusTokenType("id");
  IElementType LEFT_PAREN = new SimplyTypedLambdaCalculusTokenType("(");
  IElementType RIGHT_PAREN = new SimplyTypedLambdaCalculusTokenType(")");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == IDENTIFIER_LIST) {
        return new SimlyTypedLambdaCalculusIdentifierListImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
