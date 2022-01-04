package Client;

import Model.ClientModel;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    Scanner scannerIn = new Scanner(System.in);
    private ClientModel clientModel;
    private Socket clientSocket;
    private PrintWriter pout;
    private BufferedReader buffin;

    public Client(ClientModel clientModel) throws IOException {
        this.clientModel = clientModel;
        startConnection();
        //loginOrRegister();

    }

   /* private void loginOrRegister() throws IOException {
        int response = 0;

        while (response != 1 || response != 2) {
            //choose etc..
            System.out.println(buffin.readLine());
            response = scannerIn.nextInt();
            pout.println(response);
            pout.flush();
            switch (response) {
                case 1:
                    clientlogincheck();
                    break;
                case 2:
                    clientRegister();
                    clientlogincheck();
                    break;
            }
        }
    }*/

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

        while (isAuthentified.equals("false")) {
            System.out.println(buffin.readLine());
            username = scannerIn.nextLine();
            pout.println(username);
            pout.flush();

            password = scannerIn.nextLine();
            pout.println(password);
            pout.flush();
            isAuthentified = buffin.readLine();
            if (isAuthentified.equals("false")) {
                System.out.println("! WRONG PASSWORD OR LOGIN !");
                System.out.println();
            }
        }


    }

    private void startConnection() throws IOException {
        clientSocket = new Socket(InetAddress.getByName(clientModel.getIpAdress()), clientModel.getPortUsed());
        System.out.println("//WELCOME TO VSFY//");
        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        buffin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        pout = new PrintWriter(clientSocket.getOutputStream(), true);
    }


}
