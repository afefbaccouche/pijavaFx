/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.IServices;

import Don.entities.BesoinArgent;
import Don.entities.BesoinObjet;
import java.util.List;

/**
 *
 * @author jacem
 */
public interface IBesoinObjet {

    public void ajouterBesoinObjet(BesoinObjet o);

    public void supprimerBesoinObjet(BesoinObjet o);

    public void modifierBesoinObjet(int id);

    public List<BesoinObjet> consulterBesoinObjet();
}
