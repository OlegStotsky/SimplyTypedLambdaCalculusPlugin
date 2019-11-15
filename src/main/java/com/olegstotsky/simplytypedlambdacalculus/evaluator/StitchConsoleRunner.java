package com.olegstotsky.simplytypedlambdacalculus.evaluator;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class StitchConsoleRunner implements StitchRunner {
    private static String STITCH_BINARY_PATH = "/Users/olegstotsky/desktop/University/LambdaPlugin/stitch/stitch";
    private static Logger LOG = Logger.getInstance(StitchConsoleRunner.class);

    @Override
    public Optional<List<String>> evaluate(String text) {
        GeneralCommandLine cmd = new GeneralCommandLine();
        cmd.setExePath(STITCH_BINARY_PATH);
        try {
            Application app = ApplicationManager.getApplication();
            CapturingProcessHandler processHandler = new CapturingProcessHandler(cmd);
            PrintWriter stitchStdIn = new PrintWriter(processHandler.getProcessInput());
            stitchStdIn.println(text.substring(0, text.length() - 1));
            stitchStdIn.flush();
            stitchStdIn.println(":quit");
            stitchStdIn.flush();
            ProcessOutput out = processHandler.runProcess();
            List<String> lines = out.getStdoutLines();
            return Optional.of(lines);
        } catch (ExecutionException e) {
            LOG.warn(e);
            return Optional.empty();
        }
    }
}
