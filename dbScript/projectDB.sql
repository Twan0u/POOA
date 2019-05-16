CREATE TABLE IF NOT EXISTS Client
	( idNumber INT PRIMARY KEY
	, phoneNumber VARCHAR(18) NOT NULL
	, clientName VARCHAR(64) NOT NULL
	, vatNumber VARCHAR(32)
	, discount DOUBLE NOT NULL
	)ENGINE = InnoDB;
    
CREATE TABLE IF NOT EXISTS Locality
	( idLocality INT PRIMARY KEY
	, localityName VARCHAR(50) NOT NULL
	, postalCode VARCHAR(15) NOT NULL
	)ENGINE = InnoDB;
	
CREATE TABLE IF NOT EXISTS BusinessUnit
	( idBusinessUnit INT PRIMARY KEY
	, clientNumber INT NOT NULL
	, locality INT NOT NULL
	, streetName VARCHAR(40) NOT NULL
	, streetNumber VARCHAR(5) NOT NULL
	
	, CONSTRAINT clientNumber_fk FOREIGN KEY (clientNumber) REFERENCES Client(idNumber)
	, CONSTRAINT locality_fk FOREIGN KEY (locality) REFERENCES Locality(idLocality)
	)ENGINE = InnoDB;
    
    
CREATE TABLE IF NOT EXISTS ClientOrder
	( idNumber INT PRIMARY KEY AUTO_INCREMENT
	, businessUnit INT
	, clientNumber INT NOT NULL
	, hasPriority TINYINT NOT NULL
	, orderDate DATE NOT NULL
	, state VARCHAR(30) NOT NULL
	, timeLimit INT
	
	, CONSTRAINT businessUnit_fk FOREIGN KEY (businessUnit) REFERENCES BusinessUnit(idBusinessUnit)
	, CONSTRAINT clientNbr_fk FOREIGN KEY (clientNumber) REFERENCES Client(idNumber)
    , CONSTRAINT state_chk CHECK (state IN ('new', 'prepared', 'delivered', 'paid'))
	)ENGINE = InnoDB;
    
CREATE TABLE IF NOT EXISTS Beer
	( idName VARCHAR(64) PRIMARY KEY
	, stockPrice DOUBLE NOT NULL
	, qtInStock INT NOT NULL CHECK (qtInStock > -1)
	, lowTreshold INT NOT NULL
	)ENGINE = InnoDB;
	
CREATE TABLE IF NOT EXISTS OrderLine
	( idOrderLigne INT AUTO_INCREMENT
	, orderNumber INT
	, quantity INT NOT NULL CHECK (quantity > 0)
	, price DOUBLE NOT NULL
	, beerName VARCHAR(64)
	
	, CONSTRAINT orderLine_pk PRIMARY KEY(idOrderLigne)
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
	INSERT INTO Beer(idName, stockPrice, qtInStock, lowTreshold)
	VALUES ('bush', 2.12, 15, 30);


INSERT INTO Client(idNumber, phoneNumber, clientName, vatNumber, discount)
	VALUES (165, '+32497/05.65.89', 'jean', 'BE145668569', 0);
INSERT INTO Client(idNumber, phoneNumber, clientName, vatNumber, discount)
	VALUES (145, '+32497/15.45.89', 'marc', 'BE468465542', 8);
INSERT INTO Client(idNumber, phoneNumber, clientName, vatNumber, discount)
	VALUES (198, '+32496/25.44.65', 'jacqueline', 'BE455672494', 6.4);
INSERT INTO Client(idNumber, phoneNumber, clientName, vatNumber, discount)
	VALUES (25, '+32497/76.54.12', 'stephanie', NULL, 0);
INSERT INTO Client(idNumber, phoneNumber, clientName, vatNumber, discount)
	VALUES (73, '+32498/99.87.34', 'michel', 'BE456872465', 5);
	

INSERT INTO Locality(idLocality, postalCode, localityName)
	VALUES (1, 1457, 'Walhain');
INSERT INTO Locality(idLocality, postalCode, localityName)
	VALUES (2, 1348, 'LLN');
INSERT INTO Locality(idLocality, postalCode, localityName)
	VALUES (3, 5000, 'Namur');
	
	
INSERT INTO BusinessUnit(idBusinessUnit, clientNumber, locality, streetName, streetNumber)
	VALUES (1, 165, 1, 'Rue Haute', '45');
INSERT INTO BusinessUnit(idBusinessUnit, clientNumber, locality, streetName, streetNumber)
	VALUES (2, 145, 2, 'Rue des Hirondelles', '70');
INSERT INTO BusinessUnit(idBusinessUnit, clientNumber, locality, streetName, streetNumber)
	VALUES (3, 198, 3, 'Rue Saint jean', '22');
INSERT INTO BusinessUnit(idBusinessUnit, clientNumber, locality, streetName, streetNumber)
	VALUES (4, 25, 1, 'Rue du youkoulele', '36');
INSERT INTO BusinessUnit(idBusinessUnit, clientNumber, locality, streetName, streetNumber)
	VALUES (5, 73, 2, 'Rue de la pistache', '14');
	

INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)
	VALUES (1, null, 165, 0, '2019-01-13', "new", null);
INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)
	VALUES (2, 1, 165, 1, '2018-12-29', "prepared", 15);
INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)
	VALUES (3, 2, 145, 0, '2018-05-14', "delivered", null);
INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)
	VALUES (4, 3, 198, 0, '2019-02-24', "paid", 30);
INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)
	VALUES (5, 5, 25, 1, '2019-05-17', "prepared", 23);


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