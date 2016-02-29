
--DROP FUNCTION log.dolog(character varying, character varying, character varying, character varying, character varying, text);
CREATE FUNCTION log.dolog( a_sid character varying(40),
  a_currentcomponent varchar(40),
  a_sourcequeue character varying(40),
  a_targetqueue character varying(40),
  a_correlid character varying(40),
  a_request text)
  RETURNS void AS
$$
	INSERT INTO log.application_log (sid, logtime, currentcomponent, sourcequeue, targetqueue, correlid, request) 
	VALUES (a_sid, NOW(), a_currentcomponent, a_sourcequeue, a_targetqueue, a_correlId, a_request);
$$
LANGUAGE SQL;
	
GRANT EXECUTE on ALL FUNCTIONS IN SCHEMA log to application;
