/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Contrat;
import entities.Message;
import interfaces.MessageInterface;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BKHmidi
 */
public class MessageCRUD implements MessageInterface<Message> {

    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";

    @Override
    public void AddMessage(Message m) {

        LocalDateTime date = m.getDate_message().toLocalDateTime();
            
    // Format the LocalDateTime object using a DateTimeFormatter
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDate = date.format(formatter);
    
    String sql = "INSERT INTO message (expediteur, destinataire, contenu, date_message) VALUES (?, ?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, m.getExpediteur());
        pstmt.setString(2, m.getDestinataire());
        pstmt.setString(3, m.getContenu());
            
        pstmt.setTimestamp(4, new Timestamp(m.getDate_message().getTime()));
        pstmt.executeUpdate();
        System.out.println("message ajouté");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    }

    @Override
    public void UpdateMessage(Message m) {
        String sql = "Update INTO Message (contenu) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.getContenu());

            pstmt.executeUpdate();
            System.out.println("message modifié");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
     public Message ReadMessage(int id_message) {
        String sql = "SELECT * FROM Message WHERE id_message = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_message);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Message m = new Message();
                m.setId_message(rs.getInt("id_message"));
                m.setExpediteur(rs.getString("expediteur"));
                m.setDestinataire(rs.getString("destinataire"));
                m.setDate_message(rs.getTimestamp("date_message"));
                m.setContenu(rs.getString("contenu"));
                          
                return m;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void DeleteMessage(int id_message){
        String sql = "DELETE FROM Message WHERE id_message = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_message);
            pstmt.executeUpdate();
            System.out.println("message supprimé");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }    

    @Override
    public List<Message> MessageList() {
          List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Message";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Message m = new Message();
                m.setId_message(rs.getInt("id_message"));
                m.setExpediteur(rs.getString("expediteur"));
                m.setDestinataire(rs.getString("destinataire"));
                m.setDate_message(rs.getTimestamp ("date_message"));
                
                m.setContenu(rs.getString("contenu"));
                    LocalDateTime date = rs.getTimestamp("date_message").toLocalDateTime();
            
            // Format the LocalDateTime object using a DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = date.format(formatter);
            m.setDate_message(Timestamp.valueOf(date));
                messages.add(m);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    
    }
      public ObservableList<Message> getDataMsg() {
     ObservableList<Message> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Message";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Message c = new Message();
               c.setId_message(rs.getInt("id_message"));
                c.setContenu(rs.getString("contenu"));
                c.setDestinataire(rs.getString("destinataire"));
                c.setExpediteur(rs.getString("expediteur"));
                 // Convert the Timestamp object to LocalDateTime object
            LocalDateTime date = rs.getTimestamp("date_message").toLocalDateTime();
            
            // Format the LocalDateTime object using a DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = date.format(formatter);
            c.setDate_message(Timestamp.valueOf(date));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

}
