create table tour_address
(
    IdTour    int not null,
    IdAddress int not null
);

create index IdAddress
    on tour_address (IdAddress);

create index IdTour
    on tour_address (IdTour);