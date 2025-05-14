CREATE TABLE outbox {
    id              UUID PRIMARY KEY,
    aggregate_type  VARCHAR(255),
    aggregate_id    VARCHAR(255),
    type            VARCHAR(255),
    payload         JSON
}