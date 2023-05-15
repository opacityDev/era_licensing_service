DROP DATABASE IF EXISTS license_keys;
CREATE DATABASE license_keys;
USE license_keys;
CREATE USER 'springuser'@'0.0.0.0' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON license_keys.* TO 'springuser'@'0.0.0.0';
FLUSH PRIVILEGES;
CREATE TABLE lkey (
    id int NOT NULL,
    content varchar(255) NOT NULL,
    domain_name varchar(255),
    owner int,
    PRIMARY KEY (ID)
);