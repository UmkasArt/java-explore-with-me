DROP TABLE IF EXISTS users, categories, compilations, locations, events, participations, compilation_event CASCADE;

CREATE TABLE IF NOT EXISTS users (
     id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
     name VARCHAR(255) NOT NULL UNIQUE,
     email VARCHAR(100) NOT NULL,
     CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS categories (
      id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
      name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS compilations (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    pinned BOOLEAN
);

CREATE TABLE IF NOT EXISTS locations (
     id  BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
     lat INTEGER NOT NULL,
     lon INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS events (
      id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
      annotation VARCHAR(1000) NOT NULL,
      category_id BIGINT REFERENCES categories(id) ON DELETE CASCADE,
      created_on TIMESTAMP NOT NULL,
      description VARCHAR(1000),
      event_date TIMESTAMP NOT NULL,
      initiator_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
      location_id BIGINT REFERENCES locations(id) ON DELETE CASCADE,
      paid BOOLEAN NOT NULL,
      participant_limit INTEGER,
      published_on TIMESTAMP,
      request_moderation BOOLEAN,
      state VARCHAR(50),
      title VARCHAR(255) NOT NULL,
      views BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS participations (
      id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
      created TIMESTAMP NOT NULL,
      event_id BIGINT REFERENCES events(id) ON DELETE CASCADE,
      requester BIGINT REFERENCES users(id) ON DELETE CASCADE,
      status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS compilation_event (
     compilation_id BIGINT REFERENCES compilations(id) ON DELETE CASCADE,
     event_id BIGINT REFERENCES events(id) ON DELETE CASCADE,
     CONSTRAINT ce_pk PRIMARY KEY (compilation_id, event_id)
);