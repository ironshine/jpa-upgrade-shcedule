create table user
(
    id          bigint auto_increment
        primary key,
    post_time   datetime(6)            null,
    update_time datetime(6)            null,
    email       varchar(255)           not null,
    password    varchar(255)           null,
    role        enum ('ADMIN', 'USER') null,
    username    varchar(255)           not null,
    constraint UKob8kqyqqgmefl0aco34akdtpe
        unique (email),
    constraint UKsb8bbouer5wak8vyiiy4pf2bx
        unique (username)
);

