package ru.lakhmanovsergey.CLI;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String regex="[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}"; //регулярное выражение для даты
        Matcher matcher= Pattern.compile(regex).matcher(sDate);
        if (!matcher.matches()) throw new IllegalArgumentException("uncorrect date = "+sDate);
        String[] masStr=sDate.split("-");
        int day= Integer.parseInt(masStr[0]);
        if (day<=0||day>31) throw new IllegalArgumentException("uncorrect date = " + sDate+ " from 1 to 31");
        int month= Integer.parseInt(masStr[1]);
        if (month<=0||month>12) throw new IllegalArgumentException("uncorrect date = "+sDate+" month from 1 to 12");
        if((month==4||month==6||month==9||month==11)&&day==31) throw new IllegalArgumentException("uncorrect date = " + sDate+ " from 1 to 30");
        if(month==2&&day==30) throw new IllegalArgumentException("uncorrect date = " + sDate+ " from 1 to 29");
        int year= Integer.parseInt(masStr[2]);
        if (year<=0||year>9999) throw new IllegalArgumentException("uncorrect date = "+sDate+" year from 1 to 9999");
        GregorianCalendar date=new GregorianCalendar(year,month-1,day);
        if(!date.isLeapYear(year)&&month==2&&day==29)throw new IllegalArgumentException("uncorrect date = "+sDate+" day from 1 to 28");
        return date;
    }
    static File getFile(){
        if(!commandLine.hasOption("f")) return null;
        String sDate=commandLine.getOptionValue("f");
        File file=new File(sDate);
        return file;
    }
}
