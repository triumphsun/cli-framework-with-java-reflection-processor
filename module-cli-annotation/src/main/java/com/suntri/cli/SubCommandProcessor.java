package com.suntri.cli;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * Author: Chia-Yang, Sun
 * Email: triumph.sun@gmail.com
 * Date: 2019/6/27
 */

@SupportedAnnotationTypes("com.suntri.cli.SubCommand")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SubCommandProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        StringBuilder sbClass = new StringBuilder();
        StringBuilder sbHelp = new StringBuilder();

        sbClass.append("package com.suntri.cli;\n\n")
            .append("import java.util.HashMap;\n")
            .append("import java.util.Iterator;\n")
            .append("import javax.annotation.Generated;\n\n")
            .append("@Generated(\"com.suntri.cli.SubCommandProcessor\")\n")
            .append("public class CommandFactoryGenerated implements CommandFactory {\n\n")
            .append("\tprivate HashMap <String, String> subcommands = new HashMap <> ();\n\n")
            .append("\tpublic CommandFactoryGenerated(){\n");

        sbHelp.append("\tpublic void printHelpMessage(){\n")
                .append("\t\tSystem.out.println(\"Usage: suntri-cli [sub-command]\\n\");\n");

        for(Element element: roundEnv.getElementsAnnotatedWith(SubCommand.class)){
            String strSubCommand = element.getAnnotation(SubCommand.class).name();
            String strDesscrption = element.getAnnotation(SubCommand.class).description();
            String className = element.toString();
            sbClass.append("\t\tsubcommands.put(\"").append(strSubCommand).append("\",\"").append(className).append("\");\n");
            sbHelp.append("\t\tSystem.out.println(String.format(\"\\t%s\\t\\t%s\", \"")
                .append(strSubCommand)
                .append("\", \"")
                .append(strDesscrption)
                .append("\"));\n");
        }

        sbClass.append("\t}\n\n");

        sbClass.append("\tpublic ICommand searchCommand(String name) {\n")
            .append("\t\tString classname = subcommands.get(name);\n")
            .append("\t\tif(classname==null) return null;\n")
            .append("\t\ttry {\n")
            .append("\t\t\tICommand command = (ICommand) Class.forName(classname).newInstance();\n")
            .append("\t\t\tICommand logger = new Logger(command);\n")
            .append("\t\t\treturn logger;\n")
            .append("\t\t} catch (Exception e){\n")
            .append("\t\t\treturn null;\n")
            .append("\t\t}\n")
            .append("\t}\n");

        sbHelp.append("\t}\n");

        sbClass.append(sbHelp);

        sbClass.append("}\n");

        System.out.println(sbClass);

        try {
            JavaFileObject source = processingEnv.getFiler().createSourceFile("com.suntri.cli.CommandFactoryGenerated");
            Writer writer = source.openWriter();
            writer.write(sbClass.toString());
            writer.flush();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return true;
    }
}
