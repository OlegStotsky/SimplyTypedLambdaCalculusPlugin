package com.olegstotsky.simplytypedlambdacalculus.execution;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.KillableProcessHandler;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import org.jetbrains.annotations.NotNull;

public class SimplyTypedLambdaCalculusCommandLineState extends CommandLineState {
    private static final String STITCH_BINARY_PATH = "/Users/olegstotsky/desktop/University/LambdaPlugin/stitch/stitch";


    public SimplyTypedLambdaCalculusCommandLineState(ExecutionEnvironment environment) {
        super(environment);
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        GeneralCommandLine cmd = new GeneralCommandLine();
        cmd.setExePath(STITCH_BINARY_PATH);
        OSProcessHandler processHandler = new KillableProcessHandler(cmd);
        ProcessTerminatedListener.attach(processHandler, getEnvironment().getProject());

        return processHandler;
    }

    @NotNull
    @Override
    public ExecutionResult execute(@NotNull Executor executor, @NotNull ProgramRunner runner) throws ExecutionException {
        ExecutionResult result = super.execute(executor, runner);
        return result;
    }
}
