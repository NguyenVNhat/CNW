create table tour
(
    Id           int  not null,
    IdInstructor int  not null,
    Price        int  not null,
    TotalTime    int  not null,
    TimeStart    date not null,
    Status       int  not null
);

create index IdInstructor
    on tour (IdInstructor);
alter table tour
    add primary key (Id);