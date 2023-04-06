------------------------------------------------
---- CSIPROJECT                 ----
------------------------------------------------

CREATE USER CSIPROJECT IDENTIFIED BY OMAR;
GRANT CREATE SESSION TO CSIPROJECT;
GRANT CREATE TABLE TO CSIPROJECT;
GRANT CREATE VIEW TO CSIPROJECT;
GRANT CREATE PROCEDURE TO CSIPROJECT;
GRANT CREATE SYNONYM TO CSIPROJECT;
GRANT CREATE SEQUENCE TO CSIPROJECT;
ALTER USER CSIPROJECT QUOTA UNLIMITED ON USERS;

connect CSIPROJECT/OMAR;

CREATE TABLE AGENT (
	AgentID VARCHAR(15),
	AgentName VARCHAR(100),
	AgentEmail VARCHAR(60),
	PRIMARY KEY(AgentID)
);

CREATE TABLE COMPANY (
	CompanyID VARCHAR(15),
	CompanyName	 VARCHAR(30),
	CommissionRate Decimal(5,2),
	OfficeCity VARCHAR(35),
	PRIMARY KEY(CompanyID));

CREATE TABLE OWNER (
	SSN VARCHAR(15) NOT NULL,
	OwnerName VARCHAR(100),
	Dependants Integer,
	Income numeric (8,2),
	Age Integer,
	Profession VARCHAR(30),
	PRIMARY KEY(SSN)
);

CREATE TABLE COMPANY_AGENT (
	CAID VARCHAR(15),
	AName VARCHAR(100),
	CName VARCHAR(30),
	AgentID VARCHAR(15),
	CompanyID VARCHAR(15),
	PRIMARY KEY(CAID),
	FOREIGN KEY (AgentID) REFERENCES AGENT(AgentID),
	FOREIGN KEY (CompanyID) REFERENCES COMPANY(CompanyID)
);

CREATE TABLE homes (
     HomeID INTEGER,
     Address CHAR(50) NOT NULL,
     Floorspace NUMBER(5,1),
     Floors NUMBER(1),
     Bedrooms NUMBER(2),
     FullBathrooms NUMBER(2),
     HalfBathrooms NUMBER(2),
     Landsize NUMBER(6,2),
     YearConstructed NUMBER(4),
     city VARCHAR2(40),
     zip NUMBER(5),
     schoolDistrict VARCHAR2(40),
     homePrice NUMBER(10),
     CHECK (homePrice > 0),
     CHECK (Floorspace > 0),
     CHECK (Floors > 0),
     CHECK (YearConstructed > 1900),
     PRIMARY KEY (HomeID)
);

CREATE TABLE TRADING_HISTORY (
	TransactionID VARCHAR(15),
	Status VARCHAR(20),
	agentID VARCHAR(15),
	ownerSSN VARCHAR(15),
	homeID Integer,
	PRIMARY KEY(TransactionID),
	FOREIGN KEY (agentID) REFERENCES AGENT(AgentID),
	FOREIGN KEY (ownerSSN) REFERENCES OWNER(SSN),
	FOREIGN KEY (homeID) REFERENCES HOMES(HomeID)
);


CREATE TABLE location (
      locationID Integer,
      PRIMARY KEY(locationID),
      address VARCHAR2(40),
      address2 VARCHAR2(40),
      city VARCHAR2(30),
      state VARCHAR2(20),
      zipCode VARCHAR2(5),
      country VARCHAR2(40),
      homeID Integer,
      FOREIGN KEY (homeID) REFERENCES HOMES(HomeID)
);

CREATE TABLE appliances (
      modelNumber NUMBER(8) CHECK( modelNumber > 0 ),
      PRIMARY KEY(modelNumber),
      make VARCHAR2(40),
      name VARCHAR2(40),
      UNIQUE(make,name),
      description VARCHAR(1000),
      homeID Integer,
      FOREIGN KEY (homeID) REFERENCES HOMES(HomeID)
);
