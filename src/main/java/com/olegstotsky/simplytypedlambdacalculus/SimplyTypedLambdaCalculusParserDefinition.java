package com.olegstotsky.simplytypedlambdacalculus;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.olegstotsky.simplytypedlambdacalculus.parser.SimplyTypedLambdaCalculusParser;
import com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusFile;
import com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusTypes;
import org.jetbrains.annotations.NotNull;

public class SimplyTypedLambdaCalculusParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final IFileElementType FILE = new IFileElementType(SimplyTypedLambdaCalculusLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new SimplyTypedLambdaCalculusLexerAdapter();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new SimplyTypedLambdaCalculusParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @Override
    public TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return SimplyTypedLambdaCalculusTypes.Factory.createElement(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new SimplyTypedLambdaCalculusFile(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MUST;
    }
}
