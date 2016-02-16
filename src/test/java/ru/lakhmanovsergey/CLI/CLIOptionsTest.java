package ru.lakhmanovsergey.CLI;

//import com.sun.xml.internal.xsom.impl.util.SchemaTreeTraverser;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
@RunWith(DataProviderRunner.class)
public class CLIOptionsTest extends Assert{
    @DataProvider
    public static Object[][] cliOptionsData(){
        return new Object[][]{
                {null,null,null,null},
                {new String[] {"-h"},
                        null,null,null},
                {new String[] {"-d", "13-05-2014","-t","13-20-40"},
                        LocalDate.of(2014, Month.MAY,13),
                        null,
                        LocalTime.of(13,20,40)},
                {new String[] {"-d", "01-12-1538","-t","11-00"},
                        LocalDate.of(1538,Month.DECEMBER,01),
                        null,
                        LocalTime.of(11,00)},
                {new String[] {"-d", "31-05-1971","-t","23-5-20"},
                        LocalDate.of(1971,Month.MAY,31),
                        null,
                        LocalTime.of(23,05,20)},
                {new String[] {"-d", "29-2-2016","-t","00-30"},
                        LocalDate.of(2016,Month.FEBRUARY,29),
                        null,
                        LocalTime.of(00,30)},
                {new String[] {"-f", "/home/lsp/IdeaProjects/CLIApp/pom.xml"},
                        null,
                        new File("/home/lsp/IdeaProjects/CLIApp/pom.xml"),
                        null},
                {new String[] {"-f", "pom.xml"},
                        null,
                        new File("/home/lsp/IdeaProjects/CLIApp/pom.xml"),
                        null},
        };
    }
    @DataProvider
    public static Object[][] cliOptionsExceptionData(){
        return new Object[][]{
                {new String[]{"-d","13-0"}},
                {new String[]{"-d","test"}},
                {new String[]{"-d","65736457654"}},
                {new String[]{"-d","15-58-2015"}},
                {new String[]{"-d","13.11.2018"}},
                {new String[]{"-d","31-04-2014"}},
                {new String[]{"-d","29-02-2013"}},
                {new String[]{"-f","blablabla"}},
                {new String[]{"-f","&65*%"}},
                {new String[]{"-f","ggffdd kkkl;ll"}},
                {new String[]{"-f","test"}},
                {new String[]{"-t","test"}},
                {new String[]{"-t","38-11-40"}},
                {new String[]{"-t","23-72"}},
                {new String[]{"-t","10-55-77"}},
        };
    }
    @Test
    @UseDataProvider("cliOptionsData")
    public void getDataTest(String[] input, LocalDate date, File file,LocalTime time) throws ParseException {
      CLIOptions.setCommandLine(input);
        assertEquals(date,CLIOptions.getDate());
    }
    @Test
    @UseDataProvider("cliOptionsData")
    public void getTimeTest(String[] input, LocalDate date, File file, LocalTime time) throws ParseException {
        CLIOptions.setCommandLine(input);
        assertEquals(time,CLIOptions.getTime());
    }
    @Test
    @UseDataProvider("cliOptionsData")
    public void getFileTest(String[] input, LocalDate date,File file,LocalTime time) throws ParseException {
        CLIOptions.setCommandLine(input);
        assertEquals(file,CLIOptions.getFile());
    }
    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("cliOptionsExceptionData")
    public void testGetDataException(String[] input) throws ParseException {
        CLIOptions.setCommandLine(input);
        CLIOptions.getDate();
        CLIOptions.getFile();
        CLIOptions.getTime();
    }
}