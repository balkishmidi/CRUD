/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Conducteur;
import interfaces.ConducteurInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BKHmidi
 */
public class ConducteurCRUD  implements ConducteurInterface<Conducteur>{
      private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";

    public void AddConducteur(Conducteur cl) {
        String sql = "INSERT INTO conducteur(nom_conducteur, prenom_conducteur, cin_conducteur, ville_conducteur, telephone_conducteur, email_conducteur, mdp_conducteur,type_de_permis,image_conducteur) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cl.getNom_conducteur());
            pstmt.setString(2, cl.getPrenom_conducteur());
            pstmt.setInt(3, cl.getCin_conducteur());
            pstmt.setString(4, cl.getVille_conducteur());
            pstmt.setInt(5, cl.getTelephone_conducteur());
            pstmt.setString(6, cl.getEmail_conducteur());
            pstmt.setString(7, cl.getMdp_conducteur());
            pstmt.setString(8, cl.getType_de_permis());
            pstmt.setString(9, cl.getImage_conducteur());
            pstmt.executeUpdate();
            System.out.println("Le conducteur a été ajouté avec succès !");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
    
    
    public Conducteur getConducteurById(int id) {
        String sql = "SELECT * FROM conducteur WHERE id_conducteur = ?";
        Conducteur cd = null;
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cd = new Conducteur();
                cd.setId_conducteur(rs.getInt("id_conducteur"));
                cd.setNom_conducteur(rs.getString("nom_conducteur"));
                cd.setPrenom_conducteur(rs.getString("prenom_conducteur"));
                cd.setTelephone_conducteur(rs.getInt("telephone_conducteur"));
                cd.setEmail_conducteur(rs.getString("email_conducteur"));
                cd.setVille_conducteur(rs.getString("ville_conducteur"));
                cd.setMdp_conducteur(rs.getString("mdp_conducteur"));
                cd.setType_de_permis(rs.getString("type_de_permis"));
                cd.setImage_conducteur(rs.getString("image_conducteur"));
                System.out.println(cd);
            } else {
                System.out.println("Conducteur not found with id: " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cd;
    }
    
    
    
    //UpDate

    @Override
    public void UpDateConducteur(Conducteur cl,int id) {
        String sql = "UPDATE conducteur SET nom_conducteur = ?, prenom_conducteur = ?, cin_conducteur = ?, ville_conducteur = ?, telephone_conducteur = ?, email_conducteur = ?, mdp_conducteur = ?,type_de_permis = ?,image_conducteur=? WHERE id_conducteur = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cl.getNom_conducteur());
            pstmt.setString(2, cl.getPrenom_conducteur());
            pstmt.setInt(3, cl.getCin_conducteur());
            pstmt.setString(4, cl.getVille_conducteur());
            pstmt.setInt(5, cl.getTelephone_conducteur());
            pstmt.setString(6, cl.getEmail_conducteur());
            pstmt.setString(7, cl.getMdp_conducteur());
            pstmt.setString(8, cl.getType_de_permis());
            pstmt.setString(9, cl.getImage_conducteur());
            pstmt.setInt(10,id);

            pstmt.executeUpdate();
            System.out.println("Le conducteur a été modifié ");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void DeleteConducteur(int id_conducteur) {
        String sql = "DELETE FROM conducteur WHERE id_conducteur = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_conducteur);
            pstmt.executeUpdate();
            System.out.println(" Le conducteur supprimé avec succès");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Conducteur> ConducteurList() {

        List<Conducteur> Conducteur = new ArrayList<>();
        String sql = "SELECT * FROM conducteur";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Conducteur cd = new Conducteur();
                cd.setId_conducteur(rs.getInt("id_conducteur"));
                cd.setNom_conducteur(rs.getString("nom_conducteur"));
                cd.setPrenom_conducteur(rs.getString("prenom_conducteur"));
                cd.setTelephone_conducteur(rs.getInt("telephone_conducteur"));
                cd.setEmail_conducteur(rs.getString("email_conducteur"));
                cd.setVille_conducteur(rs.getString("ville_conducteur"));
                cd.setMdp_conducteur(rs.getString("mdp_conducteur"));
                cd.setType_de_permis(rs.getString("type_de_permis"));
                cd.setImage_conducteur(rs.getString("image_conducteur"));
                Conducteur.add(cd);
                System.out.println(cd);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Conducteur;
    }
    

}

