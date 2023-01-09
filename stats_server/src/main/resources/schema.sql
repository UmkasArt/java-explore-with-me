DROP TABLE IF EXISTS hits;

CREATE TABLE IF NOT EXISTS hits (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    app VARCHAR(50) NOT NULL,
    uri VARCHAR(255),
    ip VARCHAR(50),
    timestamp TIMESTAMP NOT NULL
)