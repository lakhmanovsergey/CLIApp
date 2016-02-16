package ru.lakhmanovsergey.CLI;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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
    options.addOption("h", "help", false, "print this message");
    options.addOption(Option.builder("d") // короткая опция
            .longOpt("date") // длинная опция -ааа=ббббб
            .desc("date dd-mm-yyyy")  //дескриптор, выводится в хелпе
            .hasArg() // если аргумент обязателен
            .argName("DATE") // тип аргумента, выводится в хелпе
            .build());
    options.addOption(Option.builder("t")
            .longOpt("time")
            .desc("time of day hh-mm-ss")
            .hasArg()
            .argName("TIME")
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

    static LocalDate getDate() {
        if(!commandLine.hasOption("d")) return null;
        String sDate=commandLine.getOptionValue("d");
        String regex="[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}"; //регулярное выражение для даты
        Matcher matcher= Pattern.compile(regex).matcher(sDate);
        if (!matcher.matches()) throw new IllegalArgumentException("uncorrect date = "+sDate);
        String[] masStr=sDate.split("-");
        int day= Integer.parseInt(masStr[0]);
        int month= Integer.parseInt(masStr[1]);
        int year= Integer.parseInt(masStr[2]);
        try{
            LocalDate date=LocalDate.of(year,month,day);
            return date;
        }catch (DateTimeException e){
            throw new IllegalArgumentException("uncorrect date = "+sDate+" "+e.getMessage());
        }
    }
    static File getFile(){
        if(!commandLine.hasOption("f")) return null;
        String sDate=commandLine.getOptionValue("f");
        File file=new File(sDate);
        if(!file.exists()) throw new IllegalArgumentException("uncorrect filename = "+sDate);
        return file.getAbsoluteFile();
    }

    public static LocalTime getTime() {
        if(!commandLine.hasOption("t")) return null;
        String sTime=commandLine.getOptionValue("t");
        String regex="[0-9]{1,2}-[0-9]{1,2}(-[0-9]{1,2})?"; //регулярное выражение для времени
        Matcher matcher= Pattern.compile(regex).matcher(sTime);
        if (!matcher.matches()) throw new IllegalArgumentException("uncorrect time = "+sTime);
        String[] masStr=sTime.split("-");
        int hour= Integer.parseInt(masStr[0]);
        int min= Integer.parseInt(masStr[1]);
        int sec=0;
        if (masStr.length==3)sec = Integer.parseInt(masStr[2]);
        try{
            LocalTime time=(masStr.length<3)?LocalTime.of(hour,min):LocalTime.of(hour,min,sec);
            return time;
        }catch (DateTimeException e){
            throw new IllegalArgumentException("uncorrect time = "+sTime+" "+e.getMessage());
        }

    }
}
