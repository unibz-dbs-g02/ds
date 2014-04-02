package chat.shared;

import java.net.*;
import java.util.*;
import chat.*;

public class ServerConnection extends Connection {
  private Server ss;

  public ServerConnection(Socket s, Server ss) {
    super(s);
    this.ss = ss;
  }

  @Override
  public void run() {
    while(true) {
      String msg = this.receive();
      ss.addMsg(msg);
      for(ServerConnection c : ss.getConns()) {
        if(c != this) {
          c.send(msg);
        }
      } 
    }
  }
}
