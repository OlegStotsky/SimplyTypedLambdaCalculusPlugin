package com.olegstotsky.simplytypedlambdacalculus.execution;

import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SimplyTypedLambdaCalculusSettingsEditor extends SettingsEditor<RunConfiguration> {
    JPanel panel;
    TextFieldWithBrowseButton fileSelector;

    SimplyTypedLambdaCalculusSettingsEditor(Project project) {
        panel = new JPanel();
        fileSelector = new TextFieldWithBrowseButton();
        fileSelector.addBrowseFolderListener("hw", "hw2", project, FileChooserDescriptorFactory.createSingleFileDescriptor());
        panel.add(fileSelector);
    }

    @Override
    protected void resetEditorFrom(@NotNull RunConfiguration configuration) {
        fileSelector.setText("");
    }

    @Override
    protected void applyEditorTo(@NotNull RunConfiguration configuration) throws ConfigurationException {
        ((SimplyTypedLambdaCalculusRunConfiguration) configuration).setFilePath(fileSelector.getText());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return panel;
    }
}
