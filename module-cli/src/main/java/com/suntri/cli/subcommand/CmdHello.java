package com.suntri.cli.subcommand;

import com.suntri.cli.ICommand;
import com.suntri.cli.SubCommand;

/**
 * Author: Chia-Yang, Sun
 * Email: triumph.sun@gmail.com
 * Date: 2019/6/27
 */

@SubCommand(name = "hello", description = "Test command.")
public class CmdHello implements ICommand {

    @Override
    public void execute(String[] args) {

    }

    @Override
    public void printHelpMessage() {

    }
}
