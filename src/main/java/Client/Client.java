package Client;

import Model.ClientModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private ClientModel clientModel;
    private Socket clientSocket;
    Scanner scannerIn = new Scanner(System.in);

    public Client(ClientModel clientModel) throws IOException {
        this.clientModel = clientModel;


        loginOrRegister();

    }

    private void loginOrRegister() throws IOException {
        int response = 0;

        while(response!=1 || response!=2) {
            System.out.println(" (1) Login, (2) register");
            response = scannerIn.nextInt();
            switch (response) {
                case 1:
                    startConnection();
                    clientlogincheck();
                    break;
                case 2:
                    clientRegister();
                    startConnection();
                    clientlogincheck();
                    break;

            }
        }
    }

    private void clientRegister() {
        String usernameRegister;
        String passwordRegister;
        System.out.println("CREATE A ACCOUNT");
        System.out.println("//NEW USERNAME//");

        System.out.println("//NEW USERNAME//");
    }

    private void clientlogincheck() throws IOException {
        String isAuthentified = "false";
        String username;
        String password;
        BufferedReader buffin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter pout = new PrintWriter(clientSocket.getOutputStream(), true);
        while(isAuthentified.equals("false")) {
            System.out.println("//USERNAME// : ");
            username = scannerIn.nextLine();
            pout.println(username);
            pout.flush();

            System.out.println("//PASSWORD// : ");
            password = scannerIn.nextLine();
            pout.println(password);
            pout.flush();
            isAuthentified = buffin.readLine();
            if (isAuthentified.equals("false")){
                System.out.println("! WRONG PASSWORD OR LOGIN !");
                System.out.println();
            }
        }
        System.out.println("You are logged");

    }

    private void startConnection() throws IOException {
        clientSocket = new Socket(InetAddress.getByName(clientModel.getIpAdress()),clientModel.getPortUsed());
       // BufferedReader Buffin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
       // PrintWriter Pout = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("//WELCOME TO VSFY//");
    }


}
