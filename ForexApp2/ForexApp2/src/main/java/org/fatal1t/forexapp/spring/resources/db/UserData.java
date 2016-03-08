/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.resources.db;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author Filip
 */
@Entity
@Table(schema = "forexdata", name="users")
public class UserData implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    
    @Column(name = "username", nullable = false)
    private final String username;
    
    public UserData()
    {
        this.username = "default";
    }

    public UserData(String user)
    {
        this.username = user;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString()
    {
        return "[ID:" + id + " username: " + username+ " ]";
    }
}
