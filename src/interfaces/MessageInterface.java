/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author BKHmidi
 */
public interface MessageInterface <Message> {
     void AddMessage(Message m);
      void UpdateMessage(Message m);
      public Message ReadMessage(int id_message);
       void DeleteMessage(int id_message);
      public List<Message> MessageList();
      
    
}
