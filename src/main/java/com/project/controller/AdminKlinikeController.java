package com.project.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.AdminKlinikeDto;
import com.project.dto.LekarDTO;
import com.project.dto.OperacijaDto;
import com.project.dto.PregledDto;
import com.project.dto.TerminiLekarDto;
import com.project.model.AdminKlinike;
import com.project.model.Lekar;
import com.project.model.Operacija;
import com.project.model.Pregled;
import com.project.model.Sala;
import com.project.model.ZahtevZaOperaciju;
import com.project.model.ZahtevZaPregled;
import com.project.model.ZauzetoVreme;
import com.project.service.EmailService;
import com.project.service.LekarService;
import com.project.service.OperacijaService;
import com.project.service.SalaService;
import com.project.service.ZahtevZaOperacijuService;
import com.project.service.ZahtevZaPregledService;

@RestController
@RequestMapping("/api/adminKlinike")
public class AdminKlinikeController {

	@Autowired
	ZahtevZaPregledService zzpService;

	@Autowired
	ZahtevZaOperacijuService zzoService;

	@Autowired
	SalaService salaService;

	@Autowired
	OperacijaService operacijaService;

	@Autowired
	LekarService lekarService;

	@Autowired
	EmailService emailService;

	@GetMapping(value = "/pregledi")
	public ArrayList<PregledDto> getPregled() {
		Set<ZahtevZaPregled> pregledi = zzpService.findBySala();
		ArrayList<PregledDto> pp = new ArrayList<PregledDto>();
		for (ZahtevZaPregled zzp : pregledi) {
			pp.add(new PregledDto(zzp.getId(), zzp.getLekar().getIme(), zzp.getLekar().getPrezime(), null,
					zzp.getDatum(), zzp.getVremeOd(), null, zzp.getCena(), false, zzp.getTipPregleda().getIme()));
		}
		return pp;
	}

	@PostMapping(value = "/proveri_preglede")
	public void proveriPreglede() throws MailException, InterruptedException {
		LocalDate yesterday = LocalDate.now().minusDays(1);
		LocalTime time = LocalTime.now();
		Set<ZahtevZaPregled> pregledi = zzpService.findBySala();
		for (ZahtevZaPregled zzp : pregledi) {
			while (true) {
				if (zzp.getDatumPrijave().compareTo(yesterday) == 0 && time.compareTo(zzp.getVremePrijave()) > 0) {
					List<Sala> sale = salaService.findSale(zzp.getDatum(), zzp.getVremeOd());
					if (sale.size() == 0)
						zzp.setDatum(zzp.getDatum().plusDays(1L));
					else {
						zzp.setSala(sale.get(0));
						zzpService.save(zzp);
						emailService.sendDodeljenaSala(zzp);
						break;
					}

				}
			}
		}
	}

	@PostMapping(value = "/sale")
	public ResponseEntity getSale(@RequestBody PregledDto pregled) {
		System.out.println(pregled);
		List<Sala> sale = salaService.findSale(pregled.getDatum(), pregled.getVremeOd());
		if (sale.size() != 0)
			return new ResponseEntity(sale, HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

	}

	@PostMapping(value = "/termini")
	public ResponseEntity getTermini(@RequestBody String pregled) {
		String ime = pregled.split(":")[1].split(",")[0].replaceAll("\"", "");
		String prezime = pregled.split(":")[2].split(",")[0].replaceAll("\"", "");
		LocalDate date = LocalDate.parse(pregled.split(":")[3].replaceAll("\"", "").replace("}", ""));
		Lekar l = lekarService.findByNameAndSurname(ime, prezime);
		System.out.println((l == null) + "  " + date);
		return new ResponseEntity(PacijentController.kreirajTermine(date, l), HttpStatus.OK);
		// return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping(value = "/terminiOp")
	public ResponseEntity getTerminiOp(@RequestBody TerminiLekarDto pregled) {
		LocalDate date = pregled.getDatum();
		List<LocalTime> termini = new ArrayList<LocalTime>();
		int i = 0;
		for (Long l : pregled.getLekariIds()) {
			Lekar lekar = lekarService.findById(l);
			List<LocalTime> terminip = PacijentController.kreirajTermine(date, lekar);
			if (i == 0) {
				termini = terminip;
				i++;
			}else {
				termini.retainAll(terminip);
			}
		}

		return new ResponseEntity(termini, HttpStatus.OK);
		// return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping(value = "/zakazi_pregled")
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

	@GetMapping(value = "/operacije")
	public ArrayList<OperacijaDto> getOperacije() {
		Set<ZahtevZaOperaciju> zahteviO = zzoService.findByPraznaSala();
		ArrayList<OperacijaDto> operacije = new ArrayList<OperacijaDto>();
		for (ZahtevZaOperaciju zzo : zahteviO) {
			operacije.add(new OperacijaDto(zzo.getId(), zzo.getLekari(), null, zzo.getDatum(), zzo.getVremeOd(),
					zzo.getVremeDo(), zzo.getCena(), false, zzo.getTipPregleda().getIme(), zzo.getLekarId()));
		}
		return operacije;
	}

	@PostMapping(value = "/sale_operacija")
	public ResponseEntity getSaleOperacija(@RequestBody OperacijaDto operacija) {
		System.out.println(operacija);
		List<Sala> sale = salaService.findSaleOp(operacija.getDatum(), operacija.getVremeOd(),
				operacija.getLekarZakazaoId());
		if (sale.size() != 0)
			return new ResponseEntity(sale, HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

	}

	@PostMapping(value = "/lekari_sala")
	public Set<LekarDTO> getLekariSala(@RequestBody OperacijaDto operacija) {
		Set<LekarDTO> lekari = new HashSet<LekarDTO>();
		Sala sala = salaService.findByNaziv(operacija.getSala());
		System.out.println(sala.getKlinika().getId());
		for (Lekar lk : lekarService.getLekariSala(sala.getKlinika().getId())) {
			LekarDTO lekardto = new LekarDTO(lk.getId(), lk.getIme(), lk.getPrezime(), lk.getProsecnaOcena(), "");
			for (Pregled pregled : lk.getPregledi()) {

				if (LocalDateTime.of(pregled.getDatum(), pregled.getVremeOd())
						.isEqual(LocalDateTime.of(operacija.getDatum(), operacija.getVremeOd()))
						&& LocalDateTime.of(pregled.getDatum(), pregled.getVremeDo())
								.isEqual(LocalDateTime.of(operacija.getDatum(), operacija.getVremeDo())))
					continue;

				lekari.add(lekardto);
			}

			for (Operacija op : lk.getOperacije()) {
				if (LocalDateTime.of(op.getDatum(), op.getVremeOd())
						.isEqual(LocalDateTime.of(operacija.getDatum(), operacija.getVremeOd()))
						&& LocalDateTime.of(op.getDatum(), op.getVremeDo())
								.isEqual(LocalDateTime.of(operacija.getDatum(), operacija.getVremeDo())))
					continue;

				lekari.add(lekardto);
			}

			for (ZauzetoVreme zv : lk.getZauzetoVreme()) {
				if (zv.getDatumOd().isEqual(operacija.getDatum()) || zv.getDatumDo().isEqual(operacija.getDatum()))
					continue;

				lekari.add(lekardto);
			}
		}

		return lekari;
	}

	@PostMapping(value = "/zakazi_operaciju")
	public void zakaziOperaciju(@RequestBody OperacijaDto operacijadto) {
		ZahtevZaOperaciju zzo = zzoService.findById(operacijadto.getId());
		Sala sala = salaService.findByNaziv(operacijadto.getSala());
		zzoService.delete(zzo);
		Operacija o = new Operacija("aaa");
		o.setId(zzo.getId());
		o.setCena(zzo.getCena());
		o.setDatum(operacijadto.getDatum());
		o.setIzvrsen(false);
		o.setDefinisan(false);
		o.setSala(sala);
		o.setTipPregleda(zzo.getTipPregleda());
		o.setVremeOd(operacijadto.getVremeOd());
		o.setVremeDo(operacijadto.getVremeDo());
		o.setZkPacijenta(zzo.getZkPacijenta());
		o.setLekarId(operacijadto.getLekarZakazaoId());
		for (Long id : operacijadto.getLekari_ids()) {
			Lekar l = lekarService.findById(id);
			o.getLekari().add(l);
		}

		operacijaService.save(o);
		try {
			for (Long id : operacijadto.getLekari_ids()) {
				emailService.sendDodeljenaSalaOp(zzo);
			}

		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PostMapping(value = "/proveri_operacije")
	public void proveriOperacije() throws MailException, InterruptedException {
		LocalDate yesterday = LocalDate.now().minusDays(1);
		LocalTime time = LocalTime.now();
		Set<ZahtevZaOperaciju> operacije = zzoService.findByPraznaSala();
		for (ZahtevZaOperaciju zzo : operacije) {
			while (true) {
				if (zzo.getDatumPrijave().compareTo(yesterday) == 0 && time.compareTo(zzo.getVremePrijave()) > 0) {
					List<Sala> sale = salaService.findSaleOp(zzo.getDatum(), zzo.getVremeOd(), zzo.getLekarId());
					if (sale.size() == 0)
						zzo.setDatum(zzo.getDatum().plusDays(1L));
					else {
						Sala izabranaSala = sale.get(0);
						Set<Lekar> izabraniLekari = new HashSet<Lekar>();
						for (Lekar lk : lekarService.getLekariSala(izabranaSala.getKlinika().getId())) {
							for (Pregled pregled : lk.getPregledi()) {

								if (LocalDateTime.of(pregled.getDatum(), pregled.getVremeOd())
										.isEqual(LocalDateTime.of(zzo.getDatum(), zzo.getVremeOd()))
										&& LocalDateTime.of(pregled.getDatum(), pregled.getVremeDo())
												.isEqual(LocalDateTime.of(zzo.getDatum(), zzo.getVremeDo())))
									continue;

								izabraniLekari.add(lk);
							}

							for (Operacija op : lk.getOperacije()) {
								if (LocalDateTime.of(op.getDatum(), op.getVremeOd())
										.isEqual(LocalDateTime.of(zzo.getDatum(), zzo.getVremeOd()))
										&& LocalDateTime.of(op.getDatum(), op.getVremeDo())
												.isEqual(LocalDateTime.of(zzo.getDatum(), zzo.getVremeDo())))
									continue;

								izabraniLekari.add(lk);
							}

							for (ZauzetoVreme zv : lk.getZauzetoVreme()) {
								if (zv.getDatumOd().isEqual(zzo.getDatum()) || zv.getDatumDo().isEqual(zzo.getDatum()))
									continue;

								izabraniLekari.add(lk);
							}
						}
						if (izabraniLekari.size() == 0)
							zzo.setDatum(zzo.getDatum().plusDays(1L));
						else {
							Set<Lekar> iLekari = new HashSet<Lekar>();
							for (Lekar l : izabraniLekari) {
								if (iLekari.size() < 4) {
									iLekari.add(l);
								}
							}

							zzoService.delete(zzo);
							Operacija o = new Operacija("aaa");
							o.setId(zzo.getId());
							o.setCena(zzo.getCena());
							o.setDatum(zzo.getDatum());
							o.setIzvrsen(false);
							o.setDefinisan(false);
							o.setSala(izabranaSala);
							o.setTipPregleda(zzo.getTipPregleda());
							o.setVremeOd(zzo.getVremeOd());
							o.setVremeDo(zzo.getVremeDo());
							o.setZkPacijenta(zzo.getZkPacijenta());
							o.setLekarId(zzo.getLekarId());
							o.setLekari(iLekari);

							operacijaService.save(o);

							for (int i = 0; i < iLekari.size(); i++) {
								emailService.sendDodeljenaSalaOp(zzo);
							}

							break;
						}
					}

				}
			}
		}
	}
}
