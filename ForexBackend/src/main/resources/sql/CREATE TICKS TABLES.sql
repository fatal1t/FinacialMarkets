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
*Skritp pro vytvoreni tabulky pro ticks EURUSD pair
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
  symbol bigint,
  quoteid integer,
  ilevel integer,
  itime timestamp without time zone,
  CONSTRAINT ticks_eurusd_id PRIMARY KEY (id),
  CONSTRAINT ticks_eurusd_symbols_is FOREIGN KEY (symbol)
  REFERENCES forexdata.symbols (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE forexdata.ticks_eurusd
  OWNER TO admin;
GRANT ALL ON TABLE forexdata.ticks_eurusd TO admin;
GRANT INSERT ON TABLE forexdata.ticks_eurusd TO app;



CREATE SEQUENCE forexdata.candles_eur_usd_ids;


/**
*Skritp pro vytvoreni tabulky pro candles EURUSD pair
*
*/

DROP TABLE forexdata.candles_EURUSD;

CREATE TABLE forexdata.candles_EURUSD
(
  id bigint NOT NULL DEFAULT nextval('forexdata.candles_eur_usd_ids'::regclass),
  itime timestamp without time zone,
  open double precision,
  high double precision,
  low double precision,
  cclose double precision,
  vol double precision,
  quoteid integer,
  symbol bigint,
  period integer,
  CONSTRAINT candlestemp_pkey PRIMARY KEY (id),
    CONSTRAINT candles_eurusd_symbols_is FOREIGN KEY (symbol)
  REFERENCES forexdata.symbols (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE forexdata.candles_EURUSD
  OWNER TO admin;
GRANT ALL ON TABLE forexdata.candles_EURUSD TO admin;
GRANT INSERT ON TABLE forexdata.candles_EURUSD TO app;