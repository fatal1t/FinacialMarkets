    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.db.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.fatal1t.backend.forexbackend.db.Tick;
import org.fatal1t.forexapp.spring.api.eventdata.TickRecord;

/**
 *
 * @author fatal1t
 */
@Entity
@Table(name = "ticks_eurusd", schema = "forexdata")
public class EURUSDtick extends Tick implements Serializable{
    @Transient
    private static Long symbolId = 3915L;
    @Id
    @GeneratedValue(generator = "ticks_eur_usd", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ticks_eur_usd", sequenceName = "forexdata.ticks_eur_usd_ids")
    @Column(name = "id")
    protected Long id;
    
    @Override
    public String toString()
    {
        return "EURUSD tick in " + this.time.toString();
    }

    public EURUSDtick() {
    }

    public EURUSDtick(TickRecord r) {
        super(r);
    }

    
    public Long getId() {
        return id;
    }

   
    public void setId(Long id) {
        this.id = id;
    }
    
    
}
