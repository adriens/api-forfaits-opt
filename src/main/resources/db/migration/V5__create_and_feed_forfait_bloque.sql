CREATE TABLE forfait_bloque (
    id VARCHAR(255) PRIMARY KEY,
    prix INT,
    credit INT,
    sms_offert INT,
    url VARCHAR(255)
);

INSERT INTO forfait_bloque (id, prix, credit, sms_offert,url)
SELECT 
    id,
    prix,
    credit,
    sms_offert,
    url
FROM CSVREAD('classpath:db/data/forfait_bloque.csv');