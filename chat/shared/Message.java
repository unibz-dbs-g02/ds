package chat.shared;

import java.util.HashMap;

public class Message {
  private String raw;
  private String enc;
  private HashMap<String, String> f = new HashMap<>();

  private Message() {}

  public static Message Decoded(String msg) {
    Message m = new Message();
    m.enc = msg;

    return m;
  }

  public static Message Encoded(String msg) {
    Message m = new Message();
    m.raw = msg;

    return m;
  }

  public String encode() {
    StringBuilder b = new StringBuilder();

    for(String k : f.keySet()) {
      b.append(k + "[" + f.get(k) + "]");
    }

    return b.toString();
  }

  public HashMap<String, String> decode(String msg) {
    for(int i = 0; i < msg.length(); ) {

      StringBuilder b = new StringBuilder();
      while(true) {
        if(msg.charAt(i) == '[') { i++; break; }
        
        b.append(msg.charAt(i));
        i++;
      }
      
      StringBuilder v = new StringBuilder();   
      while(true) {
        if(msg.charAt(i) == ']') { i++; break; }
     
        v.append(msg.charAt(i));
        i++;
      }
      
      f.put(b.toString(), v.toString());
    }
    
    return f;
  }
}
