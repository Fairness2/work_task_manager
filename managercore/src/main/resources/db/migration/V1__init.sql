create table task
(
    id              bigserial primary key,
    title           varchar(255),
    description     varchar(255),
    employer_id     bigint not null references user (id),
    author_id       bigint not null references user (id),
    responsible_user_id bigint not null references user (id),
    working_hours   bigint,
    plan_start_date date,
    plan_end_date   date,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table status
(
    code            bigserial primary key,
    title           varchar(255)
);

create table task_status
(
    id              bigserial primary key,
    task_id         bigint not null references task (id),
    status_code     bigint not null references status (code),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp,
    ended_at        date,
    user_id         bigint not null references user (id)
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
    role_code       bigint not null references role (code),
    created_at      timestamp default current_timestamp
);
create table comment
(
    id              bigserial primary key,
    text            varchar(255),
    task_id         bigint not null references task (id),
    author_id       bigint not null references user (id),
    comment_type    bigint not null references comment_type (code),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);
create table files
(
    id              bigserial primary key,
    task_id         bigint not null references task (id),
    user_id         bigint not null references user (id),
    comment_id      bigint not null references comment (id),
    name            varchar(255),
    type            varchar(255),
    link            varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);