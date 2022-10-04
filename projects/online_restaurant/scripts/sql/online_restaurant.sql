-- DROP SCHEMA online_restaurant;

CREATE SCHEMA online_restaurant AUTHORIZATION postgres;

-- DROP SEQUENCE online_restaurant.food_item_id_seq;

CREATE SEQUENCE online_restaurant.food_item_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE online_restaurant.food_item_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE online_restaurant.food_item_id_seq TO postgres;

-- DROP SEQUENCE online_restaurant.menu_category_id_seq;

CREATE SEQUENCE online_restaurant.menu_category_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE online_restaurant.menu_category_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE online_restaurant.menu_category_id_seq TO postgres;

-- DROP SEQUENCE online_restaurant.vendor_id_seq;

CREATE SEQUENCE online_restaurant.vendor_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE online_restaurant.vendor_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE online_restaurant.vendor_id_seq TO postgres;
-- online_restaurant.customer definition

-- Drop table

-- DROP TABLE online_restaurant.customer;

CREATE TABLE online_restaurant.customer (
	id int8 NOT NULL,
	"name" varchar NULL,
	email_id varchar NULL,
	"password" varchar NULL,
	city varchar NULL,
	state varchar NULL,
	delivery_address text NULL,
	created_at timestamp NULL,
	modified_at timestamp NULL,
	CONSTRAINT customer_pk PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE online_restaurant.customer OWNER TO postgres;
GRANT ALL ON TABLE online_restaurant.customer TO postgres;


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


-- online_restaurant."order" definition

-- Drop table

-- DROP TABLE online_restaurant."order";

CREATE TABLE online_restaurant."order" (
	id int8 NOT NULL,
	customer_id int8 NULL,
	amount numeric NULL,
	delivery_address text NULL,
	status varchar NULL,
	created_at timestamp NULL,
	modified_at timestamp NULL,
	CONSTRAINT order_pk PRIMARY KEY (id),
	CONSTRAINT order_fk FOREIGN KEY (customer_id) REFERENCES online_restaurant.customer(id)
);

-- Permissions

ALTER TABLE online_restaurant."order" OWNER TO postgres;
GRANT ALL ON TABLE online_restaurant."order" TO postgres;


-- online_restaurant.order_item definition

-- Drop table

-- DROP TABLE online_restaurant.order_item;

CREATE TABLE online_restaurant.order_item (
	id int8 NOT NULL,
	order_id int8 NULL,
	vendor_id int8 NULL,
	item_name varchar NULL,
	menu_category varchar NULL,
	description varchar NULL,
	veg bool NULL,
	quantity int8 NULL,
	price numeric NULL,
	CONSTRAINT order_item_pk PRIMARY KEY (id),
	CONSTRAINT order_item_fk FOREIGN KEY (order_id) REFERENCES online_restaurant."order"(id),
	CONSTRAINT order_item_fk_1 FOREIGN KEY (vendor_id) REFERENCES online_restaurant.vendor(id)
);

-- Permissions

ALTER TABLE online_restaurant.order_item OWNER TO postgres;
GRANT ALL ON TABLE online_restaurant.order_item TO postgres;


-- online_restaurant.vendor_menu definition

-- Drop table

-- DROP TABLE online_restaurant.vendor_menu;

CREATE TABLE online_restaurant.vendor_menu (
	id int4 NOT NULL DEFAULT nextval('online_restaurant.food_item_id_seq'::regclass),
	"name" varchar NULL,
	veg bool NULL,
	category varchar NULL,
	price numeric NULL,
	description text NULL,
	category_id int8 NULL,
	images text NULL,
	vendor_id int8 NULL,
	CONSTRAINT food_item_pk PRIMARY KEY (id),
	CONSTRAINT food_item_fk FOREIGN KEY (category_id) REFERENCES online_restaurant.menu_category(id),
	CONSTRAINT vendor_menu_fk FOREIGN KEY (vendor_id) REFERENCES online_restaurant.vendor(id)
);

-- Permissions

ALTER TABLE online_restaurant.vendor_menu OWNER TO postgres;
GRANT ALL ON TABLE online_restaurant.vendor_menu TO postgres;




-- Permissions

GRANT ALL ON SCHEMA online_restaurant TO postgres;
