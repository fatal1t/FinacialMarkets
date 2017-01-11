/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.engine.configuration;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author fatal1t
 */
/*
 * 
 * @author fatal1t (CREATE SQL
  id bigint NOT NULL DEFAULT nextval('configuration.eplstatementsids'::regclass),
    statement_name varchar(100),
    epl_statement text,
    description varchar(100),
    is_used boolean,
    load_on_statup boolean,

 */

@Entity
@Table(schema = "configuration", name = "eplstatements", indexes = {
    @Index(name= "eplstatements_name_index", columnList = "epl_statement", unique = true)
})
public class EplStatement implements Serializable {
    @Id    
    @GeneratedValue(generator = "elpstatementids", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "elpstatementids", sequenceName = "configuration.eplstatementsids")
    @Column(name = "id")
    private Long id;
    
    @Column(name = "statement_name")
    private String statementName;
    
    @Column(name ="epl_statement",columnDefinition = "text")
    private String eplStatement;
    
    @Column(name = "description")
    private String description;
        
    @Column(name= "is_used")
    private boolean isUsed;
    
    @Column(name = "load_on_statup")
    private boolean loadOnStartUp;
    
    @Column(name = "is_main_rule")
    private boolean isMainRule;
    
    @Column(name="is_simulation")
    private boolean isSimulation;
    
    @Column(name = "is_realtime")
    private boolean isRealTime;

    public EplStatement() {
    }

    public EplStatement(String statementName, String eplStatement, String description, boolean isUsed, boolean loadOnStartUp, boolean isMainRule, boolean isSimulation, boolean isRealtime) {
        this.statementName = statementName;
        this.eplStatement = eplStatement;
        this.description = description;
        this.isUsed = isUsed;
        this.loadOnStartUp = loadOnStartUp;
        this.isMainRule = isMainRule;
        this.isSimulation = isSimulation;
        this.isRealTime = isRealtime;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatementName() {
        return statementName;
    }

    public void setStatementName(String statementName) {
        this.statementName = statementName;
    }

    public String getEplStatement() {
        return eplStatement;
    }

    public void setEplStatement(String eplStatement) {
        this.eplStatement = eplStatement;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public boolean isLoadOnStartUp() {
        return loadOnStartUp;
    }

    public void setLoadOnStartUp(boolean loadOnStartUp) {
        this.loadOnStartUp = loadOnStartUp;
    }

    public boolean isIsMainRule() {
        return isMainRule;
    }

    public void setIsMainRule(boolean isMainRule) {
        this.isMainRule = isMainRule;
    }

    public boolean isIsSimulation() {
        return isSimulation;
    }

    public void setIsSimulation(boolean isSimulation) {
        this.isSimulation = isSimulation;
    }

    public boolean isIsRealTime() {
        return isRealTime;
    }

    public void setIsRealTime(boolean isRealTime) {
        this.isRealTime = isRealTime;
    }
    
    
    
    
    @Override
    public String toString()
    {
        return "Statemment: name: " + this.statementName;
    }
    
}
