create table address
(
    Id      int          not null,
    Address varchar(255) not null
);
alter table address
    add primary key (Id);