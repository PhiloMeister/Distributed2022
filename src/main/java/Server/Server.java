package Server;


import Client.ListOfClientsUtils;
import Model.ClientModel;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;

public class Server {
    ListOfClientsUtils listOfClientsUtils = new ListOfClientsUtils();
    BufferedReader buffin;
    BufferedWriter bufferedWriter;
    PrintWriter pout;
    Scanner scannerIn = new Scanner(System.in);
    ClientModel clientTest;
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
        register()
        //check if the login given by client is good, if yes it let enter
        //loginOrRegister();


    }

    private void register() {
    }

    private void loginOrRegister() throws IOException, ClassNotFoundException, InterruptedException {
        int response = 0;

        while (response != 1 || response != 2) {
            pout.println(" Choose : (1) Login / (2) Register");

            response = Integer.parseInt(buffin.readLine());
            System.out.println("response : " + response);
            switch (response) {
                case 1:
                    System.out.println("here");
                    loginCheck();
                    break;
                case 2:
                    System.out.println("here2");
                    break;
            }
        }
    }

    private void loginCheck() throws IOException, ClassNotFoundException, InterruptedException {
        // get the input stream from the connected socket
        boolean isAuthentified = false;
        String username;
        String password;

        System.out.println("loginCheck");

        while (isAuthentified != true) {

            //getting username
            pout.println("//USERNAME// : ");

            username = buffin.readLine();
            System.out.println("username :" + username);

            pout.println("//PASSWORD// : ");

            password = buffin.readLine();

            System.out.println("password :" + password);
            //getting password


            //creating a object with both inputs given
            clientTest = new ClientModel(username, password);

            //checking if there is username & password matches
            for (ClientModel client : listOfClientsUtils.getListOfCLients()) {
                if (client.getUsername().equals(clientTest.getUsername())) {
                    if (client.getPassword().equals(clientTest.getPassword())) {
                        // terminate the while() loop server side
                        isAuthentified = true;
                        // terminate the while() loop in the client side
                        pout.println("true");
                        pout.flush();
                    }

                }
            }
            pout.println("false");
            pout.flush();

        }
        //if login is success
        pout.println("//SUCCESSFUL LOGIN//");
        pout.flush();
        System.out.println("[SERVER] : client connected");
        System.out.println("Client infos :");
        System.out.println("username :" + clientTest.getUsername());
        System.out.println();

    }

    private void createServer() throws IOException, InterruptedException {
        mySkServer = new ServerSocket(portUsed, 10, InetAddress.getByName(ipadress));
        System.out.println("//-VSFY- ONLINE//");
        System.out.println("Default Timeout : " + mySkServer.getSoTimeout());
        System.out.println("Used IpAddress : " + mySkServer.getInetAddress());
        System.out.println("Listening to Port : " + mySkServer.getLocalPort());
        System.out.println("waiting..");
        srvSocket = mySkServer.accept();
        buffin = new BufferedReader(new InputStreamReader(srvSocket.getInputStream()));
        pout = new PrintWriter(srvSocket.getOutputStream(), true);


    }


}
