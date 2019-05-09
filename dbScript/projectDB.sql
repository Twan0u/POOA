CREATE TABLE IF NOT EXISTS Client
	( idNumber INT PRIMARY KEY AUTO_INCREMENT
	, phoneNumber VARCHAR(20) NOT NULL
	, clientName VARCHAR(50) NOT NULL
	, vatNumber INT
	, discount FLOAT NOT NULL
	)ENGINE = InnoDB;
    
CREATE TABLE IF NOT EXISTS Locality
	( idLocality INT PRIMARY KEY
	, postalCode VARCHAR(15) NOT NULL
	, localityName VARCHAR(50) NOT NULL
	)ENGINE = InnoDB;
	
CREATE TABLE IF NOT EXISTS BusinessUnit
	( idBusinessUnit INT PRIMARY KEY
	, clientNumber INT NOT NULL
	, locality INT NOT NULL
	, streetName VARCHAR(30) NOT NULL
	, streetNumber INT NOT NULL
	
	, CONSTRAINT clientNumber_fk FOREIGN KEY (clientNumber) REFERENCES Client(idNumber)
	, CONSTRAINT locality_fk FOREIGN KEY (locality) REFERENCES Locality(idLocality)
	)ENGINE = InnoDB;
    
	
CREATE TABLE IF NOT EXISTS ClientOrder
	( idNumber INT PRIMARY KEY
	, businessUnit INT
	, clientNumber INT NOT NULL
	, hasPriority TINYINT NOT NULL
	, orderDate DATE NOT NULL
	, state VARCHAR(50) NOT NULL
	, timeLimit DATE
	
	, CONSTRAINT businessUnit_fk FOREIGN KEY (businessUnit) REFERENCES BusinessUnit(idBusinessUnit)
	, CONSTRAINT clientNbr_fk FOREIGN KEY (clientNumber) REFERENCES Client(idNumber)
	)ENGINE = InnoDB;
    
CREATE TABLE IF NOT EXISTS Beer
	( idName VARCHAR(30) PRIMARY KEY
	, stockPrice INT NOT NULL
	, qtInStock INT NOT NULL
	, lowTreshold INT NOT NULL
	)ENGINE = InnoDB;
	
CREATE TABLE IF NOT EXISTS OrderLine
	( beerName VARCHAR(30)
	, orderNumber INT
	, quantity INT NOT NULL
	, price FLOAT NOT NULL
	
	, CONSTRAINT orderLine_pk PRIMARY KEY(beerName, orderNumber)
	, CONSTRAINT beerName_fk FOREIGN KEY (beerName) REFERENCES Beer(idName)
	, CONSTRAINT orderNumber_fk FOREIGN KEY (orderNumber) REFERENCES clientOrder(idNumber)
	)ENGINE = InnoDB;
	

INSERT INTO Beer(idName, stockPrice, qtInStock, lowTreshold)
	VALUES ('stella', 1.50, 123, 4);
INSERT INTO Beer(idName, stockPrice, qtInStock, lowTreshold)
	VALUES ('duvel', 1.45, 145, 6);
INSERT INTO Beer(idName, stockPrice, qtInStock, lowTreshold)
	VALUES ('heineken', 1.35, 198, 2);
INSERT INTO Beer(idName, stockPrice, qtInStock, lowTreshold)
	VALUES ('desperados', 1.25, 122, 7);
INSERT INTO Beer(idName, stockPrice, qtInStock, lowTreshold)
	VALUES ('carlsberg', 1.68, 145, 9);
INSERT INTO Beer(idName, stockPrice, qtInStock, lowTreshold)
	VALUES ('jupiler', 1.89, 115, 6);



INSERT INTO Client(idNumber, phoneNumber, clientName, vatNumber, discount)
	VALUES (165, '+32497/05.65.89', 'jean', 145668569, 0);
INSERT INTO Client(idNumber, phoneNumber, clientName, vatNumber, discount)
	VALUES (145, '+32497/15.45.89', 'marc', 468465542, 8);
INSERT INTO Client(idNumber, phoneNumber, clientName, vatNumber, discount)
	VALUES (198, '+32496/25.44.65', 'jacqueline', 455672494, 6.4);
INSERT INTO Client(idNumber, phoneNumber, clientName, vatNumber, discount)
	VALUES (25, '+32497/76.54.12', 'stephanie', NULL, 0);
INSERT INTO Client(idNumber, phoneNumber, clientName, vatNumber, discount)
	VALUES (73, '+32498/99.87.34', 'michel', 456872465, 5);
	

INSERT INTO Locality(idLocality, postalCode, localityName)
	VALUES (1, 1457, 'Walhain');
INSERT INTO Locality(idLocality, postalCode, localityName)
	VALUES (5, 1348, 'LLN');
INSERT INTO Locality(idLocality, postalCode, localityName)
	VALUES (8, 5000, 'Namur');
	
	
INSERT INTO BusinessUnit(idBusinessUnit, clientNumber, locality, streetName, streetNumber)
	VALUES (1, 165, 1, 'Rue Haute', 45);
INSERT INTO BusinessUnit(idBusinessUnit, clientNumber, locality, streetName, streetNumber)
	VALUES (2, 145, 5, 'Hirondelles', 70);
INSERT INTO BusinessUnit(idBusinessUnit, clientNumber, locality, streetName, streetNumber)
	VALUES (3, 198, 8, 'Saint jean', 22);
	

INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)
	VALUES (1, null, 165, 0, '2019-01-13', 'en préparation', null);
INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)
	VALUES (2, 1, 145, 1, '2018-12-29', 'livrée', '2019-01-25');
INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)
	VALUES (3, 2, 198, 0, '2018-05-14', 'dans le camion', null);
INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)
	VALUES (4, 3, 25, 0, '2019-02-24', 'validée', '2019-07-05');
INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)
	VALUES (5, 2, 73, 1, '2019-05-17', 'validée', '2019-06-14');


INSERT INTO OrderLine (beerName, orderNumber, quantity, price)
	VALUES ('stella', 1, 15, 1.6);
INSERT INTO OrderLine (beerName, orderNumber, quantity, price)
	VALUES ('duvel', 1, 10, 2.2);
INSERT INTO OrderLine (beerName, orderNumber, quantity, price)
	VALUES ('heineken', 1, 13, 2);
INSERT INTO OrderLine (beerName, orderNumber, quantity, price)
	VALUES ('desperados', 2, 16, 1.5);
INSERT INTO OrderLine (beerName, orderNumber, quantity, price)
	VALUES ('carlsberg', 2, 18, 1.6);
INSERT INTO OrderLine (beerName, orderNumber, quantity, price)
	VALUES ('stella', 3, 25, 1.2);
INSERT INTO OrderLine (beerName, orderNumber, quantity, price)
	VALUES ('desperados', 3, 50, 1.3);
INSERT INTO OrderLine (beerName, orderNumber, quantity, price)
	VALUES ('jupiler', 4, 40, 1.65);
INSERT INTO OrderLine (beerName, orderNumber, quantity, price)
	VALUES ('stella', 4, 20, 2.3);
INSERT INTO OrderLine (beerName, orderNumber, quantity, price)
	VALUES ('duvel', 5, 16, 2.8);
INSERT INTO OrderLine (beerName, orderNumber, quantity, price)
	VALUES ('jupiler', 5, 18, 1.7);




	
	
	
	
	
	
	
	
	
	
	
	
	
	