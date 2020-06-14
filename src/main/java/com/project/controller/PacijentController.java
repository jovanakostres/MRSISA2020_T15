package com.project.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.config.CustomUserDetails;
import com.project.dto.KlinikaPretragaDTO;
import com.project.dto.LekarDTO;
import com.project.dto.PregledDto;
import com.project.dto.ZdravstveniKartonDTO;
import com.project.model.Klinika;
import com.project.model.Lekar;
import com.project.model.Pacijent;
import com.project.model.Pregled;
import com.project.model.TipPregleda;
import com.project.model.ZahtevZaPregled;
import com.project.model.ZdravstveniKarton;
import com.project.service.EmailService;
import com.project.service.KlinikaService;
import com.project.service.KorisnikService;
import com.project.service.LekarService;
import com.project.service.PacijentService;
import com.project.service.PregledService;
import com.project.service.TIpPregledaService;
import com.project.service.ZahtevZaPregledService;
import com.project.service.ZdravstveniKartonService;

@RestController
@RequestMapping("/api/pacijent")
public class PacijentController {
	
	@Autowired
    PacijentService pacijentService;
	
	@Autowired
    KlinikaService klinikaService;
	
	@Autowired
	LekarService lekarService;
	
	@Autowired
	TIpPregledaService tipPregledaService;
	
	@Autowired
	PregledService pregledService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ZahtevZaPregledService zzpService;
	
	@Autowired
    TokenStore tokenStore;
	
	@Autowired
	ZdravstveniKartonService zkService;
	
	@Autowired 
	KorisnikService korisnikService;
	
/**	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProfil() {
    	return "";
    }
    **/
   
   
   @GetMapping(value ="/klinike")
   public List<Klinika> getKlinike() {
	   List <Klinika> k =klinikaService.findAll();
	   return klinikaService.findAll();
   }
   
   @PostMapping(value ="/lekari")
   public List<LekarDTO> getLekari(@RequestBody String params) throws ParseException {
	   String[] index = params.split(",");
	   String ind = index[0].split(":")[1].replace("\"","").replace("}", "");
	   String datum = index[1].split(":")[1].replace("\"", "").replace("}", "");
	   System.out.println(datum);
	   LocalDate date = LocalDate.parse(datum);
	   System.out.println(date + " datum sa posta"); 
	   System.out.println("KLINIKA " + ind);
	   List<LekarDTO> lekari = new ArrayList<LekarDTO>();
	   Set<Lekar> setLekari = klinikaService.getLekari(Long.parseLong(ind));
	   for(Lekar l : setLekari)
	   {
		   LekarDTO lekar = new LekarDTO(l.getId(), l.getIme(), l.getPrezime(), l.getProsecnaOcena(), l.getTipPregleda().getIme());
		   lekar.setSlobodniTermini(kreirajTermine(date, l));
		   lekari.add(lekar);
		  
	   }
	   return lekari;
   }
   
   public static List<LocalTime> kreirajTermine(LocalDate date, Lekar l) {
	// TODO Auto-generated method stub
	   List<LocalTime> datumi = new ArrayList<LocalTime>();
	   LocalTime vremeOd = l.getPocetakRada();
	   LocalTime vremeDo = l.getKrajRada();
	   LocalTime vreme = vremeOd;
	   while(true)
	   {
		   boolean ind = true;
		   for(Pregled p : l.getPregledi())
		   {
			   System.out.println("Moj datum: " + date + " njihov datum: " + p.getDatum());
			   System.out.println("Moje vreme: " + vreme + " njihovo vreme: " + p.getVremeOd());
			   if(p.getDatum().equals(date)&& p.getVremeOd().equals(vreme))
				   ind = false;
		   }
		   if(ind)
			   datumi.add(vreme);
		   vreme = vreme.plusHours(1);
		   System.out.println("NOVO VREME: " + vreme);
		   if(vreme.isAfter(vremeDo) || vreme.equals(vremeDo))
			   break;
	   }
	   for(LocalTime tt : datumi)
		   System.out.println(tt);
	   return datumi;
   }

   @PostMapping(value ="/lekari_tip")
   public List<LekarDTO> getLekariTip(@RequestBody String params) {
	   String[] index = params.split(",");
	   String ind = index[0].split(":")[1].replace("\"","");
	   Long tip = Long.parseLong(index[1].split(":")[1].replace("\"",""));
	   String datum = index[2].split(":")[1].replace("\"", "").replace("}", "");
	   System.out.println(datum);
	   LocalDate date = LocalDate.parse(datum);
	   //System.out.println("TIP PREGLEDA " + tip);
	   System.out.println("KLINIKA " + ind);
	   List<LekarDTO> lekarRet = new ArrayList<LekarDTO>();
	   Set<Lekar> lekari = klinikaService.getLekari(Long.parseLong(ind));
	   for(Lekar l:lekari)
	   {
		   if(l.getTipPregleda().getId()==tip)
		   {
			   System.out.println("TIP PREGLEDA " + tip);
			   System.out.println("TIP PREGLEDA LEKAR " + l.getTipPregleda().getId());
			   LekarDTO lekar = new LekarDTO(l.getId(), l.getIme(), l.getPrezime(), l.getProsecnaOcena(),l.getTipPregleda().getIme());
			   lekar.setSlobodniTermini(kreirajTermine(date, l));
			   lekarRet.add(lekar);
		   }
	   }
	   return lekarRet;
   }
   
   
   @RequestMapping(value = "/istorija", method = RequestMethod.GET)
   public List<PregledDto> istorija()
   {
	   CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Pacijent pac = pacijentService.findByEmail(userDetails.getUsername());
       ZdravstveniKarton zk = zkService.fingByPacijent(pac.getId());
       List<Pregled> pregledi = pregledService.findByZKarton(zk.getId());
       List<PregledDto> pregledidto = new ArrayList<PregledDto>();
       for(Pregled p: pregledi)
    	   pregledidto.add(new PregledDto(p.getId(), p.getLekar().getIme(), p.getLekar().getPrezime(), p.getSala().getNaziv(), p.getDatum(), p.getVremeOd(), null, p.getCena(), true, p.getTipPregleda().getIme()));
	   return pregledidto;
   }
   
   @RequestMapping(value = "/izmena_profila", method = RequestMethod.POST)
   public ResponseEntity updateProfil(@RequestBody Pacijent pacijent) {
   	System.out.println(pacijent.getIme() + " " + pacijent.getPrezime() );
   	try {
   		System.out.println("PROSAOOO");
   		pacijentService.save(pacijent);
   		return new ResponseEntity(HttpStatus.OK);
   	}
   	catch(Exception ex){
   		return new ResponseEntity(HttpStatus.BAD_REQUEST);
   	}
    
   }
   
   @RequestMapping(value = "/filter_klinika", method = RequestMethod.POST)
   public List<KlinikaPretragaDTO> filter_klinika(@RequestBody String pacijent) {
	   String[] params = pacijent.split(",");
	   String naziv, adresa, opis, minmax;
	   naziv = params[0].split(":")[1].replace("\"", "");
	   adresa = params[1].split(":")[1].replace("\"", "");
	   opis = params[2].split(":")[1].replace("\"", "");
	   minmax = params[3].split(":")[1].replace("\"","");
	   Long tip = Long.parseLong(params[4].split(":")[1].replace("\"","").replace("}", ""));
	   System.out.println("Naziv:  " + naziv + "  Adresa: " + adresa +"  Opis : " + opis);
	   List<Lekar> l = lekarService.findAll();
	   List<KlinikaPretragaDTO> lista = new ArrayList<KlinikaPretragaDTO>();
	   List<Klinika> k = klinikaService.filter(naziv, adresa, opis, minmax);
	   System.out.println("KLINIKA SIZE FILTER: " + k.size());
	   System.out.println("LEKAR SIZE " + l.size());
	   for(Lekar lekar : l)
	   {
		   System.out.println("RADII");
		   if(lekar.getTipPregleda().getId()==tip)
		   {
			   System.out.println("NE RADII");
			   int index = k.indexOf(lekar.getKlinika());
			   System.out.println(index);
			   if(index!=-1)
			   {
				   Klinika klinika = k.get(index);
				   lista.add(new KlinikaPretragaDTO(klinika.getId(),klinika.getNaziv(), klinika.getOcena(), klinika.getAdresa(), lekar.getTipPregleda().getCena()));
			   }			   
		   }
	   }
	   return lista;	   
   }   
   
   //pretraga_klinika
   @RequestMapping(value = "/pretraga_klinika", method = RequestMethod.POST)
   public List<KlinikaPretragaDTO> pretraga_klinika(@RequestBody String pacijent) {
	   String param = pacijent.split(":")[1].replace("\"", "").replace("}", "");
	   System.out.println("PRETRAGA PARAM : " + param);
	   List<Lekar> l = lekarService.findAll();
	   System.out.println("LEAKRI SIZE: "+l.size());
	   List<Klinika> k = klinikaService.findAll();
	   System.out.println("KLINIKELISTA SIZE: "+k.size());
	   List<KlinikaPretragaDTO> klinikeRet = new ArrayList<KlinikaPretragaDTO>();
	   for(Lekar lekar : l)
	   {
		   if(lekar.getTipPregleda().getId()==Long.parseLong(param))
		   {
			   int index = k.indexOf(lekar.getKlinika());
			   if(index!=-1)
			   {
				   Klinika klinika = k.get(index);
				   klinikeRet.add(new KlinikaPretragaDTO(klinika.getId(),klinika.getNaziv(), klinika.getOcena(), klinika.getAdresa(), lekar.getTipPregleda().getCena()));
			   }			   
		   }
	   }
	   System.out.println("KLINIKE SIZE: "+klinikeRet.size());
	   return klinikeRet;
   }   
   
   
   @RequestMapping(value = "/filter_lekar", method = RequestMethod.POST)
   public List<LekarDTO> filter_lekar(@RequestBody String pacijent) {
	   String[] params = pacijent.split(",");
	   String ime, prez, broj, minmax;
	   ime = params[0].split(":")[1].replace("\"", "");
	   prez = params[1].split(":")[1].replace("\"", "");
	   broj = params[2].split(":")[1].replace("\"", "");
	   minmax = params[3].split(":")[1].replace("\"","");
	   String id = params[4].split(":")[1].replace("\"",""); // klinika
	   Long tip = Long.parseLong(params[5].split(":")[1].replace("\"", "").replace("}", "")); //tip pregleda
	   LocalDate date = LocalDate.parse(params[6].split(":")[1].replace("\"", "").replace("}", ""));
	   List<Lekar> lekari = lekarService.filter(ime, prez, broj, minmax, Long.parseLong(id));
	   List<LekarDTO> lekariRet = new ArrayList<LekarDTO>();
	   for(Lekar l : lekari)
	   {
		   LekarDTO lekar = new LekarDTO(l.getId(), l.getIme(), l.getPrezime(), l.getProsecnaOcena(), l.getTipPregleda().getIme());
		   lekar.setSlobodniTermini(kreirajTermine(date, l));
		   lekariRet.add(lekar);
	   }
	   return lekariRet;
   }   
   
   //pretraga_klinika
   @RequestMapping(value = "/pretraga_lekar", method = RequestMethod.POST)
   public List<LekarDTO> pretraga_lekar(@RequestBody String par) {
	   String[] pacijent = par.split(",");
	   String param = pacijent[0].split(":")[1].replace("\"", "");
	   String id = pacijent[1].split(":")[1].replace("\"", "");
	   Long tip = Long.parseLong(pacijent[2].split(":")[1].replace("\"", "").replace("}", ""));
	   LocalDate date = LocalDate.parse(pacijent[3].split(":")[1].replace("\"", "").replace("}", ""));
	   System.out.println("PRETRAGA PARAM : .." + param+ "..");
	   List<Lekar> lekari =  lekarService.search(param, Long.parseLong(id));
	   List<LekarDTO> lekariRet = new ArrayList<LekarDTO>();
	   for(Lekar l : lekari)
	   {
		   LekarDTO lekar = new LekarDTO(l.getId(), l.getIme(), l.getPrezime(), l.getProsecnaOcena(), l.getTipPregleda().getIme());
		   lekar.setSlobodniTermini(kreirajTermine(date, l));
		   lekariRet.add(lekar);
	   }
	   return lekariRet;
	  
   }  
   
   @RequestMapping(value = "/tipovi_pregleda", method = RequestMethod.GET)
   public List<TipPregleda> tipoviPregleda(){
	   List<TipPregleda> lista = tipPregledaService.findAll();
	   System.out.println(lista.size());
	   return lista;
   }
   
   @RequestMapping(value = "/unapred_definisani", method = RequestMethod.POST)
   public List<Pregled> unapred_definisani(@RequestBody String par)
   {
	   return pregledService.findByDefinisan();
   }
   
   @RequestMapping(value = "/unapred_definisani_zakazi", method = RequestMethod.POST)
   public ResponseEntity unapred_definisani_zakazi(@RequestBody String par)
   {
	   System.out.println("ID parametar " + par);
	   Long id = Long.parseLong(par.split(":")[1].replace("\"", "").replace("}", ""));
	   Pregled p = pregledService.findById(id);
	   ZahtevZaPregled pregled = new ZahtevZaPregled(p);
	   zzpService.save(pregled);
	   try{
		   emailService.sendZahtevZaPregled(pregled);
	   }
	   catch(Exception ex)
	   {
		   System.out.println(ex);
	   }
	   return new ResponseEntity(HttpStatus.OK);
   }
   
   @RequestMapping(value = "/zakazi_pregled", method = RequestMethod.POST)
   public ResponseEntity zakaziPregled(@RequestBody String par, @RequestParam (value = "access_token") String accessToken)
   {
	   System.out.println(accessToken);
	   System.out.println(par);
	   String[] pregled = par.split(",");
	   String klinika_id = pregled[0].split(":")[1].replace("\"", "");
	   Long lekar_id = Long.parseLong(pregled[1].split(":")[1].replace("\"", ""));
	   String termin = pregled[2].split("\":")[1].replace("\"", "");
	   String datum = pregled[3].split(":")[1].replace("\"", "").replace("}", "");
	   LocalTime t = LocalTime.parse(termin);
	   LocalDate date = LocalDate.parse(datum);
	   Lekar lekar = lekarService.findById(lekar_id);
	   
	   System.out.println("ddd");
	   
	   CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       
	   Pacijent p = pacijentService.findByEmail(userDetails.getUsername());
	   
	   ZdravstveniKarton zk = zkService.fingByPacijent(p.getId());
	   
	   System.out.println(zk == null);
	   
	   LocalTime time = LocalTime.now();
	   LocalDate d = LocalDate.now();
	   
	   ZahtevZaPregled zzp = new ZahtevZaPregled(date, t, t.plusHours(1L), 100.0, lekar.getTipPregleda(), null, lekar, zk, time, d) ;
	   zzp.setPacijent(p);
	   
	   zzpService.save(zzp);
	   
	   try {
			emailService.sendZahtevZaPregled(zzp);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	   
	   return new ResponseEntity(HttpStatus.OK);
   }
   
   @RequestMapping(value = "/zKarton", method = RequestMethod.GET)
   public ZdravstveniKartonDTO zdravstveniKarton()
   {
	   CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       
	   Pacijent p = pacijentService.findByEmail(userDetails.getUsername());
	   
	   ZdravstveniKarton zk = zkService.fingByPacijent(p.getId());
	   
	   ZdravstveniKartonDTO zkdto = new ZdravstveniKartonDTO(zk);
	   
	   return zkdto;
   }
   
   @RequestMapping(value = "/potvda_pregleda", method = RequestMethod.GET)
   public List<PregledDto> potvrdaPregleda()
   {
	   CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Pacijent p = pacijentService.findByEmail(userDetails.getUsername());
       List<ZahtevZaPregled> zahtevi = zzpService.findByPacijent(p);
       List<PregledDto> pregledi = new ArrayList<PregledDto>();
       for(ZahtevZaPregled z : zahtevi)
    	   pregledi.add(new PregledDto(z.getId(), "","", z.getSala().getNaziv(), z.getDatum(), z.getVremeOd(), null, z.getCena(), false, z.getTipPregleda().getIme()));
       return pregledi;
   }
   
   @RequestMapping(value = "/potvrdi", method = RequestMethod.POST)
   public void potvrdi(@RequestBody String zz) {
	   System.out.println("Prosao");
	   Long id = Long.parseLong(zz.split(":")[1].replace("}", ""));
	   ZahtevZaPregled zzp = zzpService.findById(id);
	   Pregled p = new Pregled();
	   System.out.println(zzp==null);
	   p.setCena(zzp.getCena());
	   p.setDatum(zzp.getDatum());
	   p.setIzvrsen(false);
	   p.setDefinisan(false);
	   p.setLekar(zzp.getLekar());
	   p.setOperacija(false);
	   p.setSala(zzp.getSala());
	   p.setTipPregleda(zzp.getTipPregleda());
	   p.setVremeOd(zzp.getVremeOd()); p.setVremeDo(zzp.getVremeDo());
	   p.setZkPacijenta(zzp.getZkPacijenta());
	   pregledService.save(p);
	   zzpService.delete(zzp);
   }
   
}

