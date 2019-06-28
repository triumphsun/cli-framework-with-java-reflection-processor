package com.suntri.cli;

import java.util.Arrays;

/**
 * Author: Chia-Yang, Sun
 * Email: triumph.sun@gmail.com
 * Date: 2019/6/27
 */

public class Logger implements ICommand {

    public static final boolean isLogging = true;

    private ICommand command;

    public Logger(ICommand command){
        this.command = command;
    }

    @Override
    public void execute(String[] args) {
        log(String.format("candidate:\t%s", command.getClass().getCanonicalName()));
        log(String.format("args:\t\t" + Arrays.toString(args)));
        this.command.execute(args);
    }

    private void log(String line){
        if(isLogging){
            System.out.println(line);
        }
    }

    @Override
    public void printHelpMessage() {

    }
}
