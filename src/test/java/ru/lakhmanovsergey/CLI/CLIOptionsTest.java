package ru.lakhmanovsergey.CLI;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class CLIOptionsTest {
/*    @Ignore
    @Test
    public void testGetDate() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
        CLIOptions.setCommandLine(new String[]{"-d", "13-05-2014"});
        assertEquals("13-05-2014",format.format(CLIOptions.getDate().getTime()));
        CLIOptions.setCommandLine(new String[]{"-d", "01-12-1538"});
        assertEquals("01-12-1538",format.format(CLIOptions.getDate().getTime()));
        CLIOptions.setCommandLine(new String[]{"-d", "31-05-1971"});
        assertEquals("31-05-1971",format.format(CLIOptions.getDate().getTime()));
        CLIOptions.setCommandLine(new String[]{"-d", "29-2-2016"});
        assertEquals("29-02-2016",format.format(CLIOptions.getDate().getTime()));
        CLIOptions.setCommandLine(null);
        assertNull(CLIOptions.getDate());
        CLIOptions.setCommandLine(new String[]{"-h"});
        assertNull(CLIOptions.getDate());
        CLIOptions.setCommandLine(new String[]{"-f","test"});
        assertNull(CLIOptions.getDate());
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testGetDateEx() throws ParseException {
            CLIOptions.setCommandLine(new String[]{"-d", "13-0"});
            CLIOptions.getDate();
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testGetDateEx1() throws ParseException {
        CLIOptions.setCommandLine(new String[]{"-d", "test"});
        CLIOptions.getDate();
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testGetDateEx2() throws ParseException {
        CLIOptions.setCommandLine(new String[]{"-d", "54545454"});
        CLIOptions.getDate();
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testGetDateEx3() throws ParseException {
        CLIOptions.setCommandLine(new String[]{"-d", "15-58-2015"});
        CLIOptions.getDate();
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testGetDateEx4() throws ParseException {
        CLIOptions.setCommandLine(new String[]{"-d", "11.08.2013"});
        CLIOptions.getDate();
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testGetDateEx5() throws ParseException {
        CLIOptions.setCommandLine(new String[]{"-d", "31-04-2013"});
        CLIOptions.getDate();
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testGetDateEx6() throws ParseException {
        CLIOptions.setCommandLine(new String[]{"-d", "29-02-2013"});
        CLIOptions.getDate();
    }
    */
    @Test
    public void testGetFile() throws ParseException {
        CLIOptions.setCommandLine(null);
        assertNull(CLIOptions.getFile());
        CLIOptions.setCommandLine(new String[]{"-f","/Users/lsp/IdeaProjects/CLIApp/pom.xml"});
        assertEquals("/Users/lsp/IdeaProjects/CLIApp/pom.xml",CLIOptions.getFile().getAbsolutePath().toString());
    }
}