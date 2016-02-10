package ru.lakhmanovsergey.CLI;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class App
{
    public static void main( String[] args ) throws ParseException, ClassNotFoundException {
        CLIOptions.setCommandLine(args);
        SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Date = " + format.format(CLIOptions.getDate().getTime()));
    }
}

