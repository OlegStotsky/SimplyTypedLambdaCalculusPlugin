// This is a generated file. Not intended for manual editing.
package com.olegstotsky.simplytypedlambdacalculus.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SimplyTypedLambdaCalculusParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return Root(b, l + 1);
  }

  /* ********************************************************** */
  // '\' id ':' TypingExpr '.' LambdaExpr
  public static boolean AbstractionExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AbstractionExpr")) return false;
    if (!nextTokenIs(b, BACKSLASH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BACKSLASH, ID, COLON);
    r = r && TypingExpr(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && LambdaExpr(b, l + 1);
    exit_section_(b, m, ABSTRACTION_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // '(' LambdaExpr  LambdaExpr ')'
  public static boolean ApplicationExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ApplicationExpr")) return false;
    if (!nextTokenIs(b, LEFT_PAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_PAREN);
    r = r && LambdaExpr(b, l + 1);
    r = r && LambdaExpr(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, APPLICATION_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // (VariableExpr | NumExpr) ('+' | '-' | '*' | '==' | '/') LambdaExpr
  public static boolean BinOpExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BinOpExpr")) return false;
    if (!nextTokenIs(b, "<bin op expr>", ID, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BIN_OP_EXPR, "<bin op expr>");
    r = BinOpExpr_0(b, l + 1);
    r = r && BinOpExpr_1(b, l + 1);
    r = r && LambdaExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // VariableExpr | NumExpr
  private static boolean BinOpExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BinOpExpr_0")) return false;
    boolean r;
    r = VariableExpr(b, l + 1);
    if (!r) r = NumExpr(b, l + 1);
    return r;
  }

  // '+' | '-' | '*' | '==' | '/'
  private static boolean BinOpExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BinOpExpr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, MUL);
    if (!r) r = consumeToken(b, DEQ);
    if (!r) r = consumeToken(b, DIV);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // true | false
  public static boolean BoolExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BoolExpr")) return false;
    if (!nextTokenIs(b, "<bool expr>", FALSE, TRUE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BOOL_EXPR, "<bool expr>");
    r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // if LambdaExpr then LambdaExpr else LambdaExpr
  public static boolean CondExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CondExpr")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF);
    r = r && LambdaExpr(b, l + 1);
    r = r && consumeToken(b, THEN);
    r = r && LambdaExpr(b, l + 1);
    r = r && consumeToken(b, ELSE);
    r = r && LambdaExpr(b, l + 1);
    exit_section_(b, m, COND_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // VariableExpr '=' LambdaExpr
  public static boolean Declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Declaration")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = VariableExpr(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && LambdaExpr(b, l + 1);
    exit_section_(b, m, DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // ApplicationExpr
  //         | BinOpExpr
  //         | VariableExpr
  //         | AbstractionExpr
  //         | CondExpr
  //         | NumExpr
  //         | BoolExpr
  //         | ParExpr
  public static boolean LambdaExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LambdaExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LAMBDA_EXPR, "<lambda expr>");
    r = ApplicationExpr(b, l + 1);
    if (!r) r = BinOpExpr(b, l + 1);
    if (!r) r = VariableExpr(b, l + 1);
    if (!r) r = AbstractionExpr(b, l + 1);
    if (!r) r = CondExpr(b, l + 1);
    if (!r) r = NumExpr(b, l + 1);
    if (!r) r = BoolExpr(b, l + 1);
    if (!r) r = ParExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // number
  public static boolean NumExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumExpr")) return false;
    if (!nextTokenIs(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    exit_section_(b, m, NUM_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // '(' LambdaExpr ')'
  public static boolean ParExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParExpr")) return false;
    if (!nextTokenIs(b, LEFT_PAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_PAREN);
    r = r && LambdaExpr(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, PAR_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // Statement *
  static boolean Root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Root")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Root", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (Declaration | LambdaExpr) ';'
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = Statement_0(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Declaration | LambdaExpr
  private static boolean Statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_0")) return false;
    boolean r;
    r = Declaration(b, l + 1);
    if (!r) r = LambdaExpr(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Int TypingExprOther | Bool TypingExprOther | Int | Bool
  public static boolean TypingExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypingExpr")) return false;
    if (!nextTokenIs(b, "<typing expr>", BOOL, INT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPING_EXPR, "<typing expr>");
    r = TypingExpr_0(b, l + 1);
    if (!r) r = TypingExpr_1(b, l + 1);
    if (!r) r = consumeToken(b, INT);
    if (!r) r = consumeToken(b, BOOL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Int TypingExprOther
  private static boolean TypingExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypingExpr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INT);
    r = r && TypingExprOther(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Bool TypingExprOther
  private static boolean TypingExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypingExpr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BOOL);
    r = r && TypingExprOther(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '->' TypingExpr
  public static boolean TypingExprOther(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypingExprOther")) return false;
    if (!nextTokenIs(b, ARROW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ARROW);
    r = r && TypingExpr(b, l + 1);
    exit_section_(b, m, TYPING_EXPR_OTHER, r);
    return r;
  }

  /* ********************************************************** */
  // id+
  public static boolean VariableExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariableExpr")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, ID)) break;
      if (!empty_element_parsed_guard_(b, "VariableExpr", c)) break;
    }
    exit_section_(b, m, VARIABLE_EXPR, r);
    return r;
  }

}
