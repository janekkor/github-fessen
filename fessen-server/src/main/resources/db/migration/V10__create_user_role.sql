CREATE TABLE USER_ROLE(
USER_ID BIGINT not null,
ROLE_ID BIGINT not null,
foreign key (USER_ID) references APP_USER(ID),
foreign key (ROLE_ID) references APP_ROLE(ID)
);