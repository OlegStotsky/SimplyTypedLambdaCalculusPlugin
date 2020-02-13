package com.olegstotsky.simplytypedlambdacalculus.execution;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.LocatableConfigurationBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizerUtil;
import com.intellij.openapi.util.io.FileUtil;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.openapi.util.io.FileUtil.toSystemDependentName;
import static com.intellij.openapi.util.text.StringUtil.notNullize;

public class SimplyTypedLambdaCalculusRunConfiguration extends LocatableConfigurationBase {
    private static final String TAG_PREFIX = "INDEPENDENT_";
    private static final String FILE_PATH_TAG = "FILE_PATH";
    private String filePath;

    protected SimplyTypedLambdaCalculusRunConfiguration(@NotNull Project project, @Nullable ConfigurationFactory factory, @Nullable String name) {
        super(project, factory, name);
    }

    private static void writePathWithMetadata(@NotNull Element element, @NotNull String path, @NotNull String pathTag) {
        String systemIndependentPath = FileUtil.toSystemIndependentName(path);
        JDOMExternalizerUtil.writeField(element, TAG_PREFIX + pathTag, Boolean.toString(systemIndependentPath.equals(path)));
        JDOMExternalizerUtil.writeField(element, pathTag, systemIndependentPath);
    }

    private static String readPathWithMetadata(@NotNull Element element, @NotNull String pathTag) {
        return Boolean.parseBoolean(JDOMExternalizerUtil.readField(element, TAG_PREFIX + pathTag))
                ? readStringTagValue(element, pathTag)
                : toSystemDependentName(readStringTagValue(element, pathTag));
    }

    @NotNull
    private static String readStringTagValue(@NotNull Element element, @NotNull String tagName) {
        return notNullize(JDOMExternalizerUtil.readField(element, tagName), "");
    }

    @NotNull
    @Override
    public SettingsEditor<RunConfiguration> getConfigurationEditor() {
        return new SimplyTypedLambdaCalculusSettingsEditor(super.getProject());
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
        SimplyTypedLambdaCalculusCommandLineState state = new SimplyTypedLambdaCalculusCommandLineState(environment, filePath);
        return state;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeExternal(@NotNull Element element) {
        super.writeExternal(element);

        writePathWithMetadata(element, filePath, FILE_PATH_TAG);
    }

    @Override
    public void readExternal(@NotNull Element element) throws InvalidDataException {
        super.readExternal(element);

        filePath = readPathWithMetadata(element, FILE_PATH_TAG);
    }
}
