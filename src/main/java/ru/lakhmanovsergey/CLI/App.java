package ru.lakhmanovsergey.CLI;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class App
{
    public static void main( String[] args ) throws ParseException {
        MyCLIOptions cliOptions=new MyCLIOptions(args);
    }
}

