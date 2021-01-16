DROP DATABASE IF EXISTS MedQueue;
CREATE DATABASE MedQueue;
USE MedQueue;

CREATE TABLE Utente(               
CodiceFiscale varchar(25) not null,
Password varchar(255) not null,
Nome varchar(255) not null,
Cognome varchar(255) not null,
Data_di_Nascita date not null,
Indirizzo_email varchar(255) not null,
Numero_di_telefono varchar(255) not null,
primary key(CodiceFiscale) );

CREATE TABLE Struttura(
Id integer auto_increment not null,
Nome varchar(255) not null,
Indirizzo varchar(255) not null,
Numero_di_telefono varchar(255) not null,
primary key(id) );

CREATE TABLE Impiegato(
CodiceFiscale varchar(25) not null,
Password varchar(255) not null,
Nome varchar(255) not null,
Cognome varchar(255) not null,
Data_di_Nascita date not null,
Indirizzo_email varchar(255) not null,
Numero_di_telefono varchar(255) not null,
Id_Struttura integer not null,
primary key(CodiceFiscale) ,
foreign key(Id_Struttura) references Struttura(Id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE Operazione(
Id integer auto_increment not null,
Tipo_Operazione varchar(255) not null,
Descrizione varchar(255) not null,
primary key(id) );



CREATE TABLE Prenotazione(
Id integer auto_increment not null,
Data date not null,
Ora time not null,
Convalida bool not null,
CodiceFiscale varchar(255) not null,
Id_Operazione integer not null,
Id_Struttura integer not null,
primary key(Id),
foreign key(CodiceFiscale) references Utente(CodiceFiscale) ON UPDATE CASCADE ON DELETE CASCADE,
foreign key(Id_Operazione) references Operazione(Id) ON UPDATE CASCADE ON DELETE CASCADE,
foreign key(Id_Struttura) references Struttura(Id) ON UPDATE CASCADE ON DELETE CASCADE );

CREATE TABLE Ambulatorio(
Id integer auto_increment not null,
Nome varchar(255) not null,
Id_Struttura integer not null,
primary key(Id),
foreign key(Id_Struttura) references Struttura(Id) ON UPDATE CASCADE ON DELETE CASCADE );
