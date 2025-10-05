CREATE TABLE outbox {
    id              UUID PRIMARY KEY,
    aggregate_type  VARCHAR(255),
    aggregate_id    VARCHAR(255),
    type            VARCHAR(255),
    payload         JSON
}

CREATE TABLE entity {
	id			BIGINT PRIMARY KEY,
	project		VARCHAR(64),
	code		VARCHAR(64,),
	description VARCHAR(512)
}

CREATE TABLE function {
	id			BIGINT PRIMARY KEY,
	project		VARCHAR(64),
	code		VARCHAR(64,),
	description VARCHAR(512)
}