/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Baklouti
 */
public interface IEventdao<T> {
    public void add(T t);
    public boolean update(T t);
    public void delete(T t);
    public List<T> displayAll();
    public T displayById(int id);
    
    
}
