package com.project.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.KalendarDto;
import com.project.model.Pacijent;
import com.project.model.Pregled;
import com.project.model.ZauzetoVreme;
import com.project.service.LekarService;
import com.project.service.PregledService;
import com.project.service.ZauzetoVremeService;

@RestController
@RequestMapping("/api/lekar")
public class LekarController {

	@Autowired
    LekarService lekarService;
	
	@Autowired
    PregledService pregledService;
	
	@Autowired
    ZauzetoVremeService zauzetoVremeService;
	
	   @GetMapping(value ="/pacijentiklinike")
	   public Set<Pacijent> getPacijenti() {
		   /*
		   Pacijent k = new Pacijent("aaa@email.com", "aaa", "aaa", "aaa", "aaa", "aaa","aaa");
		   Set<Pacijent> lista = new HashSet<Pacijent>();
		   lista.add(k);
		   lista.add(k);
		   return lista;
		   */
		   return lekarService.getPacijentiKlinike(3L);
	   }
	   
	   @GetMapping(value ="/radnikalendar")
	   public Set<KalendarDto> getRadKal() {
		   Set<KalendarDto> kalendar = new HashSet<KalendarDto>();
		   LocalTime lt = LocalTime.of(00,00,00);
		   
		   for (Pregled pregled : pregledService.findAll()) {
			   if(pregled.getOperacija()) {
				   kalendar.add(new KalendarDto("Operacija " + pregled.getId(), LocalDateTime.of(pregled.getDatum(),pregled.getVremeOd()),LocalDateTime.of(pregled.getDatum(),pregled.getVremeDo())));
			   }else {
				   kalendar.add(new KalendarDto("Pregled " + pregled.getId(), LocalDateTime.of(pregled.getDatum(),pregled.getVremeOd()),LocalDateTime.of(pregled.getDatum(),pregled.getVremeDo())));
			   }
			   
		}
		   
		   for (ZauzetoVreme zv : zauzetoVremeService.findAll()) {
			   kalendar.add(new KalendarDto(zv.getTipZauzetosti().toString(), LocalDateTime.of(zv.getDatumOd(),lt),LocalDateTime.of(zv.getDatumDo(),lt)));
		}
		   
		   return kalendar;
	   }
	
}
