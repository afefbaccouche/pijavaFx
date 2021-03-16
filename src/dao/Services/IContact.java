/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Services;

import java.util.List;

/**
 *
 * @author amani
 */
public interface IContact <T> {
      void add(T t);
    void delete(T t);
    void update(T t);
    List<T> getAll();
    T getById(int id);
}
