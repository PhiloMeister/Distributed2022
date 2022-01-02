package App;

import Model.ClientModel;
import Server.Server;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Server server = new Server("0.0.0.0",1234);

    }
}
