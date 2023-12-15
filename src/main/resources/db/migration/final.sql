create table address
(
    Id      int          not null,
    Address varchar(255) not null
);
alter table address
    add primary key (Id);
create table admin
(
    Id       int          not null,
    Username varchar(255) null,
    Password varchar(255) null
);

alter table admin
    add primary key (Id);
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
create table tour_address
(
    IdTour    int not null,
    IdAddress int not null
);

create index IdAddress
    on tour_address (IdAddress);

create index IdTour
    on tour_address (IdTour);
create table traveler_tour
(
    IdTour     int not null,
    IdTraveler int not null
);

create index IdTour
    on traveler_tour (IdTour);

create index IdTraveler
    on traveler_tour (IdTraveler);
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
);
alter table tour
    add constraint tour_ibfk_1
        foreign key (IdInstructor) references instructor (Id);

alter table tour_address
    add constraint tour_address_ibfk_1
        foreign key (IdTour) references tour (Id);

alter table tour_address
    add constraint tour_address_ibfk_2
        foreign key (IdAddress) references address (Id);

alter table traveler_tour
    add constraint traveler_tour_ibfk_1
        foreign key (IdTour) references tour (Id);

alter table traveler_tour
    add constraint traveler_tour_ibfk_2
        foreign key (IdTraveler) references traveler (Id);