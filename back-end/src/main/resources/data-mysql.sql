insert into users (id, email, nickname, password, created_at, updated_at)
values (1, 'admin@test.com', 'admin', '$2a$10$SYRaUcIMqde4T8Za/9olV.KnIPyWoGSq11Hnhu/jVWMTDhnbha2XK', NOW(), NOW());

insert into users (id, email, nickname, password, created_at, updated_at)
values (2, 'editor@test.com', 'editor', '$2a$10$P9VhxQTiqHTxH3dmkuTkQuBIpv0h2f6pjkCQYbLpKOskluSOVRgNW', NOW(), NOW());

insert into users (id, email, nickname, password, created_at, updated_at)
values (3, 'viewer@test.com', 'viewer', '$2a$10$Uy47ZQNRPX/fBxnD44kUzOdWDENnvlqcmrPHbEIk3s7MmErr6OOIK', NOW(), NOW());


insert into project (id, name, created_at, updated_at)
values (1, 'myProject', NOW(), NOW());


insert into user_project (id, user_id, project_id, role, created_at, updated_at)
values (1, 1, 1, 'ROLE_ADMIN', NOW(), NOW());

insert into user_project (id, user_id, project_id, role, created_at, updated_at)
values (2, 2, 1, 'ROLE_EDITOR', NOW(), NOW());

insert into user_project (id, user_id, project_id, role, created_at, updated_at)
values (3, 3, 1, 'ROLE_VIEWER', NOW(), NOW());


insert into template (id, path, title, description, created_at, updated_at)
values (1, 'path', 'spring server template', 'Simple deploy Spring server', NOW(), NOW());

insert into template (id, path, title, description, created_at, updated_at)
values (2, 'path', 'react template', 'Simple deploy react', NOW(), NOW());
