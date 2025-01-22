CREATE TABLE tourism_card (
    id VARCHAR(255) PRIMARY KEY,
    prix INT,
    credit INT,
    volumetrie VARCHAR(255),
    duree_validite VARCHAR(255),
    url VARCHAR(255)
);

INSERT INTO tourism_card(id,prix,credit,volumetrie,duree_validite,url)
SELECT id,prix,credit,volumetrie,duree_validite,url
FROM CSVREAD('classpath:/db/data/tourism_card.csv')
