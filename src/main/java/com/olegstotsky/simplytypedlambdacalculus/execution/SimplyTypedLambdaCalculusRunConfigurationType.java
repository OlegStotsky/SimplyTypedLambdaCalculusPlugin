package com.olegstotsky.simplytypedlambdacalculus.execution;

import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.openapi.util.NotNullLazyValue;
import com.olegstotsky.simplytypedlambdacalculus.SimplyTypedLambdaCalculusIcons;
import com.olegstotsky.simplytypedlambdacalculus.SimplyTypedLambdaCalculusLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SimplyTypedLambdaCalculusRunConfigurationType extends ConfigurationTypeBase {
    protected SimplyTypedLambdaCalculusRunConfigurationType() {
        super("STLC", "SimplyTypedLambdaCalculus", "Simply Typed Lambda Calculus Runner", SimplyTypedLambdaCalculusIcons.ICON);
        addFactory(new SimplyTypedLambdaCalculusConfigurationFactory(this));
    }
}
