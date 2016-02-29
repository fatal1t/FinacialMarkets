-- Table: log.application_log
-- DROP TABLE log.application_log;

CREATE TABLE log.application_log
(
  sid character varying(40) NOT NULL,
  logtime timestamp without time zone,
  currentcomponent varchar(40),
  sourcequeue character varying(40),
  targetqueue character varying(40),
  correlid character varying(40),
  request text,
  CONSTRAINT application_log_pkey PRIMARY KEY (sid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE log.application_log
  OWNER TO admin;
