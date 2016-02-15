package ru.lakhmanovsergey.CLI;

import com.sun.xml.internal.xsom.impl.util.SchemaTreeTraverser;
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
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
@RunWith(DataProviderRunner.class)
public class CLIOptionsTest extends Assert{
    @DataProvider
    public static Object[][] cliOptionsData(){
        return new Object[][]{
                {null,null,null},
                {new String[] {"-h"},null,null},
                {new String[] {"-d", "13-05-2014"}, new GregorianCalendar(2014,04,13 ),null},
                {new String[] {"-d", "01-12-1538"}, new GregorianCalendar(1538,11,01 ),null},
                {new String[] {"-d", "31-05-1971"}, new GregorianCalendar(1971,04,31 ),null},
                {new String[] {"-d", "29-2-2016"}, new GregorianCalendar(2016,01,29 ),null},
                {new String[] {"-f", "/Users/lsp/IdeaProjects/CLIApp/pom.xml"},null,new File("/Users/lsp/IdeaProjects/CLIApp/pom.xml")},
                {new String[] {"-f", "pom.xml"},null,new File("/Users/lsp/IdeaProjects/CLIApp/pom.xml")},
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
        };
    }
    @Test
    @UseDataProvider("cliOptionsData")
    public void getDataTest(String[] input, GregorianCalendar date, File file) throws ParseException {
      CLIOptions.setCommandLine(input);
        assertEquals(date,CLIOptions.getDate());
    }
    @Test
    @UseDataProvider("cliOptionsData")
    public void getFileTest(String[] input,GregorianCalendar date,File file) throws ParseException {
        CLIOptions.setCommandLine(input);
        assertEquals(file,CLIOptions.getFile());
    }
    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("cliOptionsExceptionData")
    public void testGetDataException(String[] input) throws ParseException {
        CLIOptions.setCommandLine(input);
        CLIOptions.getDate();
        CLIOptions.getFile();
    }
}