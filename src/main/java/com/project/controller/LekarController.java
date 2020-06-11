package com.project.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.config.CustomUserDetails;
import com.project.dto.KalendarDto;
import com.project.model.Pacijent;
import com.project.model.Pregled;
import com.project.model.ZahtevZaOdmor;
import com.project.model.ZauzetoVreme;
import com.project.service.LekarService;
import com.project.service.PregledService;
import com.project.service.ZahtevZaOdmorService;
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
	
	@Autowired
    ZahtevZaOdmorService zahtevZaOdmorService;
	
	   @GetMapping(value ="/pacijentiklinike")
	   public Set<Pacijent> getPacijenti() {
		   CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   /*
		   Pacijent k = new Pacijent("aaa@email.com", "aaa", "aaa", "aaa", "aaa", "aaa","aaa");
		   Set<Pacijent> lista = new HashSet<Pacijent>();
		   lista.add(k);
		   lista.add(k);
		   return lista;
		   */
		   return lekarService.getPacijentiKlinike(lekarService.findByEmail(userDetails.getUsername()).getId());
	   }
	   
	   @GetMapping(value ="/radnikalendar")
	   public Set<KalendarDto> getRadKal() {
		   Set<KalendarDto> kalendar = new HashSet<KalendarDto>();
		   LocalTime lt = LocalTime.of(00,00,00);
		   CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   
		   for (Pregled pregled : lekarService.findByEmail(userDetails.getUsername()).getPregledi()) {
			   if(pregled.getOperacija()) {
				   kalendar.add(new KalendarDto("Operacija " + pregled.getId(), LocalDateTime.of(pregled.getDatum(),pregled.getVremeOd()),LocalDateTime.of(pregled.getDatum(),pregled.getVremeDo())));
			   }else {
				   kalendar.add(new KalendarDto("Pregled " + pregled.getId(), LocalDateTime.of(pregled.getDatum(),pregled.getVremeOd()),LocalDateTime.of(pregled.getDatum(),pregled.getVremeDo())));
			   }
			   
		}
		   
		   for (ZauzetoVreme zv : lekarService.findByEmail(userDetails.getUsername()).getZauzetoVreme()) {
			   kalendar.add(new KalendarDto(zv.getTipZauzetosti().toString(), LocalDateTime.of(zv.getDatumOd(),lt),LocalDateTime.of(zv.getDatumDo(),lt)));
		}
		   
		   return kalendar;
	   }
	   
	   @RequestMapping(value = "/zahtev_goo", method = RequestMethod.POST)
	   public ResponseEntity saveZahtev(@RequestBody ZahtevZaOdmor zahtev) {
	   	try {
	   		System.out.println("PROSAOOO");
	   		zahtevZaOdmorService.save(zahtev);
	   		return new ResponseEntity(HttpStatus.OK);
	   	}
	   	catch(Exception ex){
	   		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	   	}
	    
	   }
}
