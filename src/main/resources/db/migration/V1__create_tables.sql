CREATE TABLE outbox (
    id UUID PRIMARY KEY,
    aggregate_type VARCHAR(255),
    aggregate_id VARCHAR(255),
    type VARCHAR(255),
    payload JSON
);

CREATE SEQUENCE IF NOT EXISTS entity_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 10;
    
CREATE TABLE IF NOT EXISTS functions
(
    id bigint NOT NULL DEFAULT nextval('entity_seq'::regclass),
    entityid_project VARCHAR(64) NOT NULL,
    entityid_code VARCHAR(64) NOT NULL,
    name VARCHAR(128),
    description VARCHAR(512),
    CONSTRAINT functions_pkey PRIMARY KEY (id)
);

ALTER SEQUENCE entity_seq OWNED BY functions.id;
