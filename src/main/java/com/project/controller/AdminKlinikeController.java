package com.project.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.PregledDto;
import com.project.model.Lekar;
import com.project.model.Sala;
import com.project.model.ZahtevZaPregled;
import com.project.service.EmailService;
import com.project.service.LekarService;
import com.project.service.SalaService;
import com.project.service.ZahtevZaPregledService;

@RestController
@RequestMapping("/api/adminKlinike")
public class AdminKlinikeController {

	@Autowired
	ZahtevZaPregledService zzpService;
	
	@Autowired 
	SalaService salaService;
	
	@Autowired 
	LekarService lekarService;
	
	@Autowired
	EmailService emailService;
	
	
	@GetMapping(value ="/pregledi")
	public ArrayList<PregledDto> getPregled() {
		Set<ZahtevZaPregled> pregledi = zzpService.findBySala();
		ArrayList<PregledDto> pp = new ArrayList<PregledDto>();
		for(ZahtevZaPregled zzp : pregledi)
		{
			pp.add(new PregledDto(zzp.getId(), zzp.getLekar().getIme(), zzp.getLekar().getPrezime(), null, zzp.getDatum(), zzp.getVremeOd(),
					null, zzp.getCena(), false, zzp.getTipPregleda().getIme()));
		}
		return pp;
	}
	
	@PostMapping(value ="/proveri_preglede")
	public void proveriPreglede() throws MailException, InterruptedException {
		LocalDate yesterday = LocalDate.now().minusDays(1);
		LocalTime time = LocalTime.now();
		Set<ZahtevZaPregled> pregledi = zzpService.findBySala();
		for(ZahtevZaPregled zzp : pregledi)
		{
			if(zzp.getDatum().compareTo(yesterday)==0 && time.compareTo(zzp.getVremeOd())>0)
			{
				List<Sala> sale = salaService.findAll(zzp.getDatum(), zzp.getVremeOd());
				zzp.setSala(sale.get(0));
				zzpService.save(zzp);
				emailService.sendDodeljenaSala(zzp);
			}
		}
	}
	
	@PostMapping(value ="/sale")
	public ResponseEntity getSale(@RequestBody PregledDto pregled) {
		System.out.println(pregled);
		List<Sala> sale = salaService.findAll(pregled.getDatum(), pregled.getVremeOd());
		if(sale.size()!=0)
			return new ResponseEntity(sale, HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping(value ="/termini")
	public ResponseEntity getTermini(@RequestBody String pregled) {
		String ime = pregled.split(":")[1].split(",")[0].replaceAll("\"", "");
		String prezime = pregled.split(":")[2].split(",")[0].replaceAll("\"", "");
		LocalDate date = LocalDate.parse(pregled.split(":")[3].replaceAll("\"", "").replace("}", ""));
		Lekar l = lekarService.findByNameAndSurname(ime, prezime);
		System.out.println((l == null) + "  " + date);
		return new ResponseEntity(PacijentController.kreirajTermine(date, l), HttpStatus.OK);		
		//return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping(value ="/zakazi_pregled")
	public void zakaziPregled(@RequestBody PregledDto pregled) {
		System.out.println(pregled);
		ZahtevZaPregled zzp = zzpService.findById(pregled.getId());
		Sala sala = salaService.findByNaziv(pregled.getSala());
		zzp.setSala(sala);
		zzp = zzpService.updateById(zzp);
		try {
			emailService.sendDodeljenaSala(zzp);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
