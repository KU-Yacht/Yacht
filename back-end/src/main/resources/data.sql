insert into USERS (id, email, nickname, password, role, created_at, updated_at)
values (1, 'admin@test.com', 'admin', 'pw', 'ROLE_ADMIN', NOW(), NOW());

insert into USERS (id, email, nickname, password, role, created_at, updated_at)
values (2, 'editor@test.com', 'editor', 'pw', 'ROLE_EDITOR', NOW(), NOW());

insert into USERS (id, email, nickname, password, role, created_at, updated_at)
values (3, 'viewer@test.com', 'viewer', 'pw', 'ROLE_VIEWER', NOW(), NOW());
