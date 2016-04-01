package ru.lakhmanovsergey.CLI;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockTest {
    //@Test
      public void test1() {
        Comparable c=mock(Comparable.class);
        when(c.compareTo("test")).thenReturn(1);
        when(c.compareTo("test1")).thenReturn(2);
        assertEquals(2,c.compareTo("test1"));
        assertEquals(1,c.compareTo("test"));
        when(c.compareTo(anyString())).thenReturn(-1);
        assertEquals(-1,c.compareTo("ghsdjkhgskdjgh"));
    }
    public void test2(){
        SimpleServerSocket serverSocket=new SimpleServerSocket(8888);
    }
}
