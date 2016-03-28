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
        try {
            MergeLists mergeLists=new MergeLists(cliOptions.getFile(),cliOptions.getFile1());
            mergeLists.listOut(mergeLists.getListOut());
        } catch (IOException e) {
            System.out.println("uncorrect filenames "+cliOptions.getFile().toString()+" "+cliOptions.getFile1().toString());
            System.out.println(e.getMessage());
        }
    }
}

