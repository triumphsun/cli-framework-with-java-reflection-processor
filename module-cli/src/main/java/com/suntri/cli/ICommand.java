package com.suntri.cli;

/**
 * Author: Chia-Yang, Sun
 * Email: triumph.sun@gmail.com
 * Date: 2019/6/27
 */

public interface ICommand {
    public void execute(String [] args);
    public void printHelpMessage();
}
