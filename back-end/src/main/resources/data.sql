insert into USERS (id, email, nickname, password, created_at, updated_at)
values (1, 'admin@test.com', 'admin', '$2a$10$SYRaUcIMqde4T8Za/9olV.KnIPyWoGSq11Hnhu/jVWMTDhnbha2XK', NOW(), NOW());

insert into USERS (id, email, nickname, password, created_at, updated_at)
values (2, 'editor@test.com', 'editor', '$2a$10$P9VhxQTiqHTxH3dmkuTkQuBIpv0h2f6pjkCQYbLpKOskluSOVRgNW', NOW(), NOW());

insert into USERS (id, email, nickname, password, created_at, updated_at)
values (3, 'viewer@test.com', 'viewer', '$2a$10$Uy47ZQNRPX/fBxnD44kUzOdWDENnvlqcmrPHbEIk3s7MmErr6OOIK', NOW(), NOW());


insert into PROJECT (id, name, created_at, updated_at)
values (1, 'myProject', NOW(), NOW());


insert into USER_PROJECT (id, user_id, project_id, role, created_at, updated_at)
values (1, 1, 1, 'ROLE_ADMIN', NOW(), NOW());

insert into USER_PROJECT (id, user_id, project_id, role, created_at, updated_at)
values (2, 2, 1, 'ROLE_EDITOR', NOW(), NOW());

insert into USER_PROJECT (id, user_id, project_id, role, created_at, updated_at)
values (3, 3, 1, 'ROLE_VIEWER', NOW(), NOW());
