package ru.lakhmanovsergey.CLI;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class App
{
    public static void main( String[] args ) throws ParseException, ClassNotFoundException, IOException {
        //CLIOptions.setCommandLine(args);
        //CLIOptions.getDate().format(DateTimeFormatter.BASIC_ISO_DATE);
        SimpleServerSocket serverSocket=new SimpleServerSocket(8888);
        serverSocket.showIn();
    }
}

