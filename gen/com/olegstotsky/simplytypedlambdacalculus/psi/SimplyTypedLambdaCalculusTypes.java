// This is a generated file. Not intended for manual editing.
package com.olegstotsky.simplytypedlambdacalculus.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.olegstotsky.simplytypedlambdacalculus.psi.impl.*;

public interface SimplyTypedLambdaCalculusTypes {

  IElementType ABSTRACTION_EXPR = new SimplyTypedLambdaCalculusElementType("ABSTRACTION_EXPR");
  IElementType APPLICATION_EXPR = new SimplyTypedLambdaCalculusElementType("APPLICATION_EXPR");
  IElementType BOOL_EXPR = new SimplyTypedLambdaCalculusElementType("BOOL_EXPR");
  IElementType COND_EXPR = new SimplyTypedLambdaCalculusElementType("COND_EXPR");
  IElementType LAMBDA_EXPR = new SimplyTypedLambdaCalculusElementType("LAMBDA_EXPR");
  IElementType NUM_EXPR = new SimplyTypedLambdaCalculusElementType("NUM_EXPR");
  IElementType PAR_EXPR = new SimplyTypedLambdaCalculusElementType("PAR_EXPR");
  IElementType STATEMENT = new SimplyTypedLambdaCalculusElementType("STATEMENT");
  IElementType TYPING_EXPR = new SimplyTypedLambdaCalculusElementType("TYPING_EXPR");
  IElementType TYPING_EXPR_OTHER = new SimplyTypedLambdaCalculusElementType("TYPING_EXPR_OTHER");
  IElementType VARIABLE_EXPR = new SimplyTypedLambdaCalculusElementType("VARIABLE_EXPR");

  IElementType ARROW = new SimplyTypedLambdaCalculusTokenType("->");
  IElementType BACKSLASH = new SimplyTypedLambdaCalculusTokenType("\\");
  IElementType BOOL = new SimplyTypedLambdaCalculusTokenType("Bool");
  IElementType COLON = new SimplyTypedLambdaCalculusTokenType(":");
  IElementType DIV = new SimplyTypedLambdaCalculusTokenType("/");
  IElementType DOT = new SimplyTypedLambdaCalculusTokenType(".");
  IElementType ELSE = new SimplyTypedLambdaCalculusTokenType("else");
  IElementType EQ = new SimplyTypedLambdaCalculusTokenType("=");
  IElementType FALSE = new SimplyTypedLambdaCalculusTokenType("false");
  IElementType GT = new SimplyTypedLambdaCalculusTokenType(">");
  IElementType GTE = new SimplyTypedLambdaCalculusTokenType(">=");
  IElementType ID = new SimplyTypedLambdaCalculusTokenType("id");
  IElementType IF = new SimplyTypedLambdaCalculusTokenType("if");
  IElementType INT = new SimplyTypedLambdaCalculusTokenType("Int");
  IElementType LEFT_PAREN = new SimplyTypedLambdaCalculusTokenType("(");
  IElementType LT = new SimplyTypedLambdaCalculusTokenType("<");
  IElementType LTE = new SimplyTypedLambdaCalculusTokenType("<=");
  IElementType MINUS = new SimplyTypedLambdaCalculusTokenType("-");
  IElementType MUL = new SimplyTypedLambdaCalculusTokenType("*");
  IElementType NUMBER = new SimplyTypedLambdaCalculusTokenType("number");
  IElementType PLUS = new SimplyTypedLambdaCalculusTokenType("+");
  IElementType REM = new SimplyTypedLambdaCalculusTokenType("%");
  IElementType RIGHT_PAREN = new SimplyTypedLambdaCalculusTokenType(")");
  IElementType SEMICOLON = new SimplyTypedLambdaCalculusTokenType(";");
  IElementType THEN = new SimplyTypedLambdaCalculusTokenType("then");
  IElementType TRUE = new SimplyTypedLambdaCalculusTokenType("true");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ABSTRACTION_EXPR) {
        return new SimplyTypedLambdaCalculusAbstractionExprImpl(node);
      }
      else if (type == APPLICATION_EXPR) {
        return new SimplyTypedLambdaCalculusApplicationExprImpl(node);
      }
      else if (type == BOOL_EXPR) {
        return new SimplyTypedLambdaCalculusBoolExprImpl(node);
      }
      else if (type == COND_EXPR) {
        return new SimplyTypedLambdaCalculusCondExprImpl(node);
      }
      else if (type == LAMBDA_EXPR) {
        return new SimplyTypedLambdaCalculusLambdaExprImpl(node);
      }
      else if (type == NUM_EXPR) {
        return new SimplyTypedLambdaCalculusNumExprImpl(node);
      }
      else if (type == PAR_EXPR) {
        return new SimplyTypedLambdaCalculusParExprImpl(node);
      }
      else if (type == STATEMENT) {
        return new SimplyTypedLambdaCalculusStatementImpl(node);
      }
      else if (type == TYPING_EXPR) {
        return new SimplyTypedLambdaCalculusTypingExprImpl(node);
      }
      else if (type == TYPING_EXPR_OTHER) {
        return new SimplyTypedLambdaCalculusTypingExprOtherImpl(node);
      }
      else if (type == VARIABLE_EXPR) {
        return new SimplyTypedLambdaCalculusVariableExprImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
