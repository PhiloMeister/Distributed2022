package Client;

import Model.ClientModel;
import Model.SongModel;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner scannerIn = new Scanner(System.in);
    String response = "HELP";
    private ListOfClientsUtils listOfClientsUtils;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ServerSocket passivSocket;
    private ClientModel clientModel;
    private Socket clientSocket;
    private PrintWriter pout;
    private BufferedReader buffin;
    private ListOfMusicUtils listOfMusicUtils;

    public Client() throws IOException, ClassNotFoundException {
        registerClient();
        startConnection();
        choiceClient();
    }

    private void registerClient() throws IOException {

        System.out.print("IPADRESS : ");
        String ipadress = scannerIn.nextLine();

        System.out.print("PORT : ");
        int port = Integer.parseInt(scannerIn.nextLine());
        //obligé de parser car il saute des lignes sinon

        System.out.print("USERNAME :");
        String username = scannerIn.nextLine();


        System.out.println("Getting all your musics :");
        listOfMusicUtils = new ListOfMusicUtils();
        for (SongModel song : listOfMusicUtils.getListOfMusics()) {
            System.out.println("Song :" + song.getFilename() + " Added.");

        }
        clientModel = new ClientModel(username, ipadress, port);
        clientModel.setListOfMusicUtils(listOfMusicUtils);
    }

    private void choiceClient() throws IOException, ClassNotFoundException {

        while (!response.equalsIgnoreCase("END")) {
            switch (response) {
                case "LIST ALL":
                    listAll();
                    break;
                case "LIST MUSICS":
                    listMusicsClient();
                    break;
                case "HELP":
                    helpClient();
                    break;
                case "FIND":
                    findClient();
                    break;
                case "END ":
                    endClient();
                    break;
                default:
                    System.out.println("Invalid command");
                    System.out.println("Type HELP for all commands");
                    System.out.println("///////////////////////////////////");
                    response = scannerIn.nextLine();
            }

        }
    }

    private void endClient() {
        System.out.println("Bye");
        System.out.println("///////////////////////////////////");
        response = "END";
        pout.write(response);
        pout.flush();
    }

    private void listMusicsClient() {
        System.out.println("list musics..");
        response = scannerIn.nextLine();
        pout.write(response);
        pout.flush();
    }

    private void helpClient() {
        System.out.println("Available commands :");
        System.out.println("LIST ALL -> List all online users");
        System.out.println("LIST MUSICS -> List all musics from users");
        System.out.println("FIND <TheMusic> -> Find a specific music");
        System.out.println("END -> Disconnect from server / client");
        System.out.println("///////////////////////////////////");
        response = scannerIn.nextLine();
        System.out.println("sending "+response);
        pout.println(response);
        pout.flush();
    }

    private void findClient() {
        System.out.println("find music..");
        response = scannerIn.nextLine();
        pout.write(response);
        pout.flush();
    }

    private void listAll() throws IOException, ClassNotFoundException {

        listOfClientsUtils = new ListOfClientsUtils();
        listOfClientsUtils = (ListOfClientsUtils) objectInputStream.readObject();

        for (ClientModel client : listOfClientsUtils.getListOfCLients()) {
            System.out.println("Username : " + client.getUsername());
            for (int i = 0; i < client.getListOfMusicUtils().size(); i++) { ;
                System.out.println("Song : "+client.getListOfMusicUtils().get(i).getFilename());
            }
        }
        response = scannerIn.nextLine();
        pout.write(response);
        pout.flush();
    }

    private void startConnection() throws IOException {
        clientSocket = new Socket(InetAddress.getByName(clientModel.getIpAdress()), clientModel.getPortUsed());
        System.out.println("Connected to server");
        buffin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        pout = new PrintWriter(clientSocket.getOutputStream(), true);
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        //give the client
        objectOutputStream.writeObject(clientModel);
        objectOutputStream.flush();
        System.out.println("profile sent.");
    }


}
