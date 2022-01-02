package Client;

import Model.ClientModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private ClientModel clientModel;
    private Socket clientSocket;

    public Client(ClientModel clientModel) throws IOException {
        this.clientModel = clientModel;

        startConnection(clientModel);
    }

    private void startConnection(ClientModel clientModel) throws IOException {
        clientSocket = new Socket(InetAddress.getByName(clientModel.getIpAdress()),clientModel.getPortUsed());
        BufferedReader Buffin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter Pout = new PrintWriter(clientSocket.getOutputStream(), true);
    }


}
