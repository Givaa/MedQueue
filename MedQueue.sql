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


INSERT INTO Struttura(nome,indirizzo,Numero_di_telefono) VALUES
('santobono','Via della Croce Rossa n. 8 Napoli (NA)','0812205111'),
('San Leonardo','Viale Europa n.8 Castellammare di Stabia NA','081872911');

INSERT INTO Impiegato VALUES
('FLTNGL99A14L845J','angelo99','angelo','afeltra','1999/01/14','a.afeltra12@studenti.unisa.it','3394487295',1),
('FCLNDR99C12B963C','andrea99','andrea','fucile','1999/03/12','andrea.fucile@studenti.unisa.it','3394487394',1),
('RPAGNN95D11A509B','giovannirapa95','giovanni','rapa','1995/01/16','g.rapa95@gmail.com','3394487293',2),
('ADRAMT99D13A587J','amato99','adriano','amato','1999/07/23','amatoadriano@gmail.com','3457892239',2);


INSERT INTO Operazione(Tipo_Operazione,Descrizione) VALUES
('Pagamento Ticket','Pagamento Ticket per visita medica'),
('Prenotazione Ambulatorio','Richiesta prenotazione ambulatorio');




