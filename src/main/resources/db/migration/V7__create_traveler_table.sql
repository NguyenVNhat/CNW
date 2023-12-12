create table traveler
(
    Id       int auto_increment
        primary key,
    Username varchar(255) not null,
    Password varchar(255) not null,
    Name     varchar(255) not null,
    Address  varchar(255) null,
    DayBorn  date         null,
    Email    varchar(255) not null,
    Phone    varchar(255) null,
    Avatar   varchar(255) null
)