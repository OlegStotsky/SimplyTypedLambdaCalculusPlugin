package com.olegstotsky.simplytypedlambdacalculus.psi;

import com.intellij.psi.tree.IElementType;
import com.olegstotsky.simplytypedlambdacalculus.SimplyTypedLambdaCalculusLanguage;

public class SimplyTypedLambdaCalculusElementType extends IElementType {
    public SimplyTypedLambdaCalculusElementType(String debugName) {
        super(debugName, SimplyTypedLambdaCalculusLanguage.INSTANCE);
    }
}
