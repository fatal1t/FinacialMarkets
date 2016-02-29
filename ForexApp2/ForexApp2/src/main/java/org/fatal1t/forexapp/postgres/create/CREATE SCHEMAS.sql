-- Schema: log

-- DROP SCHEMA log;

CREATE SCHEMA log
  AUTHORIZATION administrator;

GRANT ALL ON SCHEMA log TO administrator;
GRANT USAGE ON SCHEMA log TO application;

-- Schema: forexdata

-- DROP SCHEMA forexdata;

CREATE SCHEMA forexdata
  AUTHORIZATION administrator;

GRANT ALL ON SCHEMA forexdata TO administrator WITH GRANT OPTION;
GRANT USAGE ON SCHEMA forexdata TO application WITH GRANT OPTION;


