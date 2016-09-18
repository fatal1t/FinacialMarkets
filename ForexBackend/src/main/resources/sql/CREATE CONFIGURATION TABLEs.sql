/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  fatal1t
 * Created: Sep 18, 2016
 */
/*
    @Id        
    private Long id;    
    @Column
    private String statementName;    
    @Lob
    @Column(columnDefinition = "text")
    private String eplStatement;    
    @Column()
    private String description;        
    @Column
    private boolean isUsed;    
    @Column
    private boolean loadOnStartUp;    
    @Column
    private boolean isMainRule;
*/

--DROP SEQUENCE configuration.eplstatementsids;
DROP TABLE configuration.eplstatements;
--CREATE SEQUENCE configuration.eplstatementsids;

CREATE TABLE configuration.eplstatements
(
  id bigint NOT NULL DEFAULT nextval('configuration.eplstatementsids'::regclass),
    statement_name varchar(100) ,
    epl_statement text,
    description varchar(100),
    is_used boolean,
    load_on_statup boolean,
    is_main_rule boolean,
  CONSTRAINT eplstatement_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX eplstatements_name_index ON configuration.eplstatements (statement_name);
ALTER TABLE configuration.eplstatements
  OWNER TO admin;
GRANT ALL ON TABLE configuration.eplstatements TO admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE configuration.eplstatements TO app;