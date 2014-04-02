package chat.shared;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import chat.*;

public class Connection extends Thread {
  public ObjectOutputStream o; 
  public ObjectInputStream w;
  private ExecutorService ex = Executors.newCachedThreadPool();

  public Connection(Socket s) {
    try {
      this.o = new ObjectOutputStream(s.getOutputStream());
      this.w = new ObjectInputStream(s.getInputStream());
    } catch(Exception e) { e.printStackTrace(); }
  }

  public void send(final String msg) {
    if(msg == null) { return; }  
    try {
      o.writeObject(msg);   
    } catch(Exception e) { e.printStackTrace(); }
  }

  public String receive() {
    try {
      return (String) w.readObject();
    } catch(Exception e) { e.printStackTrace(); return null; }
  }
}
