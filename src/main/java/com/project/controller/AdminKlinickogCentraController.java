package com.project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.config.CustomUserDetails;
import com.project.dto.AdminKlinickogCentraDto;
import com.project.dto.AdminKlinikeDto;
import com.project.dto.KlinikaDto;
import com.project.dto.SifrarnikDto;
import com.project.dto.ZahtevZaRegistracijuDto;
import com.project.model.AdminKlinickogCentra;
import com.project.model.AdminKlinike;
import com.project.model.Dijagnoza;
import com.project.model.Klinika;
import com.project.model.Lek;
import com.project.model.ZahtevZaRegistraciju;
import com.project.service.AdminKlinickogCentraService;
import com.project.service.AdminKlinikeService;
import com.project.service.DijagnozaService;
import com.project.service.KlinikaService;
import com.project.service.LekService;
import com.project.service.ZahtevZaRegistracijuService;

@RestController
@RequestMapping("/api/akc")
public class AdminKlinickogCentraController {

	@Autowired
	AdminKlinickogCentraService akcService;

	@Autowired
	AdminKlinikeService akService;

	@Autowired
	KlinikaService klinikaService;

	@Autowired
	ZahtevZaRegistracijuService zahtevZaRegistracijuService;

	@Autowired
	DijagnozaService dijagnozaService;

	@Autowired
	LekService lekService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping(value = "/zahteviZaReg")
	public Set<ZahtevZaRegistracijuDto> getZahtevi() {
		Set<ZahtevZaRegistracijuDto> zahtevi = new HashSet<ZahtevZaRegistracijuDto>();

		for (ZahtevZaRegistraciju zh : zahtevZaRegistracijuService.findAll()) {
			zahtevi.add(new ZahtevZaRegistracijuDto(zh.getId(), zh.getEmail(), zh.getIme(), zh.getPrezime(),
					zh.getDatum(), zh.getVreme()));
		}
		return zahtevi;
	}

	@GetMapping(value = "/sifrarnik")
	public Set<SifrarnikDto> getSifrarnik() {
		Set<SifrarnikDto> sifrarnik = new HashSet<SifrarnikDto>();

		for (Dijagnoza d : dijagnozaService.findAll()) {
			for (Lek l : d.getLekovi()) {
				sifrarnik.add(new SifrarnikDto(l.getId(), d.getId(), l.getNaziv(), d.getNaziv()));
			}
		}
		return sifrarnik;
	}

	@GetMapping(value = "/adminiklinike")
	public Set<AdminKlinikeDto> getAdmineKlinike() {
		Set<AdminKlinikeDto> admini = new HashSet<AdminKlinikeDto>();

		for (AdminKlinike ak : akService.getAdminiBezKlinike()) {
			admini.add(new AdminKlinikeDto(ak.getId(), ak.getIme(), ak.getPrezime()));
		}

		return admini;
	}

	@GetMapping(value = "/klinike")
	public List<KlinikaDto> getKlinike() {
		List<KlinikaDto> klinike = new ArrayList<KlinikaDto>();

		for (Klinika k : klinikaService.findAll()) {
			klinike.add(new KlinikaDto(k.getId(), k.getNaziv()));
		}

		return klinike;
	}

	@PostMapping(value = "/dodaj_u_sifarnik")
	public SifrarnikDto addToSifrarnik(@RequestBody SifrarnikDto sif) {
		try {
			Dijagnoza d = dijagnozaService.findByNaziv(sif.getImeD());
			Lek l = lekService.findByNaziv(sif.getImeL());
			if (d.getLekovi().contains(l))
				return null;

			d.getLekovi().add(l);
			dijagnozaService.save(d);
			return new SifrarnikDto(l.getId(), d.getId(), l.getNaziv(), d.getNaziv());
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	@PostMapping(value = "/registracija_klinike")
	public ResponseEntity registracijaKlinike(@RequestBody KlinikaDto kl) {
		try {
			Klinika k = new Klinika(kl.getNaziv(), kl.getAdresa(), kl.getOpis(), 0.0);

			klinikaService.save(k);

			for (Long idK : kl.getAdmini()) {
				AdminKlinike l = akService.findById(idK);
				l.setKlinika(k);
				akService.save(l);
			}

			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception ex) {
			System.out.println(ex);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/registracija_akc")
	public String registracijaAkc(@RequestBody AdminKlinickogCentraDto akc) {
		try {
			CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (!akcService.findByEmail(userDetails.getUsername()).isPredefinisan()) {
				return "nema prava";
			}
			String loz = passwordEncoder.encode(akc.getIme() + akc.getPrezime());
			akcService.save(new AdminKlinickogCentra(akc.getEmail(), loz, akc.getIme(),
					akc.getPrezime(), akc.getAdresa(), akc.getBroj(),true, false));

			return "ok";
		} catch (Exception ex) {
			System.out.println(ex);
			return "pogresan email";
		}
	}

	@PostMapping(value = "/registracija_ak")
	public String registracijaAk(@RequestBody AdminKlinikeDto ak) {
		try {
			String loz = passwordEncoder.encode(ak.getIme() + ak.getPrezime());
			if (ak.getIdK() != null) {
				Klinika k = klinikaService.findById(ak.getIdK());
				akService.save(new AdminKlinike(ak.getEmail(), loz, ak.getIme(),
						ak.getPrezime(), ak.getAdresa(), ak.getBroj(), true,k));
			}else {
				akService.save(new AdminKlinike(ak.getEmail(), loz, ak.getIme(),
						ak.getPrezime(), ak.getAdresa(), ak.getBroj(), true));
			}
			
			return "ok";
		} catch (Exception ex) {
			System.out.println(ex);
			return "pogresan email";
		}
	}

}
