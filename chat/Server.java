package chat;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import chat.shared.*;

public class Server extends Thread {
  
  private ArrayList<ServerConnection> conns = new ArrayList<>();
  private ArrayList<String> history = new ArrayList<>();
  private int port;
  
  public static void main(String[] args) {
    Server c = new Server(4001);
    c.start();
 
    ClientConnection c1 = ClientConnection.getClient("127.0.0.1", 4001,1);
    ClientConnection c2 = ClientConnection.getClient("127.0.0.1", 4001,2);
    c1.start();
    c2.start();
    c1.send("ONE SAYS HAI");
    c2.send("TWO SAYS HAI TO YOU TOO");

    ClientConnection c3 = ClientConnection.getClient("127.0.0.1", 4001,3);
    c3.start();
    c3.send("THREE SAYS NIGGAS");

  }

  public Server(int port) {
    this.port = port;
  }
  
  public void run() {
    ServerSocket ss;
    try {
      ss = new ServerSocket(port); 
      System.out.println("READY TO ACCEPT!");
      while(true) {
        try {
          Socket s = ss.accept();
          
          ServerConnection c = new ServerConnection(s, this);
          for(String m : getHistory()) {
            c.send(m);
          }
          c.start();  

          conns.add(c);
        } catch(Exception e) {
          e.printStackTrace();
          return;
        }
      }

    } catch(Exception e) {
      e.printStackTrace();
      return;
    }

  } 

  public ArrayList<ServerConnection> getConns() {
    return conns;
  }

  public ArrayList<String> getHistory() {
    return history;
  }

  public void addMsg(String msg) {
    history.add(msg);
  }
}
