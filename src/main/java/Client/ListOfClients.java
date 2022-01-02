package Client;

import Model.ClientModel;

import java.io.IOException;
import java.util.HashMap;

public class ListOfClients {

    HashMap<Integer,ClientModel> listOfClient = new HashMap<Integer, ClientModel>();

    public ListOfClients() throws IOException {
        ClientModel client1 = new ClientModel("PhiloMeister","IsYourMomFree","0.0.0.0",1234);
        ClientModel client2 = new ClientModel("Nicolas","Nicolas","0.0.0.0",1234);
        ClientModel client3 = new ClientModel("JesseLivermore","JesseLivermore","0.0.0.0",1234);
        ClientModel client4 = new ClientModel("MorganHousel","MorganHousel","0.0.0.0",1234);
        listOfClient.put(1,client1);
        listOfClient.put(2,client2);
        listOfClient.put(3,client3);
        listOfClient.put(4,client4);

        }

    public HashMap<Integer, ClientModel> getListOfClient() {
        return listOfClient;
    }

    public void setListOfClient(HashMap<Integer, ClientModel> listOfClient) {
        this.listOfClient = listOfClient;
    }

    public void addToListOfClient(ClientModel clientModel) {


        }
    }
    

