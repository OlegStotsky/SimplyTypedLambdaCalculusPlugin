package com.olegstotsky.simplytypedlambdacalculus.execution;

import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SimplyTypedLambdaCalculusSettingsEditor extends SettingsEditor<RunConfiguration> {
    @Override
    protected void resetEditorFrom(@NotNull RunConfiguration s) {

    }

    @Override
    protected void applyEditorTo(@NotNull RunConfiguration s) throws ConfigurationException {

    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return new JPanel();
    }
}
