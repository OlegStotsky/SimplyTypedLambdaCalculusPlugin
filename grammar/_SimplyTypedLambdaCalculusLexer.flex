package com.olegstotsky.simplytypedlambdacalculus.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusTypes.*;

%%

%{
  public _SimplyTypedLambdaCalculusLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _SimplyTypedLambdaCalculusLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

SPACE=[ \t\n\x0B\f\r]+
ID=[a-zA-Z]
NUMBER=[0-9]+

%%
<YYINITIAL> {
  {WHITE_SPACE}      { return WHITE_SPACE; }

  "\\"               { return BACKSLASH; }
  "->"               { return ARROW; }
  "+"                { return PLUS; }
  "-"                { return MINUS; }
  "("                { return LEFT_PAREN; }
  ")"                { return RIGHT_PAREN; }
  "*"                { return MUL; }
  "/"                { return DIV; }
  "%"                { return REM; }
  "<"                { return LT; }
  ">"                { return GT; }
  "<="               { return LTE; }
  ">="               { return GTE; }
  "="                { return EQ; }
  "."                { return DOT; }
  ":"                { return COLON; }
  "Int"              { return INT; }
  "Bool"             { return BOOL; }

  {SPACE}            { return SPACE; }
  {ID}               { return ID; }
  {NUMBER}           { return NUMBER; }

}

[^] { return BAD_CHARACTER; }
