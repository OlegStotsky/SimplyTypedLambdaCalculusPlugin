package com.olegstotsky.simplytypedlambdacalculus;

import com.intellij.lexer.FlexAdapter;
import com.olegstotsky.simplytypedlambdacalculus.parser._SimplyTypedLambdaCalculusLexer;

public class SimplyTypedLambdaCalculusLexerAdapter extends FlexAdapter {
    public SimplyTypedLambdaCalculusLexerAdapter() {
        super(new _SimplyTypedLambdaCalculusLexer(null));
    }
}
