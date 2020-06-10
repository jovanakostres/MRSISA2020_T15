package com.project.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.PregledDto;
import com.project.model.ZahtevZaPregled;
import com.project.service.ZahtevZaPregledService;

@RestController
@RequestMapping("/api/adminKlinike")
public class AdminKlinikeController {

	@Autowired
	ZahtevZaPregledService zzpService;
	
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
}
