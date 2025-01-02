-- créer table forfait
CREATE TABLE forfaits (
    id VARCHAR(255) PRIMARY KEY,
    desc VARCHAR(255) NOT NULL,
    description TEXT,
    url VARCHAR(255) NOT NULL
);

-- Chargement des données depuis offres.csv
INSERT INTO forfaits (id, desc, description,url)
SELECT id, desc, desc_full, url
FROM CSVREAD('classpath:db/data/offres.csv');
