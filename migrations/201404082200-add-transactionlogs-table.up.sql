CREATE TABLE transactionlogs
(id BIGSERIAL PRIMARY KEY,
 type VARCHAR(15) NOT NULL,
 name VARCHAR(50) NOT NULL,
 description TEXT,
 quantity INTEGER);
