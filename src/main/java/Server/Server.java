package Server;

import Client.Client;
import Model.ClientModel;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Server {
    private Socket srvSocket;
    private InetAddress localAddress = null;
    private ServerSocket mySkServer;

    private ClientModel clientModel;
    List<ClientModel> listOfClientModel;

    private String ipadress;
    private int portUsed;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    Scanner scanner;

    public Server(String ipadress, int portUsed) throws IOException, ClassNotFoundException, InterruptedException {
        this.ipadress = ipadress;
        this.portUsed = portUsed;

        //CREATION OF SERVER
        //AND WAITING FOR A CLIENT..
        createServer(ipadress,portUsed,mySkServer,srvSocket);
        loginCheck(srvSocket);


    }

    private void loginCheck(Socket srvSocket) throws IOException, ClassNotFoundException {
        // get the input stream from the connected socket
        String username;
        String password;

        srvSocket = mySkServer.accept();

        BufferedReader buffin = new BufferedReader(new InputStreamReader(srvSocket.getInputStream()));
        PrintWriter pout = new PrintWriter(srvSocket.getOutputStream(), true);

        while(isAuthentified()==false){
            pout.println("//USERNAME//");
            username = scanner.nextLine();
            pout.println("//PASSWORD//");
            password = scanner.nextLine();
            ClientModel clientTest = new ClientModel(username,password);
        }
        // create a DataInputStream so we can read data from it.
        System.out.println("[SERVER] : clients info received");
        System.out.println("Client infos :");
        System.out.println("username :"+ clientModel.getUsername());
        System.out.println();

    }

    private void createServer(String ipadress, int portUsed,ServerSocket mySkServer,Socket srvSocket) throws IOException, InterruptedException {
        mySkServer = new ServerSocket(portUsed, 10, InetAddress.getByName(ipadress));
        System.out.println("//-VSFY- ONLINE//");
        System.out.println("Default Timeout : " + mySkServer.getSoTimeout());
        System.out.println("Used IpAddress : " + mySkServer.getInetAddress());
        System.out.println("Listening to Port : " + mySkServer.getLocalPort());
        System.out.println("waiting..");




    }



}
