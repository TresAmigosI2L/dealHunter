create table deals
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

create table users
(
    id         uuid    not null primary key,
    created_at timestamp(6),
    updated_at timestamp(6),
    enabled    boolean not null,
    password   varchar(255),
    username   varchar(255)
);

alter table users
    owner to username;

create table user_entity_authorities
(
    user_entity_id uuid not null
        constraint fkpx3ixqoimmc0pdada9bsox9ug
            references users,
    authorities    bytea
);

alter table user_entity_authorities
    owner to username;

