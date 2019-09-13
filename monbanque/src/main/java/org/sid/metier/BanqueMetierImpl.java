package org.sid.metier;

import java.util.Date;
import java.util.Optional;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Rettrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// on ajoute cette annotaion pour le metier 
@Service
// on ajoute cette annoations  ca d tous les methodes sont transactionnel
@Transactional
public class BanqueMetierImpl  implements BanqueMetier{
	//injections de depandance
@Autowired
private CompteRepository cptedao;
private OperationRepository opdao;
	@Override
	public Compte ConsulterCompte(String codeCpte) {
		Optional<Compte> cp=cptedao.findById(codeCpte);
		if(cp==null) throw new RuntimeException("compte introuvable");
				return  cp.get();
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp=ConsulterCompte(codeCpte);
		Versement v= new Versement(new Date(), montant, cp);
		opdao.save(v);
		cp.setSolde(cp.getSolde()+montant);
		cptedao.save(cp);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp=ConsulterCompte(codeCpte);
		double facilitescaisse=0;
		if (cp instanceof CompteCourant)
		facilitescaisse=((CompteCourant) cp).getDecouvert();
		if(cp.getSolde()+facilitescaisse<montant)
			throw new RuntimeException("solde insuffisant");
		Rettrait r=new Rettrait(new Date(),montant, cp);
		opdao.save(r);
		cp.setSolde(cp.getSolde()-montant);
		cptedao.save(cp);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		// TODO Auto-generated method stub
		if(codeCpte1.equals(codeCpte2))
			throw new RuntimeException(" impossible virement sur le meme compte");
		retirer(codeCpte1,montant);
		verser(codeCpte2,montant);
		
	}

	@Override
	public Page<Operation> ListOperation(String codeCpte, int page, int size) {
		// TODO Auto-generated method stub
		return opdao.ListOperation(codeCpte,new PageRequest(page,size) );
	}

}
