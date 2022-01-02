package App;

import Client.Client;
import Model.ClientModel;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) throws IOException {
        ClientModel clientModel = new ClientModel("Abdullah","1234","0.0.0.0",1234);
        Client client = new Client(clientModel);
    }
}
