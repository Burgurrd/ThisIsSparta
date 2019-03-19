USE treningsdagbok;

DROP TABLE IF EXISTS KroppsDelAvGruppe;
DROP TABLE IF EXISTS ApparatDelAvGruppe;
DROP TABLE IF EXISTS KroppsØvelseIØkt;
DROP TABLE IF EXISTS ApparatØvelseIØkt;
DROP TABLE IF EXISTS ApparatØvelse;
DROP TABLE IF EXISTS ØvelsesGruppe;
DROP TABLE IF EXISTS Apparat;
DROP TABLE IF EXISTS KroppsØvelse;
DROP TABLE IF EXISTS Treningsøkt;



CREATE TABLE Treningsøkt(
ØktID INT AUTO_INCREMENT,
Dato DATE,
StartTid TIME,
SluttTid TIME,
Form INT CHECK (Form BETWEEN 1 AND 10),
Prestasjon INT CHECK (Prestasjon BETWEEN 1 AND 10),
Notat TEXT,
PRIMARY KEY (ØktID)
);

CREATE TABLE KroppsØvelse(
KroppsØvelseID INT AUTO_INCREMENT,
Navn VARCHAR(50),
Beskrivelse TEXT,
PRIMARY KEY (KroppsØvelseID)
);

CREATE TABLE Apparat(
ApparatID INT AUTO_INCREMENT,
Navn VARCHAR(50),
Funksjon VARCHAR(100),
PRIMARY KEY (ApparatID)
);

CREATE TABLE ØvelsesGruppe(
ØvelsesGruppeID INT AUTO_INCREMENT,
Navn VARCHAR(50),
PRIMARY KEY (ØvelsesGruppeID)
);


CREATE TABLE ApparatØvelse(
ApparatØvelseID INT AUTO_INCREMENT,
Navn VARCHAR(50),
Kilo DOUBLE,
Sett INT,
ApparatID INT,
PRIMARY KEY (ApparatØvelseID),
FOREIGN KEY (ApparatID)
REFERENCES Apparat(ApparatID)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE ApparatØvelseIØkt(
ØktID INT,
ApparatØvelseID INT,
PRIMARY KEY (ØktID, ApparatØvelseID),
FOREIGN KEY (ØktID)
REFERENCES Treningsøkt(ØktID)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (ApparatØvelseID)
REFERENCES ApparatØvelse(ApparatØvelseID)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE KroppsØvelseIØkt(
ØktID INT,
KroppsØvelseID INT,
PRIMARY KEY (ØktID, KroppsØvelseID),
FOREIGN KEY (ØktID)
REFERENCES Treningsøkt(ØktID)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (KroppsØvelseID)
REFERENCES KroppsØvelse(KroppsØvelseID)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE ApparatDelAvGruppe(
ApparatØvelseID INT,
ØvelsesGruppeID INT,
PRIMARY KEY (ApparatØvelseID, ØvelsesGruppeID),
FOREIGN KEY (ApparatØvelseID)
REFERENCES ApparatØvelse(ApparatØvelseID)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (ØvelsesGruppeID)
REFERENCES ØvelsesGruppe(ØvelsesGruppeID)
ON DELETE CASCADE
ON UPDATE CASCADE
);


CREATE TABLE KroppsDelAvGruppe(
KroppsØvelseID INT,
ØvelsesGruppeID INT,
PRIMARY KEY (KroppsØvelseID, ØvelsesGruppeID),
FOREIGN KEY (KroppsØvelseID)
REFERENCES KroppsØvelse(KroppsØvelseID)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (ØvelsesGruppeID)
REFERENCES ØvelsesGruppe(ØvelsesGruppeID)
ON DELETE CASCADE
ON UPDATE CASCADE
);
