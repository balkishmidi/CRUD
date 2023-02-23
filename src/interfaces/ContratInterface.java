/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Contrat;
import java.util.List;

/**
 *
 * @author BKHmidi
 */
public interface ContratInterface  <C> {
      void AddContrat(C c);
      void UpdateContrat(C c);
      void DeleteContrat(int id);
      public List<C> contratList();
      public List <C>searchContrats(String search) ;

}
