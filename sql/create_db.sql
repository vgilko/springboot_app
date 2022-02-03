create database denfinder;

create user denfinder_admin with login password 'zxQWe.!/';
grant all privileges on database "denfinder" to denfinder_admin;

create table address (
    id serial primary key,
    country varchar (100) not null,
    state varchar (100) not null,
    city varchar (100) not null,
    street varchar (100) not null,
    house varchar (20) not null,
    coordinates geography not null
);


crate table university (
    id serial primary key,
    name varchar (200) not null,
    address integer foreign key,
    metro_station integer foreign key
);


create table users (
    id serial primary key,
    name varchar (100) not null,
    email varchar (50) not null ,
    gender boolean not null,
    age smallint not null,
--    university integer foreign key,
    description varchar (500)
);