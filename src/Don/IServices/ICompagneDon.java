/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.IServices;

import Don.entities.CompagneDon;
import java.util.List;

/**
 *
 * @author jacem
 */
public interface ICompagneDon {
    public void ajouterCompagneDon(CompagneDon o);

    public void supprimerCompagneDon(CompagneDon o);

    public void modifierCompagneDon(int id);

    public List<CompagneDon> consulterCompagneDon();
}
