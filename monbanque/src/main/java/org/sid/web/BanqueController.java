package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.BanqueMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
	@Autowired
	private BanqueMetier banquemetier;
	@RequestMapping("/operations")
	public String index() {
		return "comptes";
	}
	@RequestMapping("/conusltercompte")
	public String consulter(Model model, String codecompte,
			@RequestParam(name="page" ,defaultValue="0") int page,
			@RequestParam(name="size" ,defaultValue="5") int size) {
		model.addAttribute("codeCompte", codecompte);
		try {
		Compte cp=banquemetier.ConsulterCompte(codecompte);
		Page<Operation> pageOperations= banquemetier.ListOperation(codecompte,page,size);
	    model.addAttribute("listoperations", pageOperations.getContent()); 
		int[] pages=new int[pageOperations.getTotalPages()];
		model.addAttribute("pages", pages);
	    model.addAttribute("compte",cp);
		}catch(Exception e)
		{
			model.addAttribute("exception",e);
			
		}
		return "comptes";
	 }
@RequestMapping(value="/saveOperation" , method=RequestMethod.POST)
	public String saveOperation(Model model , String typeOperation, String codeCompte,double montant,String codeCompte2 ) {
	try {
		if(typeOperation.equals("vers"))
		{
			banquemetier.verser(codeCompte,montant);
		}
		else if((typeOperation.equals("rett")))
		{
			banquemetier.retirer(codeCompte,montant);
		}
		else if((typeOperation.equals("virt")))
		{
			banquemetier.virement(codeCompte,codeCompte2,montant);
		}
	}
	catch(Exception e) {
		model.addAttribute("error",e);
		return "redirect:/consultercompte?codeCompte="+codeCompte+"&error="+e.getMessage();
	}
	
	return "redirect:/consultercompte?codeCompte="+codeCompte;
	}
}
