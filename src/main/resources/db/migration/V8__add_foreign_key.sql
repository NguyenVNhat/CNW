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