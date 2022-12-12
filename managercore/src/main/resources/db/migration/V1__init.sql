create table t_task
(
    id              bigserial primary key,
    title           varchar(255),
    description     varchar(255),
    employer_id     bigint not null,
    author_id       bigint not null,
    responsible_user_id bigint not null,
    working_hours   int,
    plan_start_date date,
    plan_end_date   date,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table d_task_status
(
    code            varchar(50) primary key,
    title           varchar(255) not null
);

INSERT INTO d_task_status(code, title) VALUES
                                      ('paused', 'Приостановлено'),
                                      ('pending', 'В работе'),
                                      ('done', 'Выполнено');

create table ref_task_status
(
    id              bigserial primary key,
    task_id         bigint not null references t_task (id),
    status_code     varchar(50) not null references d_task_status (code),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp,
    ended_at        timestamp default current_timestamp,
    user_id         bigint not null
);
create table comment_type
(
    code            bigserial primary key,
    title           varchar(255)
);
create table action
(
    code            bigserial primary key,
    title           varchar(255)
);
create table action_role
(
    id              bigserial primary key,
    action_code     bigint not null references action (code),
    role_code       bigint not null,
    created_at      timestamp default current_timestamp
);
create table comment
(
    id              bigserial primary key,
    text            varchar(255),
    task_id         bigint not null references t_task (id),
    author_id       bigint not null,
    comment_type    bigint not null references comment_type (code),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);
create table files
(
    id              bigserial primary key,
    task_id         bigint not null references t_task (id),
    user_id         bigint not null,
    comment_id      bigint not null references comment (id),
    name            varchar(255),
    type            varchar(255),
    file_id         varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);