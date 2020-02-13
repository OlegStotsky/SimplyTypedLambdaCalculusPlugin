package com.olegstotsky.simplytypedlambdacalculus.execution;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.PtyCommandLine;
import com.intellij.execution.process.KillableColoredProcessHandler;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.openapi.diagnostic.Logger;
import com.olegstotsky.simplytypedlambdacalculus.evaluator.StitchDockerRunner;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Optional;

public class SimplyTypedLambdaCalculusCommandLineState extends CommandLineState {
    private static final Logger LOG = Logger.getInstance(SimplyTypedLambdaCalculusCommandLineState.class);
    private String filePath;

    public SimplyTypedLambdaCalculusCommandLineState(ExecutionEnvironment environment, String filePath) {
        super(environment);
        this.filePath = filePath;
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        Optional<String> stitchProcessId = StitchDockerRunner.INSTANCE.getStitchProcessId();
        if (!stitchProcessId.isPresent()) {
            throw new ExecutionException("Stitch Docker Process has not been started yet");
        }
        GeneralCommandLine cmd = new PtyCommandLine().withConsoleMode(true);
        cmd.setExePath("docker");
        cmd.addParameters("container", "exec", "-i", stitchProcessId.get(), "/scripts/my_stitch_bin");
        OSProcessHandler processHandler = new KillableColoredProcessHandler(cmd);
        byte[] fileText = null;
        try {
            fileText = Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            LOG.warn(e.getMessage());
            throw new ExecutionException(e);
        }
        PrintWriter stitchStdIn = new PrintWriter(processHandler.getProcessInput());
        stitchStdIn.println(new String(fileText));
        stitchStdIn.flush();
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
