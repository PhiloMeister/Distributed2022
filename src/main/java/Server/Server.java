package Server;


import Client.ListOfClients;
import Model.ClientModel;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;

public class Server {
    ListOfClients listOfClients = new ListOfClients();
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    Scanner scannerIn = new Scanner(System.in);
    private Socket srvSocket;
    private InetAddress localAddress = null;
    private ServerSocket mySkServer;
    private ClientModel clientModel;
    private String ipadress;
    private int portUsed;

    public Server(String ipadress, int portUsed) throws IOException, ClassNotFoundException, InterruptedException {
        this.ipadress = ipadress;
        this.portUsed = portUsed;

        //CREATION OF SERVER
        //AND WAITING FOR A CLIENT..
        createServer();
        loginCheck();


    }

    private void loginCheck() throws IOException, ClassNotFoundException {
        // get the input stream from the connected socket
        boolean isAuthentified = false;
        String username;
        String password;

        srvSocket = mySkServer.accept();
        System.out.println("Client connected");

        BufferedReader buffin = new BufferedReader(new InputStreamReader(srvSocket.getInputStream()));
        PrintWriter pout = new PrintWriter(srvSocket.getOutputStream(), true);

        while (isAuthentified != true) {
            pout.println("//USERNAME//");
            username = scannerIn.nextLine();
            pout.println("//PASSWORD//");
            password = scannerIn.nextLine();
            ClientModel clientTest = new ClientModel(username, password);


            for (ClientModel client : listOfClients.getListOfCLients()) {
                if (client.getUsername() == clientTest.getUsername() && client.getPassword() == clientTest.getPassword()) {
                    isAuthentified = true;
                } else {
                    System.out.println("!WRONG PASSWORD OR LOGIN!");
                }
            }

        }
        // create a DataInputStream so we can read data from it.
        System.out.println("[SERVER] : clients info received");
        System.out.println("Client infos :");
        System.out.println("username :" + clientModel.getUsername());
        System.out.println();

    }

    private void createServer() throws IOException, InterruptedException {
        mySkServer = new ServerSocket(portUsed, 10, InetAddress.getByName(ipadress));
        System.out.println("//-VSFY- ONLINE//");
        System.out.println("Default Timeout : " + mySkServer.getSoTimeout());
        System.out.println("Used IpAddress : " + mySkServer.getInetAddress());
        System.out.println("Listening to Port : " + mySkServer.getLocalPort());
        System.out.println("waiting..");


    }


}
