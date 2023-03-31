create table if not exists comments
(
    id         uuid not null primary key,
    created_at timestamp(6),
    updated_at timestamp(6),
    author     varchar(255),
    message    varchar(255)
    );

alter table comments
    owner to username;

create table if not exists deals
(
    id             uuid         not null primary key,
    created_at     timestamp(6),
    updated_at     timestamp(6),
    active         boolean      not null,
    author         varchar(255),
    deal_url       varchar(255),
    discount_price double precision,
    image_url      varchar(255),
    original_price double precision,
    title          varchar(255) not null,
    votes          integer      not null
    );

alter table deals
    owner to username;

create table if not exists deals_comments
(
    deal_entity_id uuid not null
    constraint fk8vk0y72cpsqiesl0qg11yauld
    references deals,
    comments_id    uuid not null
    constraint uk_7vi4oon4tf9erxpiwqnr8shgi
    unique
    constraint fkhmsecpq381c9oxde9a5vqqs4l
    references comments,
    primary key (deal_entity_id, comments_id)
    );

alter table deals_comments
    owner to username;

create table if not exists users
(
    id         uuid    not null
    primary key,
    created_at timestamp(6),
    updated_at timestamp(6),
    enabled    boolean not null,
    password   varchar(255),
    username   varchar(255)
    );

alter table users
    owner to username;

create table if not exists user_entity_authorities
(
    user_entity_id uuid not null
    constraint fkpx3ixqoimmc0pdada9bsox9ug
    references users,
    authorities    bytea
);

alter table user_entity_authorities
    owner to username;

