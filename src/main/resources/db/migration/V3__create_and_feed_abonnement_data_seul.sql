CREATE TABLE abonnement_data_seul (
    id VARCHAR(255) PRIMARY KEY,
    volumetrie VARCHAR(255),
    debit VARCHAR(255),
    prix DOUBLE,
    url VARCHAR(255),
    type_forfait VARCHAR(50)
);

INSERT INTO abonnement_data_seul (id, volumetrie, debit, prix, url, type_forfait)
SELECT 
    id,
    volumetrie,
    debit,
    prix,
    url,
    type_forfait
FROM CSVREAD('classpath:db/data/abonnement-data-seul.csv');
