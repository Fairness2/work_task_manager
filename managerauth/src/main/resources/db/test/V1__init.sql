create table user
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    name      varchar(50) not null,
    surname      varchar(50),
    patronymic      varchar(50),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table role
(
    code         bigserial primary key,
    title       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table ref_user_role
(
    user_id    bigint not null references user (id),
    role_code    bigint not null references role (code),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (user_id, role_code)
);

insert into roles (title)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into user (username, password, name)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john');

insert into users_roles (user_id, role_code)
values (1, 1),
       (2, 2);