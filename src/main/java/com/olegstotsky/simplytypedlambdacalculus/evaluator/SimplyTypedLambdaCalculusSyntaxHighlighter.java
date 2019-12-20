package com.olegstotsky.simplytypedlambdacalculus.evaluator;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.olegstotsky.simplytypedlambdacalculus.SimplyTypedLambdaCalculusLexerAdapter;
import com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class SimplyTypedLambdaCalculusSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey OPERATOR = createTextAttributesKey("SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("SIMPLE_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);
    public static final IElementType[] keywords = {SimplyTypedLambdaCalculusTypes.TRUE,
            SimplyTypedLambdaCalculusTypes.FALSE,
            SimplyTypedLambdaCalculusTypes.IF,
            SimplyTypedLambdaCalculusTypes.TRUE,
            SimplyTypedLambdaCalculusTypes.BOOL,
            SimplyTypedLambdaCalculusTypes.INT,
            SimplyTypedLambdaCalculusTypes.ELSE,
            SimplyTypedLambdaCalculusTypes.THEN
    };
    public static final IElementType[] identifiers = {SimplyTypedLambdaCalculusTypes.ID};
    public static final IElementType[] badCharacters = {TokenType.BAD_CHARACTER};

    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SimplyTypedLambdaCalculusLexerAdapter();
    }

    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (Arrays.stream(keywords).anyMatch(elem -> elem.equals(tokenType))) {
            return KEYWORD_KEYS;
        }
        if (Arrays.stream(identifiers).anyMatch(elem -> elem.equals(tokenType))) {
            return IDENTIFIER_KEYS;
        }
        if (Arrays.stream(badCharacters).anyMatch(elem -> elem.equals(tokenType))) {
            return BAD_CHAR_KEYS;
        } else {
            return OPERATOR_KEYS;
        }
    }
}
