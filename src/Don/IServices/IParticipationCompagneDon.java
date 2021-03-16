/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.IServices;

import Don.entities.ParticipationCompagneDon;
import java.util.List;

/**
 *
 * @author jacem
 */
public interface IParticipationCompagneDon {
    
    public void ajouterParticipationCompagneDon(ParticipationCompagneDon o);

    public void supprimerParticipationCompagneDon(ParticipationCompagneDon o);

    public void modifierParticipationCompagneDon(int id);

    public List<ParticipationCompagneDon> consulterParticipationCompagneDon();
}
