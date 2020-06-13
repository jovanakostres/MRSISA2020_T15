package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.PregledDto;
import com.project.model.Sala;
import com.project.model.ZahtevZaPregled;
import com.project.service.SalaService;
import com.project.service.ZahtevZaPregledService;

@RestController
@RequestMapping("/api/adminKlinike")
public class AdminKlinikeController {

	@Autowired
	ZahtevZaPregledService zzpService;
	
	@Autowired 
	SalaService salaService;
	
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
	
	@PostMapping(value ="/sale")
	public List<Sala> getSale(@RequestBody PregledDto pregled) {
		List<Sala> sale = salaService.findAll(pregled.getDatum(), pregled.getVremeOd());
		System.out.println(sale.size());
		for(Sala s : sale)
			System.out.println(s.getNaziv());
		return sale;
	}
	
	@PostMapping(value ="/zakazi_pregled")
	public void zakaziPregled(@RequestBody PregledDto pregled) {
		System.out.println(pregled.getSala());
	}
}
