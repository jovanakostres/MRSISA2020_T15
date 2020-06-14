package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.project.model.Korisnik;
import com.project.model.Pregled;
import com.project.model.ZahtevZaPregled;
import com.project.model.ZahtevZaOperaciju;
import com.project.model.ZahtevZaRegistraciju;



@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	/*
	 * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
	 */
	@Autowired
	private Environment env;

	/*
	 * Anotacija za oznacavanje asinhronog zadatka
	 * Vise informacija na: https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#scheduling
	 */
	
	@Async
	public void sendPrihvatanjeReg(Korisnik user) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Registracija");
		mail.setText("Registrovali ste se na sajt klinickog centra!" + "\n" + "Vase korisnicko ime: " + user.getIme() + "\n" 
				+ "Vasa lozinka: " + user.getLozinka());
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

	@Async
	public void sendOdbijanjeReg(String email, String obrazlozenje) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Registracija");
		mail.setText("Nazalost vasa registracija je odbijena. \n\n" + "Razlog: " + obrazlozenje);
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
	
	public void sendNotificaitionSync(ZahtevZaRegistraciju user) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("sara98.mik@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zahtev za registraciju");
		mail.setText(user.toString());
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
	
	public void sendZahtevZaPregled(ZahtevZaPregled pregled) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("sara98.mik@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zahtev za pregled");
		mail.setText(pregled.toString());
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

	
	@Async
	public void sendPrihvatanjePregleda(ZahtevZaPregled pregled) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("sara98.mik@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Pregled prihvacen");
		mail.setText(pregled.toString());
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendDodeljenaSala(ZahtevZaPregled pregled) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("sara98.mik@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Sala je dodeljena");
		mail.setText(pregled.toString());
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendDodeljenaSalaOp(ZahtevZaOperaciju pregled) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("sara98.mik@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Sala je dodeljena");
		mail.setText(pregled.toString());
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

	@Async
	public void sendOdbijanjePregleda() throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("sara98.mik@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odbijen pregled");
		mail.setText("Nazalost vas pregled je odbijen");
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

}
