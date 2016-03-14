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
 