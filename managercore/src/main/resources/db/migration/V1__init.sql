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
create table d_comment_type
(
    code            varchar(50) primary key,
    title           varchar(255)
);
INSERT INTO d_comment_type(code, title) VALUES
                                           ('comment', 'Комментарий'),
                                           ('pause_request', 'Заявка на приостановку'),
                                           ('start_request', 'Заявка на возобновление'),
                                           ('done_request', 'Заявка на завершение');
create table d_action
(
    code            varchar(50) primary key,
    title           varchar(255)
);
INSERT INTO d_action(code, title) VALUES
                                            ('approve', 'Одобрить'),
                                            ('disprove', 'Отклонить'),
                                            ('cancel', 'Отменить');
create table ref_action_role
(
    id              bigserial primary key,
    action_code     varchar(50) not null references d_action (code),
    role_code       varchar(50) not null,
    created_at      timestamp default current_timestamp
);
INSERT INTO ref_action_role(action_code, role_code) VALUES
                                      ('approve', 'ROLE_ADMIN'),
                                      ('disprove', 'ROLE_ADMIN'),
                                      ('cancel', 'ROLE_USER');
create table t_comment
(
    id              bigserial primary key,
    text            text,
    task_id         bigint not null references t_task (id),
    author_id       bigint not null,
    type_code       varchar(50) not null references d_comment_type (code),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp,
    result_at       timestamp,
    action_code     varchar(50)
);
create table t_files
(
    id              bigserial primary key,
    task_id         bigint references t_task (id),
    user_id         bigint not null,
    comment_id      bigint references t_comment (id),
    name            varchar(255),
    type            varchar(255),
    file_id         varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);