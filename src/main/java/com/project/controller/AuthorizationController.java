package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ZahtevZaRegistracijuDto;
import com.project.model.Korisnik;
import com.project.model.Pacijent;
import com.project.model.ZahtevZaRegistraciju;
import com.project.service.EmailService;
import com.project.service.KorisnikService;
import com.project.service.PacijentService;
import com.project.service.ZahtevZaRegistracijuService;

@RestController
@RequestMapping("/api")
public class AuthorizationController {
	
    @Autowired
    PacijentService pacijentService;
    
    @Autowired
    KorisnikService korisnikService;
    
    @Autowired
    EmailService emailService;
	
    @Autowired
    ZahtevZaRegistracijuService zahtevZaRegistracijuService;
    
    @Autowired
    TokenStore tokenStore;
    
    @RequestMapping(value = "/registracija", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody ZahtevZaRegistraciju pacijent) {
    	System.out.println(pacijent);
    	
        try {
            System.out.println("asdasdasdasdsd");
            //pacijent.setConfirmed(false);
            //pacijent = pacijentService.save(pacijent);
            
            if(pacijentService.findByEmail(pacijent.getEmail()) != null) {
            	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                ZahtevZaRegistraciju zahtev = new ZahtevZaRegistraciju(pacijent.getEmail(),pacijent.getLozinka(),pacijent.getIme(),pacijent.getPrezime(),pacijent.getAdresa(),pacijent.getBroj(),pacijent.getLbo());
                zahtevZaRegistracijuService.save(zahtev);
                emailService.sendNotificaitionSync(pacijent);
            }
            
            return new ResponseEntity<>(pacijent, HttpStatus.OK);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value="/potvrdiRegistraciju", method= RequestMethod.POST)
    public ResponseEntity confirmUserAccount(@RequestBody ZahtevZaRegistracijuDto zahtevDto)
    {
        try {
        	ZahtevZaRegistraciju zahtev = zahtevZaRegistracijuService.findById(zahtevDto.getId());
        	zahtevZaRegistracijuService.delete(zahtev);
        	Pacijent pacijent = new Pacijent(zahtev.getEmail(),zahtev.getLozinka(),zahtev.getIme(),zahtev.getPrezime(),zahtev.getAdresa(),zahtev.getBroj(),zahtev.getLbo());
        	pacijentService.save(pacijent);
        	emailService.sendPrihvatanjeReg(pacijent);
        	return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value="/odbijRegistraciju", method= RequestMethod.POST)
    public ResponseEntity disproveUserAccount(@RequestBody ZahtevZaRegistracijuDto zahtevDto)
    {
        try {
        	ZahtevZaRegistraciju zahtev = zahtevZaRegistracijuService.findById(zahtevDto.getId());
        	zahtevZaRegistracijuService.delete(zahtev);
        	emailService.sendOdbijanjeReg(zahtevDto.getEmailP(), zahtevDto.getObrazlozenje());
        	return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
	@PostMapping("login")
	public ResponseEntity login(@RequestBody String req){
		String ime = req.split(",")[0].split(":")[1].replace("\"", "");
		String lozinka = req.split(",")[1].split(":")[1].replaceAll("\"|}", "");
		System.out.println("\n\nKorinsik : " + ime + " lozinka: " + lozinka + "\n\n");
		
		Korisnik k = korisnikService.findByEmailAndLozinka(ime, lozinka);
		
		if(k!=null)
		{
			System.out.println(k.getClass());
			return new ResponseEntity<>(k, HttpStatus.OK);
		}
		
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
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
	
	 @GetMapping(value = "/logouts")
	    public void logout(@RequestParam (value = "access_token") String accessToken){
	        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
	 }
}
