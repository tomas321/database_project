CREATE TABLE agents (
	id serial not null primary key,
	name varchar(30) not null,
	phone char(14) not null,
	started_at date not null,
	rating int not null -- has a typedef 1,2,3,4,5
	-- num_of_sold_estates hard for generating data with sold estates table
);

CREATE TABLE clients (
	id serial not null primary key,
	name varchar(30) not null,
	phone char(14) not null,
	email varchar(50) not null
);

CREATE TABLE sold_estates (
	id serial not null primary key,
	agent_id not null references agents(id),
	sold_to varchar(30) not null,
	price float not null,
	sold_date date not null
);

CREATE TABLE arrangements (
	id serial not null primary key,
	balcony boolean not null default true,
	rooms int not null,
	toilets int not null,
	floors int not null,
	furniture boolean not null default false,
	pool boolean not null default false,
	garden boolean not null default false
);

CREATE TABLE locations (
	id serial not null primary key,	
	city varchar(30) not null,
	street varchar(30) not null 
);

CREATE TABLE estates (
	id serial not null primary key,
	arrangement_id bigint not null references arrangements(id),
	location_id bigint not null references locations(id),
	name text not null,
	status int not null, -- has a typedef 1 = new,2 = second hand
	category int not null, -- has a typedef 1 = house ,2 = flat ,3 = villa
	year date not null,
	land int not null,
	price float not null
);

CREATE TABLE open_houses(
	id serial not null primary key,
	client_id bigint not null references clients(id),
	estate_id bigint not null references estates(id),
	agent_id bigint not null references agents(id),
	time_at timestamp without time zone not null
);








