package com.olegstotsky.simplytypedlambdacalculus.execution;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimplyTypedLambdaCalculusRunConfiguration extends RunConfigurationBase {
    protected SimplyTypedLambdaCalculusRunConfiguration(@NotNull Project project, @Nullable ConfigurationFactory factory, @Nullable String name) {
        super(project, factory, name);
    }

    @NotNull
    @Override
    public SettingsEditor<RunConfiguration> getConfigurationEditor() {
        return new SimplyTypedLambdaCalculusSettingsEditor();
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
        SimplyTypedLambdaCalculusCommandLineState state = new SimplyTypedLambdaCalculusCommandLineState(environment);
        return state;
    }
}
