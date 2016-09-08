/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  fatal1t
 * Created: Sep 8, 2016
 */
/**
*Skritp pro vytvoreni tabulky pro EURUSD pair
*
*/
-- DROP TABLE forexdata.ticks;
CREATE SEQUENCE forexdata.ticks_eur_usd_ids;

CREATE TABLE forexdata.ticks_eurusd
(
  id bigint DEFAULT nextval('forexdata.ticks_eur_usd_ids'),
  ask double precision,
  bid double precision,
  askvolume bigint,
  bidvolume bigint,
  high double precision,
  low double precision,
  spreadraw double precision,
  spreadtable double precision,
  symbol varchar(3),
  quoteid integer,
  ilevel integer,
  itime timestamp without time zone,
  CONSTRAINT ticks_eurusd_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE forexdata.ticks_eurusd
  OWNER TO admin;
GRANT ALL ON TABLE forexdata.ticks_eurusd TO admin;
GRANT INSERT ON TABLE forexdata.ticks_eurusd TO app;
