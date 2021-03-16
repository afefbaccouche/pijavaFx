/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.IServices;

import Don.entities.BesoinArgent;
import java.util.List;

/**
 *
 * @author jacem
 */
public interface IBesoinArgent {
    public void ajouterBesoinArgent(BesoinArgent o);

    public void supprimerBesoinArgent(BesoinArgent o);

    public void modifierBesoinArgent(String object, Object obj, int id);

    public List<BesoinArgent> consulterBesoinArgent();
}
