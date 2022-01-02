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
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    Scanner scannerIn = new Scanner(System.in);
    private Socket srvSocket;
    private InetAddress localAddress = null;
    private ServerSocket mySkServer;
    private ClientModel clientModel;
    private String ipadress;
    private int portUsed;
    ClientModel clientTest;
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

            //getting username
            username = buffin.readLine();
            System.out.println("username :" + username);
            //getting password
            password = buffin.readLine();
            System.out.println("password :" + password);
            //creating a object with both inputs given
            clientTest = new ClientModel(username, password);

            //checking if there is username & password matches
            for (ClientModel client : listOfClientsUtils.getListOfCLients()) {
                System.out.println("loop");
                if (client.getUsername().equals(clientTest.getUsername() )) {
                    if (client.getPassword().equals(clientTest.getPassword())){
                        isAuthentified = true;
                        System.out.println("good");
                        pout.println("true");
                    }

                }
            }
                // if not print this
            if (isAuthentified ==false){
                System.out.println("! WRONG PASSWORD OR LOGIN !");
                System.out.println();
            }

        }
        //if login is success
        pout.println("//SUCCESSFUL LOGIN//");
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


    }


}
