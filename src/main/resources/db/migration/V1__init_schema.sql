create table users
(
    id bigint generated always as identity primary key,
    username varchar(255) not null unique
);

create table payments
(
    id bigint generated always as identity primary key,

    amount numeric(19,2) not null,
    amount_rub numeric(19,2) not null,

    currency varchar(10) not null,

    payer_id bigint not null,
    recipient_id bigint not null,

    booking_date timestamp not null,

    constraint fk_payment_payer
        foreign key (payer_id)
            references users(id),

    constraint fk_payment_recipient
        foreign key (recipient_id)
            references users(id)
);

create table fees
(
    id bigint generated always as identity primary key,

    value numeric(19,2) not null,

    user_id bigint not null,

    payment_id bigint not null unique,

    constraint fk_fee_user
        foreign key (user_id)
            references users(id),

    constraint fk_fee_payment
        foreign key (payment_id)
            references payments(id)
);