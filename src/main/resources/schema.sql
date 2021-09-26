DROP TABLE IF EXISTS user;
create table user(
  email      varchar(50),
  password        varchar(50),
  first_Name        varchar(50),
  last_Name        varchar(50),
  constraint pk_user primary key (email)
);
