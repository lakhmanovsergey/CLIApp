package ru.lakhmanovsergey.CLI;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Created by lsp on 10.02.16.
 */
public class CLIOptions {
    private static CommandLine commandLine;
    private static Options options;

    public static void printHelp(final Options options) //метод для вывода справки
    {
        final PrintWriter writer = new PrintWriter(System.out);// куда печатаем help
        final HelpFormatter helpFormatter = new HelpFormatter();// создаем объект для вывода help`а
        helpFormatter.printHelp(
                writer,
                80, // ширина строки вывода
                "java -jar test.jar", //подсказка по запуску самой программы
                "Options",  // строка перед выводом справки
                options,
                3, // число пробелов перед выводом опции
                5, // число пробелов перед выводом опцисания опции
                "-end help-", // строка следующая за выводом
                true);//формирование справки
        writer.flush(); // вывод
    }
    public static void setCommandLine(String[] args) throws ParseException {

    CommandLineParser parser = new DefaultParser(); // объект для разбора командной строки
    options = new Options();//Опции командной строки, можно создавать по отдельности
    /*
    options.addOption("t", // короткая опция
            "test", // длинная опция -ааа=ббббб
            true, // если аргумент обязателен
            "my first command line option" //дескриптор, выводится в хелпе
    );
    */
    options.addOption("h", "help", false, "print this message");
    options.addOption(Option.builder("d")
            .longOpt("date")
            .desc("date dd-mm-yyyy")
            .hasArg()
            .argName("DATE")
            .build());
    options.addOption(Option.builder("f")
            .hasArg()
            .longOpt("file")
            .desc("enter filename")
            .argName("FILE")
            .build());
        commandLine=parser.parse(options,args);
        if(commandLine.hasOption("h")) printHelp(options);
    }

    static Calendar getDate() {
        if(!commandLine.hasOption("d")) return null;
        String sDate=commandLine.getOptionValue("d");
        if("13-0".equals(sDate)) throw new IllegalArgumentException("uncorrect date = "+sDate);
        String[] masStr=sDate.split("-");
        int day= Integer.parseInt(masStr[0]);
        int month= Integer.parseInt(masStr[1]);
        int year= Integer.parseInt(masStr[2]);
        Calendar date=new GregorianCalendar(year,month-1,day);
        return date;
    }
}
