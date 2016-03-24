package ru.lakhmanovsergey.CLI;

import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleServerSocketTest {
    private SimpleServerSocket serverSocket;
    private Socket client;
    PrintWriter out;
    BufferedReader in;
    @Rule
    public final Timeout globalTimeout = Timeout.seconds(1);
    @Before
    public void init() throws IOException {
        serverSocket = new SimpleServerSocket(8888);
        client = new Socket(InetAddress.getLocalHost(), 8888);
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
    public void a1testGetIn() throws Exception {
        out.println("test");out.flush();
        assertEquals("test",serverSocket.getIn().readLine());
        out.println("hello");out.flush();
        assertEquals("hello",serverSocket.getIn().readLine());
    }

    @Test
    public void a2testGetOut() throws Exception {
        serverSocket.getOut().println("test");
        assertEquals("test",in.readLine());
        serverSocket.getOut().println("hello");
        assertEquals("hello",in.readLine());
    }
/*
    @Test
    public void a3testLoopSocket() throws Exception {
        Thread thread=new Thread(serverSocket);
        thread.start();
        assertTrue(thread.isAlive());
        out.println("test");out.flush();
        assertEquals("test",in.readLine());
        out.println("quit");out.flush();
        Thread.sleep(10);
        assertFalse(thread.isAlive());
    }
*/
    @Test
    public void a4testSerializable() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.out"));
        oos.writeObject(serverSocket);
        oos.flush();oos.close();
        serverSocket.closeSocket();
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("temp.out"));
        SimpleServerSocket serverSocket1=(SimpleServerSocket)ois.readObject();
        assertNotNull(serverSocket1);
    }
}