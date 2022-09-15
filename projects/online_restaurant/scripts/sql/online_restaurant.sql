-- DROP SCHEMA online_restaurant;

CREATE SCHEMA online_restaurant AUTHORIZATION postgres;
-- online_restaurant.vendor definition

-- Drop table

-- DROP TABLE online_restaurant.vendor;

CREATE TABLE online_restaurant.vendor (
	id serial4 NOT NULL,
	"name" varchar NOT NULL,
	category varchar NULL,
	state varchar NULL,
	city varchar NULL,
	address varchar NULL,
	registration_date timestamp NULL,
	modified_date timestamp NULL,
	approved bool NULL,
	latitude numeric NULL,
	longitude numeric NULL,
	CONSTRAINT restaurant_id_pk PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE online_restaurant.vendor OWNER TO postgres;
GRANT ALL ON TABLE online_restaurant.vendor TO postgres;


-- online_restaurant.menu_category definition

-- Drop table

-- DROP TABLE online_restaurant.menu_category;

CREATE TABLE online_restaurant.menu_category (
	id serial4 NOT NULL,
	category varchar NULL,
	sub_category varchar NULL,
	sub_category2 varchar NULL,
	veg bool NULL,
	CONSTRAINT menu_category_pk PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE online_restaurant.menu_category OWNER TO postgres;
GRANT ALL ON TABLE online_restaurant.menu_category TO postgres;


-- online_restaurant.food_item definition

-- Drop table

-- DROP TABLE online_restaurant.food_item;

CREATE TABLE online_restaurant.food_item (
	id serial4 NOT NULL,
	"name" varchar NULL,
	veg bool NULL,
	category varchar NULL,
	price numeric NULL,
	description text NULL,
	category_id int8 NULL,
	images text NULL,
	CONSTRAINT food_item_pk PRIMARY KEY (id),
	CONSTRAINT food_item_fk FOREIGN KEY (category_id) REFERENCES online_restaurant.menu_category(id)
);

-- Permissions

ALTER TABLE online_restaurant.food_item OWNER TO postgres;
GRANT ALL ON TABLE online_restaurant.food_item TO postgres;




-- Permissions

GRANT ALL ON SCHEMA online_restaurant TO postgres;
