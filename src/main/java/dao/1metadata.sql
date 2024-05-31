create
database user_step_base;


create table users
(
    user_id   serial
        constraint student_pk primary key,
    name text,
    logo text
);

insert into users (name,logo) values ('MXP','https://stud-point.com/wp-content/uploads/2024/01/logo_MKHP.png');
insert into users (name,logo) values ('Farro','https://farro.org.ua/templates/Faro_lang/images/faro-logo.png');
insert into users (name,logo) values ('GLB','https://corp.globino.ua/wp-content/uploads/2020/05/logo.png');
insert into users (name,logo) values ('John Doe','https://robohash.org/johndoe.png');
insert into users (name,logo) values ('Jane Doe','https://robohash.org/janesmith.png');