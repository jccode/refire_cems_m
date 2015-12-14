-- pwd: hello
insert into users(username, password, enabled, created_date) values ('admin', '$2a$10$umuOTCnnOCaT0pDpA1rGT.BHHYSdJV8jHzjLc6MBaLMfXApCgKztq', true, now());
insert into users(username, password, enabled, created_date) values ('dba', '$2a$10$umuOTCnnOCaT0pDpA1rGT.BHHYSdJV8jHzjLc6MBaLMfXApCgKztq', true, now());
insert into users(username, password, enabled, created_date) values ('developer', '$2a$10$umuOTCnnOCaT0pDpA1rGT.BHHYSdJV8jHzjLc6MBaLMfXApCgKztq', true, now());
insert into users(username, password, enabled, created_date) values ('tester', '$2a$10$umuOTCnnOCaT0pDpA1rGT.BHHYSdJV8jHzjLc6MBaLMfXApCgKztq', true, now());
insert into users(username, password, enabled, created_date) values ('deployer', '$2a$10$umuOTCnnOCaT0pDpA1rGT.BHHYSdJV8jHzjLc6MBaLMfXApCgKztq', true, now());

insert into authorities(username, authority) values ('admin', 'ROLE_ADMIN');
insert into authorities(username, authority) values ('admin', 'ROLE_DBA');
insert into authorities(username, authority) values ('dba', 'ROLE_DBA');
insert into authorities(username, authority) values ('developer', 'ROLE_USER');
insert into authorities(username, authority) values ('tester', 'ROLE_USER');
insert into authorities(username, authority) values ('deployer', 'ROLE_USER');


create table persistent_logins (username varchar(64) not null,
								series varchar(64) primary key,
								token varchar(64) not null,
								last_used timestamp not null);
