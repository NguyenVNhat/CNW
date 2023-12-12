create table traveler_tour
(
    IdTour     int not null,
    IdTraveler int not null
);

create index IdTour
    on traveler_tour (IdTour);

create index IdTraveler
    on traveler_tour (IdTraveler);