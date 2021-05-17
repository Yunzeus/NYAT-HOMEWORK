-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

-- DROP SEQUENCE public.users_id_seq;

CREATE SEQUENCE public.users_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.temperature_id_seq;

CREATE SEQUENCE public.temperature_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;-- public."Users" definition

-- Drop table

-- DROP TABLE public."Users";

CREATE TABLE public."Users" (
	id int4 NOT NULL DEFAULT nextval('users_id_seq'::regclass),
	name varchar NOT NULL,
	password varchar NOT NULL,
	age int2 NULL
);


-- public."Temperature" definition

-- Drop table

-- DROP TABLE public."Temperature";

CREATE TABLE public."Temperature" (
	id int4 NOT NULL DEFAULT nextval('temperature_id_seq'::regclass),
	temperature int4 NOT NULL,
	saat timestamp(0) NOT NULL
);

