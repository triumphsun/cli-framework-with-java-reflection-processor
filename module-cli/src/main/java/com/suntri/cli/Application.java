package com.suntri.cli;

import java.util.Arrays;

/**
 * Author: Chia-Yang, Sun
 * Email: triumph.sun@gmail.com
 * Date: 2019/6/27
 */

public class Application implements ICommand {

    private CommandFactory factory;

    public Application(CommandFactory factory){
        this.factory = factory;
    }

    @Override
    public void execute(String[] args) {
        if(args.length <= 0){
            printHelpMessage();
            return;
        }

        String strSubCommand = args[0];
        try {
            ICommand command = factory.searchCommand(strSubCommand);
            command.execute(Arrays.copyOfRange(args, 1, args.length));
        } catch (NullPointerException e){
            printHelpMessage();
        }
    }

    @Override
    public void printHelpMessage() {
        this.factory.printHelpMessage();
    }

    public static void main(String [] args){
        ICommand app = new Application(new CommandFactoryGenerated());
        app.execute(args);
    }
}
