package Server;

import Model.ClientModel;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

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

    public Server(String ipadress, int portUsed) throws IOException, ClassNotFoundException, InterruptedException {
        this.ipadress = ipadress;
        this.portUsed = portUsed;

        //CREATION OF SERVER
        //AND WAITING FOR A CLIENT..
        createServer(ipadress,portUsed,mySkServer,srvSocket);
       // loginCheck(srvSocket);


    }

    private void loginCheck(Socket srvSocket) throws IOException, ClassNotFoundException {
        // get the input stream from the connected socket
        System.out.println("1");
        InputStream inputStream = srvSocket.getInputStream();
        // create a DataInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        System.out.println("2");
        //The client informations are stored in "theClient"
        // client = (Client) objectInputStream.readObject();

        System.out.println("[SERVER] : clients info received");
        System.out.println("Client infos :");
        System.out.println("username :"+ clientModel.getUsername());
        System.out.println();

    }

    private void createServer(String ipadress, int portUsed,ServerSocket mySkServer,Socket srvSocket) throws IOException, InterruptedException {
        mySkServer = new ServerSocket(portUsed, 10, InetAddress.getByName(ipadress));
        System.out.println("//-VSFY-//");
        System.out.println("Default Timeout : " + mySkServer.getSoTimeout());
        System.out.println("Used IpAddress : " + mySkServer.getInetAddress());
        System.out.println("Listening to Port : " + mySkServer.getLocalPort());
        System.out.println("waiting..");

        srvSocket = mySkServer.accept();
        System.out.println("connection request received");
        Thread.sleep(3000000);


    }



}
