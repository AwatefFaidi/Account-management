package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Operation;
import org.sid.entities.Rettrait;
import org.sid.entities.Versement;
import org.sid.metier.BanqueMetierImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MonbanqueApplication implements CommandLineRunner{
	@Autowired
	private ClientRepository cltdao;
	@Autowired
	private CompteRepository cptdao;
	@Autowired
	private OperationRepository opdao;
	@Autowired
	private BanqueMetierImpl banquemetier;
	public static void main(String[] args) {
		 SpringApplication.run(MonbanqueApplication.class, args);
	 
	 
}

	@Override
	public void run(String... args) throws Exception {	// TODO Auto-generated method stub
		/*Client c1= cltdao.save(new Client("awatef","awatef@gmail.com"));
		Client c2= cltdao.save(new Client("atef","atef@gmail.com"));
		Compte cp1= cptdao.save(new CompteCourant("c1", new Date(),589,c1,8978));
		Compte cp2=cptdao.save(new CompteEpargne("c2",new Date(),859,c2,5.8));
		 opdao.save(new Versement(new Date(), 897,cp1));
		 opdao.save(new Versement (new Date(),1877,cp2));
		 opdao.save( new Rettrait (new Date(),458,cp1));
		 opdao.save( new Rettrait (new Date(),458,cp2));
		 banquemetier.verser("c1", 589);
	*/
	}
}
