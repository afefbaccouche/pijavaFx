/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Consommation;

import java.util.List;

/**
 *
 * @author soumaya ch
 */
public interface IService <T>{
    
    void add(T t);
    void delete(T t);
    void update(T t);
    public List<T> getAll();
   // T getById(int id);
    boolean Chercher(T t);
    
}
