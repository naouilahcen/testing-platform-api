-- init database

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE `authorities`;
TRUNCATE `authorities_categories`;
TRUNCATE `configurations`;
TRUNCATE `excluded_authorities`;
TRUNCATE `logs`;
TRUNCATE `profiles`;
TRUNCATE `profiles_authorities`;
TRUNCATE `users`;
SET FOREIGN_KEY_CHECKS = 1;

-- profiles

INSERT INTO profiles
    (id, description, title)
VALUES
       (1, 'root', 'root'),
       (2, 'admin', 'admin');

-- authorities_categories
INSERT INTO authorities_categories
    (id, title)
value (1, 'Administration');

-- authorities
INSERT INTO authorities
    (id, name, category_id, code)
VALUES
       (1, 'Profiles management', 1, 'PROFILES_MANAGEMENT'),
       (2, 'Users management', 1, 'USERS_MANAGEMENT');

-- profiles_authorities
INSERT INTO profiles_authorities
    (profile_id, authority_id)
VALUES

    -- root
       (1, 1),
       (1, 2),

    -- admin
       (2, 1),
       (2, 2);

-- users
INSERT INTO users
    (id, admin, authType, email, enabled, firstname, lastname, password, username, profile_id)
VALUES
       (1, b'1', 'SIMPLE', 'root@valueit.ma', b'1', 'root', 'AL ROOT', '$2a$10$nVkoyBsW.L2AeQrjs7hVBOoqtPhAtO9jjKMuXf1ZMM7NR3RFMcQGy', 'root', 1),
       (2, b'1', 'SIMPLE', 'admin@valueit.ma', b'1', 'admin', 'AL ADMIN', '$2a$10$nVkoyBsW.L2AeQrjs7hVBOoqtPhAtO9jjKMuXf1ZMM7NR3RFMcQGy', 'admin', 2);

-- configurations

INSERT INTO configurations
  (id, code, value)
VALUES
  (1, 'SMTP Adresse IP', 'smtp.gmail.com'),
  (2, 'SMTP Port', '465'),
  (3, 'SMTP TLS', 'Yes');