/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testxpress;


import entities.Contrat;
import entities.Messages;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import services.ContratCRUD;
import services.MessageCRUD;

/**
 *
 * @author BKHmidi
 */
public class Testxpress {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        // Add a new Contrat
        Contrat c = new Contrat();
        c.setId_conducteur(4);
        c.setId_admin(1);
        c.setDate_debut(Date.valueOf(LocalDate.of(2023, 2, 15))); // set the start date to February 15, 2023
        c.setDate_fin(Date.valueOf(LocalDate.of(2023, 2, 15)));
        c.setPrix(1000);
        //c.setStatut(en cours);
       ContratCRUD pcd = new ContratCRUD();
       pcd.AddContrat(c);
       // System.out.println(pcd.contratList());
   //pcd.DeleteContrat(9);
//add a new message
Messages m = new Messages();
 m.setId_conducteur(4);
 m.setId_client(8);
m.setContenu("je vous attent ici");
MessageCRUD msg= new MessageCRUD();
//msg.AddMessage(m);


//System.out.println(msg.ReadMessage(2));
//msg.DeleteMessage(44);
//System.out.println(msg.MessageList());





    

    
}}
