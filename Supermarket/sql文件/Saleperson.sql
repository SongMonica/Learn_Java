USE supermarket;

DROP TABLE IF EXISTS Salepersons;

-- 创建售货员表
CREATE TABLE Salepersons (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL ,
    password VARCHAR(20) NOT NULL ,
    intime VARCHAR(30) NOT NULL ,
    PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 插入记录
INSERT INTO Salepersons (id, name, password, intime) VALUES (101, '小林', 'a757664', now());