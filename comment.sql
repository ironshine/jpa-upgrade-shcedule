create table comment
(
    id          bigint auto_increment
        primary key,
    post_time   datetime(6)  null,
    update_time datetime(6)  null,
    content     varchar(255) null,
    username    varchar(255) not null,
    schedule_id bigint       null,
    constraint UKqooo997fdb83xassryefajw98
        unique (username),
    constraint FKsy51iks4dgapu66gfj3mnykch
        foreign key (schedule_id) references schedule (id)
);

