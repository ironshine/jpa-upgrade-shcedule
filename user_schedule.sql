create table user_schedule
(
    id          bigint auto_increment
        primary key,
    schedule_id bigint null,
    user_id     bigint null,
    constraint FKdd4cwg959bmy4551iiivx4wdw
        foreign key (schedule_id) references schedule (id),
    constraint FKmsyiiyw4bv8y8sv4dbh6k481a
        foreign key (user_id) references user (id)
);

