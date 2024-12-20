-- créer table forfait
CREATE TABLE Forfaits (
    id VARCHAR(255) PRIMARY KEY,
    desc VARCHAR(255) NOT NULL,
    description TEXT
);

-- Chargement des données depuis offres.csv
INSERT INTO Forfaits (id, desc, description)
SELECT id, desc, desc_full
FROM CSVREAD('classpath:db/data/offres.csv');
