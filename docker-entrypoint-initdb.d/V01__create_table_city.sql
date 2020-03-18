CREATE TABLE city(
    id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(50) NOT NULL,
    latitude double NOT NULL,
    longitude double
) ENGINE=Innodb DEFAULT CHARSET=utf8;