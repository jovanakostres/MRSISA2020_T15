package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.KlinikaPretragaDTO;
import com.project.dto.LekarDTO;
import com.project.model.Klinika;
import com.project.model.Lekar;
import com.project.model.Pacijent;
import com.project.model.Pregled;
import com.project.model.TipPregleda;
import com.project.model.ZahtevZaPregled;
import com.project.service.EmailService;
import com.project.service.KlinikaService;
import com.project.service.LekarService;
import com.project.service.PacijentService;
import com.project.service.PregledService;
import com.project.service.TIpPregledaService;
import com.project.service.ZahtevZaPregledService;

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
	
/**	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProfil() {
    	return "";
    }
    **/
   @GetMapping(value ="/profil")
   public Optional<Pacijent> getProfil() {
	   return pacijentService.findById(1L);
   }
   
   @GetMapping(value ="/klinike")
   public List<Klinika> getKlinike() {
	   List <Klinika> k =klinikaService.findAll();
	   return klinikaService.findAll();
   }
   
   @PostMapping(value ="/lekari")
   public List<LekarDTO> getLekari(@RequestBody String index) {
	   String ind = index.split(":")[1].replace("\"","").replace("}", "");
	   System.out.println("KLINIKA " + ind);
	   List<LekarDTO> lekari = new ArrayList<LekarDTO>();
	   Set<Lekar> setLekari = klinikaService.getLekari(Long.parseLong(ind));
	   for(Lekar l : setLekari)
	   {
		   lekari.add(new LekarDTO(l.getIme(), l.getPrezime(), l.getProsecnaOcena(), l.getTipPregleda().getIme()));
	   }
	   return lekari;
   }
   
   @PostMapping(value ="/lekari_tip")
   public List<LekarDTO> getLekariTip(@RequestBody String params) {
	   String[] index = params.split(",");
	   String ind = index[0].split(":")[1].replace("\"","");
	   Long tip = Long.parseLong(index[1].split(":")[1].replace("\"","").replace("}", ""));
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
			   lekarRet.add(new LekarDTO(l.getIme(), l.getPrezime(), l.getProsecnaOcena(),l.getTipPregleda().getIme()));
		   }
	   }
	   return lekarRet;
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
	   List<Lekar> lekari = lekarService.filter(ime, prez, broj, minmax, Long.parseLong(id));
	   List<LekarDTO> lekariRet = new ArrayList<LekarDTO>();
	   for(Lekar l : lekari)
	   {
		   lekariRet.add(new LekarDTO(l.getIme(), l.getPrezime(), l.getProsecnaOcena(), l.getTipPregleda().getIme()));
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
	   System.out.println("PRETRAGA PARAM : .." + param+ "..");
	   List<Lekar> lekari =  lekarService.search(param, Long.parseLong(id));
	   List<LekarDTO> lekariRet = new ArrayList<LekarDTO>();
	   for(Lekar l : lekari)
	   {
		   lekariRet.add(new LekarDTO(l.getIme(), l.getPrezime(), l.getProsecnaOcena(), l.getTipPregleda().getIme()));
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
   
   @GetMapping(value ="/getUsername")
   public List<String> getUsername(){
	   String s = SecurityContextHolder.getContext().getAuthentication().getName();
	   System.out.println("S : " + s);
	   String p = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
	   System.out.println("P: " + p);
	   List<String> retList = new ArrayList<String>();
	   retList.add(s);
	   retList.add(p);
       return retList;
   }
	
   
}

