package chat.shared;

import chat.*;
import java.net.*;

public class ClientConnection extends Connection {
  private int id; 
  public static ClientConnection getClient(String host, int port, int id) {
    try {
      return new ClientConnection(new Socket(host, port), id);
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
 
  public ClientConnection(Socket s, int id) {
    super(s);   
    this.id = id;
  }

  @Override
  public void run() {
    while(true) {
      String msg = this.receive();
      System.out.println(id +  " " + msg);
    }
  }
}
