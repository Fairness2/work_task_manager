create table users
(
    id              bigserial primary key,
    username        varchar(36) not null,
    password        varchar(80) not null,
    name            varchar(50) not null,
    surname         varchar(50),
    patronymic      varchar(50),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table roles
(
    code        varchar(15) primary key,
    title       varchar(50) not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table ref_user_role
(
    user_id     bigint not null references users (id),
    role_code   varchar(15) not null references roles (code),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp,
    primary key (user_id, role_code)
);

insert into roles (code, title)
values ('USER', 'Пользователь'),
       ('ADMIN', 'Руководитель');

insert into users (username, password, name)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john');

insert into ref_user_role (user_id, role_code)
values (1, 'USER'),
       (2, 'ADMIN');