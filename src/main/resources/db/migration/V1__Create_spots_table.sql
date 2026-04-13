CREATE TABLE spots (
                       spot_id BIGSERIAL PRIMARY KEY,
                       spot_name VARCHAR(255) NOT NULL,
                       spot_latitude DOUBLE PRECISION,
                       spot_longitude DOUBLE PRECISION,
                       spot_azimut INTEGER,
                       spot_created_at TIMESTAMP NOT NULL,
                       spot_updated_at TIMESTAMP,
                       spot_version BIGINT DEFAULT 0,
                       spot_deleted_at TIMESTAMP
);


CREATE INDEX idx_spots_name ON spots(spot_name);