package com.olegstotsky.simplytypedlambdacalculus.execution;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class SimplyTypedLambdaCalculusConfigurationFactory extends ConfigurationFactory {
    private static final String FACTORY_NAME = "Simply Typed Lambda Calculus Configuration Factory";

    protected SimplyTypedLambdaCalculusConfigurationFactory(ConfigurationType type) {
        super(type);
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new SimplyTypedLambdaCalculusRunConfiguration(project, this, "Simply Typed Lambda Calculus Run");
    }
}
