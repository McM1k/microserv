DROP TABLE IF EXISTS cats;
DROP TABLE IF EXISTS owners;
DROP TABLE IF EXISTS races;

	CREATE TABLE cats (
	  id INT AUTO_INCREMENT  PRIMARY KEY,
	  name VARCHAR(250) NOT NULL,
	  race INT NOT NULL,
	  color ENUM(white, black, orange) NOT NULL,
	  owner INT DEFAULT NULL
	);

	CREATE TABLE owners (
     	 id INT AUTO_INCREMENT  PRIMARY KEY,
     	 name VARCHAR(250) NOT NULL
    );

    CREATE TABLE races (
        id INT AUTO_INCREMENT  PRIMARY KEY,
        name VARCHAR(250) NOT NULL
    );

	INSERT INTO cats (name, race, color, owner) VALUES
	  ('no_owner', 1, white),
	  ('hamb', 2, orange, 1),
	  ('catto', 3, black, 1),
	  ('garfield', 1, orange, 2);

	INSERT INTO owners (name) VALUES
      ('meme'),
      ('john');

	INSERT INTO races (name) VALUES
	  ('normal'),
	  ('siamese'),
	  ('goblin');