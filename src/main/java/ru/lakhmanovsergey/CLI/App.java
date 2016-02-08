package ru.lakhmanovsergey.CLI;

import org.apache.commons.cli.*;

public class App
{
    static CommandLineParser parser; //переменные для поддержки опций командной строки
    static Options options;
    static CommandLine line;
    public static void main( String[] args ) throws ParseException {
        parser = new DefaultParser();
        options = new Options();//Опции командной строки, можно создавать по отдельности
        Option option = new Option("m", "mas", true, "any arguments");
        option.setArgs(3);
        options.addOption(option);
        options.addOption("t", "test", true, "my first command line option");
        line = parser.parse(options, args);
        if (line.hasOption("t")) {
            System.out.println("test = " + line.getOptionValue("t"));
            if (line.hasOption("m")) {
                System.out.print("mas = ");
                for (String str : line.getOptionValues("m")) {
                    System.out.print(str + ":");
                }
                System.out.println();
            }
            System.out.print("options = ");
            for (Option str : line.getOptions()) {
                System.out.print(str + ":");
            }
            System.out.println();
            System.out.print("line = ");
            for (String str : line.getArgs()) {
                System.out.print(str + ":");
            }
            System.out.println();
        }
    }
}

