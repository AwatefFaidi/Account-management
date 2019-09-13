package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("R")
public class Rettrait extends Operation {

	public Rettrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rettrait(Date dateoperation, double montant, Compte compte) {
		super(dateoperation, montant, compte);
		// TODO Auto-generated constructor stub
	}

	

	
}
