create table schedule
(
    id          bigint auto_increment
        primary key,
    post_time   datetime(6)  null,
    update_time datetime(6)  null,
    content     varchar(255) null,
    title       varchar(255) null,
    user_id     bigint       null
);

