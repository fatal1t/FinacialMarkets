/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.rest;

/**
 *
 * @author fatal1t
 */
    
public interface SimulationInterface
{
    public void start();
    public void stopSimulation();
    public boolean getStatus();
}