drop table if exists promotion;

create table promotion(
    id identity primary key,
    promo_name VARCHAR(255),
    location VARCHAR(255),
    data_source VARCHAR(255)
);
