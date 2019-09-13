package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;
@Entity
// on  a3 types de sstrategy: single bales est tjrs le applique c ad tous les comtes eparne et courant avec le mm table 
// ou  bien jointure 
// ou bien chaque a un table 
// single table a prolemem lorsque on a plusieurs colonne null 
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
// on ajoute noubveau colonne dans le table compte
@DiscriminatorColumn(name="TYPE_CPTE",
discriminatorType=DiscriminatorType.STRING,length=2)
public abstract class Compte  implements Serializable{
	@Id
	private String codecompte;
	private Date datecreation;
	private double solde;
	@ManyToOne
	//ajouter cle ertengere
	@JoinColumn(name="CODE_CLI")
	
	private Client client;
	@OneToMany(mappedBy="compte",fetch=FetchType.LAZY)
	private Collection <Operation> operations;
	
	
	
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Compte(String codecompte, Date datecreation, double solde, Client client) {
		super();
		this.codecompte = codecompte;
		this.datecreation = datecreation;
		this.solde = solde;
		this.client = client;
	}

	

	public String getCodecompte() {
		return codecompte;
	}
	public void setCodecompte(String codecompte) {
		this.codecompte = codecompte;
	}
	public Date getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Collection<Operation> getOperations() {
		return operations;
	}
	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	
	
	

}
