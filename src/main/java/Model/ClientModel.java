package Model;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientModel {
    private Socket clientSocket = null;
    private ClientModel clientModel;
    private String username;
    private String password;
    private String mailAdress;
    private String IpAdress;
    private int portUsed;


    //registered client
    public ClientModel(String username, String password, String ipAdress, int portUsed) throws IOException {
        this.username = username;
        this.password = password;
        this.IpAdress = ipAdress;
        this.portUsed = portUsed;
        //Method used for running a connection
    }
    //used when trying to login
    public ClientModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getPortUsed() {
        return portUsed;
    }

    public void setPortUsed(int portUsed) {
        this.portUsed = portUsed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public String getIpAdress() {
        return IpAdress;
    }

    public void setIpAdress(String ipAdress) {
        IpAdress = ipAdress;
    }
}
