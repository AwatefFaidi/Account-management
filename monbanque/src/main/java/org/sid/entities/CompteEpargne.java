package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte  {
	
	private double taux;
	public CompteEpargne(String codecompte, Date datecreation, double solde, Client client, double taux) {
		super(codecompte, datecreation, solde, client);
		this.taux = taux;
	}
	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getTaux() {
		return taux;
	}
	public void setTaux(double taux) {
		this.taux = taux;
	}
	

	
	

}
