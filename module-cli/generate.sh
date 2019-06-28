#!/bin/bash

javac \
-cp src/main/java/;../module-cli-annotation/build/libs/module-cli-annotation.jar \
-processorpath ../module-cli-annotation/build/libs/module-cli-annotation.jar \
-processor com.suntri.cli.SubCommandProcessor \
src/main/java/com/suntri/cli/CmdHelloWorld.java 

