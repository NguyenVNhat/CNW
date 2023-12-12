create table admin
(
    Id       int          not null,
    Username varchar(255) null,
    Password varchar(255) null
);

alter table admin
    add primary key (Id);