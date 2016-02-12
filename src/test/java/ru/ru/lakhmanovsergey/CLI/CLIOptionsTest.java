package ru.lakhmanovsergey.CLI;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class CLIOptionsTest {

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
        CLIOptions.setCommandLine(null);
        assertNull(CLIOptions.getDate());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetDateEx() throws ParseException {
            CLIOptions.setCommandLine(new String[]{"-d", "13-0"});
            CLIOptions.getDate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetDateEx1() throws ParseException {
        CLIOptions.setCommandLine(new String[]{"-d", "test"});
        CLIOptions.getDate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetDateEx2() throws ParseException {
        CLIOptions.setCommandLine(new String[]{"-d", "54545454"});
        CLIOptions.getDate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetDateEx3() throws ParseException {
        CLIOptions.setCommandLine(new String[]{"-d", "32-58-2015"});
        CLIOptions.getDate();
    }
    public void testGetDateEx4() throws ParseException {
        CLIOptions.setCommandLine(new String[]{"-d", "11.08.2013"});
        CLIOptions.getDate();
    }
}