package Client;

import Model.ClientModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListOfClients {


   private CopyOnWriteArrayList <ClientModel> listOfCLients = new CopyOnWriteArrayList<ClientModel>();

    public ListOfClients() throws IOException {
        ClientModel client1 = new ClientModel("PhiloMeister","IsYourMomFree","0.0.0.0",1234);
        ClientModel client2 = new ClientModel("Nicolas","Nicolas","0.0.0.0",1234);
        ClientModel client3 = new ClientModel("JesseLivermore","JesseLivermore","0.0.0.0",1234);
        ClientModel client4 = new ClientModel("MorganHousel","MorganHousel","0.0.0.0",1234);
        listOfCLients.add(client1);
        listOfCLients.add(client2);
        listOfCLients.add(client3);
        listOfCLients.add(client4);

    }

    public CopyOnWriteArrayList<ClientModel> getListOfCLients() {
        return listOfCLients;
    }

    public void setListOfCLients(CopyOnWriteArrayList<ClientModel> listOfCLients) {
        this.listOfCLients = listOfCLients;
    }
    public ClientModel getClient(int index) {
        return listOfCLients.get(index);
    }

}
    

