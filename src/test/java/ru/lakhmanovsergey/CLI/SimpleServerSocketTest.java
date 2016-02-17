package ru.lakhmanovsergey.CLI;

import org.junit.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class SimpleServerSocketTest {
    private SimpleServerSocket serverSocket;
    private Socket client;
    PrintWriter out;
    BufferedReader in;
    @Before
    public void init() throws IOException {
        serverSocket = new SimpleServerSocket(8889);
        client = new Socket(InetAddress.getLocalHost(), 8889);
        out = new PrintWriter(client.getOutputStream());
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }
    @After
    public void close() throws IOException {
        in.close();
        out.close();
        serverSocket.closeSocket();
        client.close();
    }
    @Test
    public void testGetIn() throws Exception {
        out.println("test");
        out.flush();
        assertEquals("test",serverSocket.getIn().readLine());
    }

    @Test
    public void testGetOut() throws Exception {
        serverSocket.getOut().println("test");
        assertEquals("test",in.readLine());
    }

    @Test
    public void testLoopSocket() throws Exception {
        out.println("test");
        out.flush();
        serverSocket.loopSocket();
        assertEquals("test",in.readLine());
    }
    //@Test
    public void testShowIn() throws IOException {
        out.println("test");
        out.flush();
        serverSocket.showIn();
    }
}