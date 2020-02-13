package com.olegstotsky.simplytypedlambdacalculus;

import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.components.ServiceManager;
import com.olegstotsky.simplytypedlambdacalculus.execution.SimplyTypedLambdaCalculusCommandLineState;
import com.olegstotsky.simplytypedlambdacalculus.execution.SimplyTypedLambdaCalculusRunConfiguration;
import org.jetbrains.annotations.NotNull;

public interface StitchSupport {
    static StitchSupport getInstance() {
        return ServiceManager.getService(StitchSupport.class);
    }

    boolean isExternalFormatterEnabled();

    boolean isRenameEnabled();

    @NotNull
    RunProfileState createRunProfileState(@NotNull Executor executor,
                                          @NotNull ExecutionEnvironment environment,
                                          @NotNull SimplyTypedLambdaCalculusRunConfiguration configuration);

    class Impl implements StitchSupport {
        @Override
        public boolean isExternalFormatterEnabled() {
            return true;
        }

        @Override
        public boolean isRenameEnabled() {
            return true;
        }

        @NotNull
        @Override
        public RunProfileState createRunProfileState(@NotNull Executor executor,
                                                     @NotNull ExecutionEnvironment environment,
                                                     @NotNull SimplyTypedLambdaCalculusRunConfiguration configuration) {
            return new SimplyTypedLambdaCalculusCommandLineState(environment, configuration.getFilePath());
        }
    }
}

