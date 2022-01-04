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
    private String response = "STAND-BY";
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public Server(String ipadress, int portUsed) throws IOException, ClassNotFoundException, InterruptedException {
        this.ipadress = ipadress;
        this.portUsed = portUsed;

        //CREATION OF SERVER
        //AND WAITING FOR A CLIENT..

        createServer();
        receiveClient();
        waitForChoice();
        
    }

    private void waitForChoice() throws IOException {
        System.out.println("response"+response);
        while (!response.equalsIgnoreCase("END")) {
            switch (response) {
                case "LIST ALL":
                    System.out.println("LIST ALL SECTION");
                    listAll();
                    response = buffin.readLine();
                    break;
                case "LIST MUSICS":
                    System.out.println("list section");
                    //TODO A redefinir car list all fait deja ceci
                    response = buffin.readLine();
                    break;
                case "HELP":
                    System.out.println("HELP SECTION");
                    response = buffin.readLine();
                    break;
                case "FIND":
                    System.out.println("FIND SECTION");
                    response = buffin.readLine();
                    break;
                default:
                    System.out.println("DEFAULT");
                    response = buffin.readLine();
                    break;
            }

        }

    }

    private void listAll() throws IOException {
     objectOutputStream.writeObject(listOfClientsUtils);
     objectOutputStream.flush();
        System.out.println("[SERVER] : list sent");
    }

    private void receiveClient() throws IOException, ClassNotFoundException {
        clientModel = (ClientModel) objectInputStream.readObject();
        System.out.println("client :"+ clientModel.getUsername()+" joined");
        listOfClientsUtils.add(clientModel);
    }


    private void createServer() throws IOException, InterruptedException {
        mySkServer = new ServerSocket(portUsed, 10, InetAddress.getByName(ipadress));
        System.out.println("//-VSFY- ONLINE//");
        System.out.println("Default Timeout : " + mySkServer.getSoTimeout());
        System.out.println("Used IpAddress : " + mySkServer.getInetAddress());
        System.out.println("Listening to Port : " + mySkServer.getLocalPort());
        System.out.println("waiting..");
        srvSocket = mySkServer.accept();
        pout = new PrintWriter(srvSocket.getOutputStream(), true);
        buffin = new BufferedReader(new InputStreamReader(srvSocket.getInputStream()));

        //Object transfering
        objectOutputStream = new ObjectOutputStream(srvSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(srvSocket.getInputStream());


    }



}
