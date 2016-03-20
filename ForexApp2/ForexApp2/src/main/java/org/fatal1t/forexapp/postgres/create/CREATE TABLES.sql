-- Table: log.application_log
-- DROP TABLE log.application_log;

CREATE TABLE log.application_log
(
  sid character varying(100) NOT NULL,
  logtime timestamp without time zone DEFAULT now(),
  currentcomponent character varying(100),
  sourcequeue character varying(100),
  targetqueue character varying(100),
  correlid character varying(100),
  request text,
  CONSTRAINT application_log_pkey PRIMARY KEY (sid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE log.application_log
  OWNER TO admin;
GRANT ALL ON TABLE log.application_log TO admin;
GRANT SELECT, INSERT ON TABLE log.application_log TO app;

CREATE SEQUENCE configuration.service_queues_id   INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 10
  CACHE 1;
CREATE TABLE configuration.service_queues
(
	id bigint PRIMARY KEY DEFAULT nextval('configuration.service_queues_id'),
	service varchar(100),
	consumer varchar(100),
	targetqueue varchar(100) NOT NULL,
	replyToQueue varchar(100)
	);
GRANT SELECT  ON configuration.service_queues TO app;

CREATE SEQUENCE forexdata.candelstempids   INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 10
  CACHE 1;

CREATE TABLE forexdata.candlestemp(
	id bigint DEFAULT nextval('forexdata.candelstempids') PRIMARY KEY,
	time timestamp,
	open double precision,
	high double precision,
	low double precision,
	close double precision,
	vol double precision,
	quoteId int,
	symbol varchar(10)
    );
GRANT INSERT ON  forexdata.candlestemp TO app;
 
CREATE SEQUENCE forexdata.symbolsids 
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 26
  CACHE 1;

  
CREATE TABLE forexdata.symbols
(
  id bigint NOT NULL DEFAULT nextval('forexdata.symbolsids'::regclass),
  ask double precision,
  bid double precision,
  currency character varying(10),
  currencypfoit character varying(10),
  description character varying(200),
  instantmaxvolume integer,
  high double precision,
  low double precision,
  symbol character varying(20),
  itime timestamp without time zone,
  itype integer,
  groupname character varying(20),
  categoryname character varying(200),
  bigintonly boolean,
  starting bigint,
  expiration bigint,
  stepruleid integer,
  stopslevel integer,
  lotmax double precision,
  lotmin double precision,
  lotstep double precision,
  iprecision integer,
  cotranctsize bigint,
  initialmargin bigint,
  marginhedged double precision,
  marginhedgedstrong boolean,
  marginmaintanence bigint,
  marginmode bigint,
  percentage double precision,
  profitmode bigint,
  spreadraw double precision,
  spreadtable double precision,
  swapbigint double precision,
  swapshort double precision,
  swaptype bigint,
  swaprollover bigint,
  ticksize double precision,
  tickvalue double precision,
  quoteid integer,
  leverage double precision,
  ticks boolean,
  CONSTRAINT symbols_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE forexdata.symbols
  OWNER TO admin;
GRANT ALL ON TABLE forexdata.symbols TO admin;
GRANT SELECT, UPDATE, INSERT ON TABLE forexdata.symbols TO app;


