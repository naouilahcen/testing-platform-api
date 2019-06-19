-- DROP USER 'platform_user'@'localhost';

CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';

GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost';

-- SET PASSWORD FOR 'platform_user'@'localhost' = 'P@55w0rd';

CREATE DATABASE IF NOT EXISTS `testing_platform_db`;

GRANT ALL PRIVILEGES ON `testing_platform_db`.* TO 'root'@'localhost';

