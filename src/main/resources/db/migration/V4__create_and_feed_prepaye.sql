CREATE TABLE kit_prepaye (
    id VARCHAR(255) PRIMARY KEY,
    credit INT,
    prix INT,
    sms_offert INT,
    duree_validite INT
);

INSERT INTO kit_prepaye (id, credit, prix, sms_offert, duree_validite)
SELECT 
    id,
    credit,
    prix,
    sms_offert,
    duree_validite
FROM CSVREAD('classpath:db/data/prepaye.csv');
