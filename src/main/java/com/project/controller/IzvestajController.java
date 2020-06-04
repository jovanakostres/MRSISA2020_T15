package com.project.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.config.CustomUserDetails;
import com.project.dto.IzvestajDto;
import com.project.model.Dijagnoza;
import com.project.model.Izvestaj;
import com.project.model.Lek;
import com.project.model.MedicinskaSestra;
import com.project.model.Pregled;
import com.project.model.Recept;
import com.project.service.DijagnozaService;
import com.project.service.IzvestajService;
import com.project.service.MedicinskaSestraService;
import com.project.service.PregledService;
import com.project.service.ReceptService;
import com.project.service.ZdravstveniKartonService;
import com.project.service.LekService;
import com.project.service.LekarService;

@RestController
@RequestMapping("/api/izvestaj")
public class IzvestajController {

	@Autowired
	IzvestajService izvestajService;

	@Autowired
	PregledService pregledService;

	@Autowired
	MedicinskaSestraService medicinskaSestraService;

	@Autowired
	ReceptService receptService;

	@Autowired
	LekService lekService;

	@Autowired
	LekarService lekarService;

	@Autowired
	DijagnozaService dijagnozaService;

	@Autowired
	ZdravstveniKartonService zdravstveniKartonService;

	@PostMapping(value = "/sacuvaj")
	public ResponseEntity sacuvajIzvestaj(@RequestBody IzvestajDto izvestajDto) {
		// System.out.println(pacijent.getIme() + " " + pacijent.getPrezime() );
		try {
			System.out.println("PROSAOOO");
			// System.out.println(izvestajDto.getPregledId());
			Pregled pregled = pregledService.findById(Long.parseLong(izvestajDto.getPregledId()));
			// System.out.println(pregled.getId());
			MedicinskaSestra medSestra = medicinskaSestraService.findById(5L);
			Recept recept = new Recept(false);
			for (String idLek : izvestajDto.getLekoviIds()) {
				Lek l = lekService.findById(Long.parseLong(idLek));
				recept.getLekovi().add(l);
			}
			recept.setMedicinskaSestra(medSestra);

			Dijagnoza dijagnoza = dijagnozaService.findById(Long.parseLong(izvestajDto.getDijagnozaId()));

			Izvestaj izvestaj = new Izvestaj(izvestajDto.getInformacije(), pregled, recept, dijagnoza);
			recept.setIzvestaj(izvestaj);
			pregled.setIzvrsen(true);

			pregledService.save(pregled);
			receptService.save(recept);
			izvestajService.save(izvestaj);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/sacuvaj_izmene")
	public ResponseEntity sacuvajIzvestajIzmene(@RequestBody IzvestajDto izvestajDto) {
		// System.out.println(pacijent.getIme() + " " + pacijent.getPrezime() );
		try {
			Izvestaj i = izvestajService.findById(Long.parseLong(izvestajDto.getId()));
			Set <Lek> lekovi = new HashSet<Lek>();
			
			if(!i.getRecept().isOveren()) {
			for (String idLek : izvestajDto.getLekoviIds()) {
				Lek l = lekService.findById(Long.parseLong(idLek));
				lekovi.add(l);
				}
				i.getRecept().setLekovi(lekovi);
			}
			
			i.setInformacije(izvestajDto.getInformacije());
			
			Dijagnoza d = dijagnozaService.findById(Long.parseLong(izvestajDto.getDijagnozaId()));
			i.setDijagnoza(d);
			
			izvestajService.save(i);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/izvestaji_lekar/{id}")
	public Set<IzvestajDto> getIzvestajLekar(@PathVariable Long id) {
		try {
			CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			List<Pregled> listapr = pregledService
					.findByLekarPacijent(lekarService.findByEmail(userDetails.getUsername()).getId(), id);
			Set<IzvestajDto> izvestaji = new HashSet<IzvestajDto>();
			for (Pregled pr : listapr) {
				System.out.println(pr.getId());
				if(izvestajService.existsById(id)) {
				Izvestaj i = izvestajService.findById(pr.getId());
				izvestaji.add(new IzvestajDto(i.getId().toString(), pr.getId().toString(), i.getInformacije(),
						i.getDijagnoza().getId().toString(), pr.getDatum(),pr.getVremeOd(),pr.getVremeDo()));
			
				}
			}
			return izvestaji;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@GetMapping(value = "/izvestaj_izmena/{id}")
	public IzvestajDto getIzvestajIzmena(@PathVariable Long id) {
		try {
			Izvestaj i = izvestajService.findById(id);
			IzvestajDto izvestajdto;
			List<String> lekoviids = new ArrayList<String>();
			for (Lek l : i.getRecept().getLekovi()) {
				lekoviids.add(l.getId().toString());
			}
			izvestajdto = new IzvestajDto(i.getId().toString(),i.getPregled().getId().toString(),i.getInformacije(),i.getDijagnoza().getId().toString(),lekoviids);
			return izvestajdto;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
