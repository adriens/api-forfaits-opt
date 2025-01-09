-- Créer la table forfait_m
CREATE TABLE forfait_m (
    id VARCHAR(255) PRIMARY KEY,
    volumetrie VARCHAR(255),
    vocal VARCHAR(255),
    sms VARCHAR(255),
    prix DOUBLE,
    url VARCHAR(255) NOT NULL
);

-- Charger les données depuis forfait_m.csv
INSERT INTO forfait_m (id, volumetrie, vocal, sms, prix, url)
SELECT id, volumetrie, vocal, sms, prix, url
FROM CSVREAD('classpath:db/data/forfait_m.csv');
