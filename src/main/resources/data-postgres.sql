insert into tip_pregleda (id, ime, cena) values (1,'opsti',100);
insert into tip_pregleda (id, ime, cena) values (2,'stomatoloski',200);
insert into zauzeto_vreme (id, datum_od, datum_do, tip_zauzetosti) values (1,'2020.05.25','2020.05.30', 'GODISNJIODMOR');

insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type, lbo, promena_lozinke) values (1,'markomarkovic@gmail.com','$2y$10$PlcypQuCmM65W7MikuEIW.vBAMLar2o9UQroEu/fpCN/r06BltbOO','Marko', 'Markovic','Adresa 1','0600000000','P' ,'10000','false');
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type, lbo, promena_lozinke) values (2,'milanmilanovic@gmail.com','milanm','Milan', 'Milanovic','Adresa 2','0600000001', 'P' ,'10001','false');
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type, prosecna_ocena, pocetak_rada, kraj_rada, tip_pregleda_id, promena_lozinke) values (3,'jovanjovanovic@gmail.com','$2y$10$//fKrjX9u4wIu.Ql3EaJuev5BSNzziTYkq7mhonRydmXCMdWdhy0i','Jovan', 'Jovanovic','Adresa 3','0600000002', 'L' , 4.36, '10:00', '18:00', 1,'false');
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type, prosecna_ocena, pocetak_rada, kraj_rada, tip_pregleda_id, promena_lozinke) values (4,'anaanic@gmail.com','$2y$10$PlcypQuCmM65W7MikuEIW.vBAMLar2o9UQroEu/fpCN/r06BltbOO','Ana', 'Anic','Adresa 4','0600000003', 'L' , 4.1, '10:00', '18:00', 2,'false');
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type, promena_lozinke) values (5,'majamajic@gmail.com','majam','Maja', 'Majic','Adresa 5','0600000004','MS','false');
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, type, promena_lozinke) values (6,'peraperic@gmail.com','$2y$10$36aBCJZ3FP0tvemsTOLGAuznxAqP2AC9Eap4IWgrPrvRAukwgKUBO','Pera', 'Peric','Adresa 6','0600000005','AK','false');
insert into korisnici (id,email, lozinka, ime, prezime, adresa, broj, predefinisan, type, promena_lozinke) values (7,'milosmilosevic@gmail.com','$2y$10$GyEn7ZWk2YSiQJt2Uzvu.emIBgwvuqNmU7ho9IRUxo.DnNnXvDVUG','Milos', 'Milosevic','Adresa 7','0600000006','true','AKC','false');

update zauzeto_vreme set lekar_id = 3 where id = 1;

insert into klinike (id,naziv, adresa, opis, ocena) values (1,'Klinika 1','Adresa Klinike 1','Opis1', 4.5);
insert into klinike (id,naziv, adresa, opis, ocena) values (2,'Klinika 2','Adresa Klinike 2','Opis2', 3.0);

update korisnici set klinika_id = 1 where id = 3;
insert into klinika_pacijenti (klinika_id,pacijent_id) values (1,1);

update korisnici set klinika_id = 1 where id = 4;
update korisnici set klinika_id = 2 where id = 3;

insert into sale (id,naziv,klinika_id) values (1, 'Sala 1', 1);
insert into sale (id,naziv,klinika_id) values (2, 'Sala 2', 1);

insert into zdravstveni_kartoni (visina, tezina, krvna_grupa, pacijent_id) values (180, 70, 'A', 1);
insert into zdravstveni_kartoni (visina, tezina, krvna_grupa, pacijent_id) values (185, 80, '0', 2);

insert into pregledi (id, datum, vreme_od, vreme_do, cena, tip_pregleda_id,izvrsen, definisan,operacija) values (1, '2020.07.07','10:00','11:00',5000, 1, 'false', 'true','false');
update pregledi set lekar_id = 3,sala_id = 1,zk_pacijenta_pacijent_id=1 where id = 1;

insert into dijagnoze(id, naziv) values (1, 'Varicela-ovcije boginje');
insert into dijagnoze(id, naziv) values (2, 'Pojacana funkcija hipofize');
insert into lekovi(id, naziv) values (1, 'ACIKLOVIR 25 po 200mg');
insert into lekovi(id, naziv) values (2, 'SOMATULINE AUTOGEL,injekcija, špric,1 po 120mg');

insert into sifarnik (dijagnoza_id,lek_id) values (1,1);
insert into sifarnik (dijagnoza_id,lek_id) values (2,2);

