DROP TABLE IF EXISTS cats;
DROP TABLE IF EXISTS owners;
DROP TABLE IF EXISTS races;

	CREATE TABLE cats (
	  id INTEGER AUTO_INCREMENT  PRIMARY KEY,
	  name VARCHAR(250) NOT NULL,
	  race INTEGER NOT NULL,
	  color VARCHAR(250) NOT NULL,
	  owner INT DEFAULT NULL
	);

	CREATE TABLE owners (
     	 id INTEGER AUTO_INCREMENT  PRIMARY KEY,
     	 name VARCHAR(250) NOT NULL
    );

    CREATE TABLE races (
        id INTEGER AUTO_INCREMENT  PRIMARY KEY,
        name VARCHAR(250) NOT NULL
    );

	INSERT INTO cats (id, name, race, color, owner) VALUES
	  (1, 'no_owner', 1, 'white', NULL),
	  (2, 'hamb', 2, 'orange', 1),
	  (3, 'catto', 3, 'black', 1),
	  (4, 'garfield', 1, 'orange', 2);

	INSERT INTO owners (id, name) VALUES
      (1, 'meme'),
      (2, 'john');

	INSERT INTO races (id, name) VALUES
	  (1, 'normal'),
	  (2, 'siamese'),
	  (3, 'goblin');