package ru.lakhmanovsergey.CLI;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class App
{
    public static void main( String[] args ) throws ParseException, ClassNotFoundException {
        CLIOptions.setCommandLine(args);
        CLIOptions.getDate().format(DateTimeFormatter.BASIC_ISO_DATE);
    }
}

