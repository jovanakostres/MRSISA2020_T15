insert into tip_pregleda (id, ime, cena) values (1,'opsti',100);
insert into tip_pregleda (id, ime, cena) values (2,'stomatoloski',200);

insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type, lbo) values (1,'markomarkovic@gmail.com','markom','Marko', 'Marković','Adresa 1','0600000000','P' ,'10000');
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type, lbo) values (2,'milanmilanovic@gmail.com','milanm','Milan', 'Milanovic','Adresa 2','0600000001', 'P' ,'10001');
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type, prosecna_ocena, pocetak_rada, kraj_rada, tip_pregleda_id) values (3,'jovanjovanovic@gmail.com','jovanj','Jovan', 'Jovanovic','Adresa 3','0600000002', 'L' , 4.36, '10:00', '18:00', 1);
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type, prosecna_ocena, pocetak_rada, kraj_rada, tip_pregleda_id) values (4,'anaanic@gmail.com','anaa','Ana', 'Anic','Adresa 4','0600000003', 'L' , 4.1, '10:00', '18:00', 2);
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type) values (5,'majamajic@gmail.com','majam','Maja', 'Majic','Adresa 5','0600000004','MS');
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type) values (6,'peraperic@gmail.com','perap','Pera', 'Peric','Adresa 6','0600000005','AK');
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type) values (7,'milosmilosevic@gmail.com','milosm','Milos', 'Milosevic','Adresa 7','0600000006','AKC');

insert into klinike (id,naziv, adresa, opis, ocena) values (1,'Klinika 1','Adresa Klinike 1','Opis1', 4.5);
insert into klinike (id,naziv, adresa, opis, ocena) values (2,'Klinika 2','Adresa Klinike 2','Opis2', 3.0);

update korisnici set klinika_id = 1 where id = 3;
insert into klinika_pacijenti (klinika_id,pacijent_id) values (1,1);

insert into sale (id,naziv,klinika_id) values (1, 'Sala 1', 1);

insert into zdravstveni_kartoni (visina, tezina, krvna_grupa, pacijent_id) values (180, 70, 'A', 1);

insert into pregledi (id, datum, vreme, trajanje_pregleda, cena, tip_pregleda_id,izvrsen) values (1, '2020.05.03','10:00',24,5000, 1, 'false');
update pregledi set lekar_id = 3,sala_id = 1,zk_pacijenta_pacijent_id=1 where id = 1;

