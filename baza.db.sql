CREATE TABLE IF NOT EXISTS "bus" (
	"id"	INTEGER,
	"manufacturer"	TEXT,
	"series"	TEXT,
	"number_of_seats"	INTEGER,
	"driver"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "driver" (
	"id"	INTEGER,
	"name"	TEXT,
	"surname"	TEXT,
	"jmb"	TEXT,
	"date_of_birth"	INTEGER,
	"date_of_employment"	INTEGER,
	PRIMARY KEY("id")
);
delete from bus;
delete from driver;

