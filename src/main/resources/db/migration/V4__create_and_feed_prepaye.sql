CREATE TABLE kit_prepaye (
    id VARCHAR(255) PRIMARY KEY,
    credit INT,
    prix INT,
    sms_offert INT,
    duree_validite INT,
    url VARCHAR(255)
);

INSERT INTO kit_prepaye (id, credit, prix, sms_offert, duree_validite, url)
SELECT 
    id,
    credit,
    prix,
    sms_offert,
    duree_validite,
    url
FROM CSVREAD('classpath:db/data/prepaye.csv');
