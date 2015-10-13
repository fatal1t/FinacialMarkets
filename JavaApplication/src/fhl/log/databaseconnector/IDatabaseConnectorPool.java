/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.log.databaseconnector;

/**
 *
 * @author Filip
 * @param <T>
 */
public interface IDatabaseConnectorPool<T> {
    public T getInstance();    
    public void returnInstance(T instance);
    
}
 