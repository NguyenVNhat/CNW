create table instructor
(
    Id    int          not null,
    Name  varchar(255) not null,
    Age   int          not null,
    Email varchar(255) not null,
    Phone varchar(255) not null
);
alter table instructor
    add primary key (Id);