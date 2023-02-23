/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author BKHmidi
 */
public class Message {

    private int id_message;
    private String expediteur;
    private String destinataire;
    private Timestamp  date_message;
    private String contenu;

    public Message() {
    }

    public Message(int id_message, String expediteur, String destinataire, Timestamp  date_message, String contenu) {
        this.id_message = id_message;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.date_message = date_message;
        this.contenu = contenu;
    }

    public Message(String expediteur, String destinataire, String contenu) {
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.contenu = contenu;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public Timestamp  getDate_message() {
        return date_message;
    }

    public void setDate_message(Timestamp  date_message) {
        this.date_message = date_message;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Message{" + "id_message=" + id_message + ", expediteur=" + expediteur + ", destinataire=" + destinataire + ", date_message=" + date_message + ", contenu=" + contenu + '}';
    }

}
