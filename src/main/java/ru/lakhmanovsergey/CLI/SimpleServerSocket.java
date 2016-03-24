package ru.lakhmanovsergey.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.Serializable;

public class SimpleServerSocket implements Runnable, Serializable {
    transient private ServerSocket serverSocket;
    transient private Socket clientSocket;
    transient private PrintWriter out;
    transient private BufferedReader in;
    public SimpleServerSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
            //System.out.println("Server socket ready = " + serverSocket.toString());
        }catch (IOException e){
            System.out.println("can not create socket port = "+port+" "+e.getMessage());
        }
    }

    public BufferedReader getIn() throws IOException {
        if(in!=null)return in;
        try{
            clientSocket = serverSocket.accept();
            //System.out.println("Server socket client accept = " + clientSocket.toString());
        } catch (IOException e) {
            System.out.println("can not accept to socket = "+e.getMessage());
        }
        try {
            in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out=new PrintWriter(clientSocket.getOutputStream(),true);
        } catch (IOException e) {
            System.out.println("can not create I/O = "+e.getMessage());
        }
        //String line=in.readLine();
        //System.out.println("SimpleServerSocket.getIn::: "+line);
        return in;
    }

    public PrintWriter getOut() throws IOException {
        if(out!=null)return out;
        try{
            clientSocket = serverSocket.accept();
            //System.out.println("Server socket client accept = " + clientSocket.toString());
        } catch (IOException e) {
            System.out.println("can not accept to socket = "+e.getMessage());
        }
        try {
            out=new PrintWriter(clientSocket.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("can not create I/O = "+e.getMessage());
        }
        return out;
    }

    public boolean loopSocket() throws IOException {
        //System.out.println("SimpleServerSocket.loopSocket");
        while (true) {
            String line = getIn().readLine();
            if(line.equalsIgnoreCase("quit")) return true;
            getOut().println(line);
        }
    }
    public void showIn() throws IOException {
        //System.out.println("listen socket");
        String line=getIn().readLine();
        System.out.println(line);
    }
    public void closeSocket() throws IOException {
        //clientSocket.close();
        serverSocket.close();
    }

    @Override
    public void run() {
        try {
            loopSocket();
        } catch (IOException e) {
            System.out.println("can not ran thread: "+e.getMessage());
        }
    }
}
