CREATE TABLE APP_GROUP(
ID BIGINT AUTO_INCREMENT not null,
NAME VARCHAR(50) not null,
DESCRIPTION VARCHAR(500),
MOD_USER VARCHAR(5) not null,
MOD_TIME TIMESTAMP not null
);

ALTER TABLE APP_GROUP
ADD PRIMARY KEY (ID);

ALTER TABLE APP_GROUP
ADD CONSTRAINT UNIQUE_GROUP_NAME
UNIQUE(NAME);