CREATE TABLE if NOT EXISTS beer
{
    id integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
    beer_name varchar(255),
    beer_style varchar(255),
    upc varchar(255),
    price decimal,
    created_date timestamp,
    last_modiefied_date timestamp
};