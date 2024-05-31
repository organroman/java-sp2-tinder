--create
--database user_step_base; todo: delete


create table users
(
    user_id   serial
        constraint student_pk primary key,
    name text,
    logo text,
    password text
);

insert into users (name,logo) values ('MXP','https://stud-point.com/wp-content/uploads/2024/01/logo_MKHP.png', '123456');
insert into users (name,logo) values ('Farro','https://farro.org.ua/templates/Faro_lang/images/faro-logo.png', '123456');
insert into users (name,logo) values ('GLB','https://corp.globino.ua/wp-content/uploads/2020/05/logo.png', '123456');
insert into users (name,logo) values ('John Doe','https://robohash.org/johndoe.png', '123456');
insert into users (name,logo) values ('Jane Doe','https://robohash.org/janesmith.png' '123456');

create table likes
(
    id     serial
        constraint likes_pk
            primary key,
    who    integer,
    whom   integer,
    action text
);

create table messages
(
    id     serial
        constraint messages_pk
            primary key,
    chat_owner  integer,
    receiver   integer,
    content text,
    date timestamp
);