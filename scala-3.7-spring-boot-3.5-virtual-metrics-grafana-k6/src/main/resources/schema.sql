DROP TABLE IF EXISTS data_entries CASCADE;
CREATE TABLE IF NOT EXISTS data_entries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_data_entries_created_at ON data_entries(created_at);
