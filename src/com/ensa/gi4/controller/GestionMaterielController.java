package com.ensa.gi4.controller;

import com.ensa.gi4.datatabase.api.UtilisateurDAO;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.GestionUtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.List;
import java.util.Scanner;

@Component("controllerPricipal")
public class GestionMaterielController {

    @Autowired

    ApplicationPublisher publisher;
    
    @Autowired
    GestionUtilisateurService userService;
    
    @Autowired
    GestionMaterielService materielService;

    public void afficherMenu() {
    	
    	//Les variables 
    	int reponse = 0;
    	int check = 0;
    	Utilisateur utilisateur = null, checkUtilisateur = null;
    	Materiel materiel = null;
    	List<Materiel> listMaterielUser = null;
    	List<Materiel> listMaterielEveryUser = null;
    	List<Materiel> listMateriel = null;
    	
    	//Message de r�ception
    	System.out.println("Bienvenue dans notre application de gestion d'allocation et de restitution de mat�riel");
    	
    	//G�rer l'authentification
    	while(checkUtilisateur.equals(null)) {
    		
    		do {
    			
    			System.out.print("Veuillez taper 1 pour continuer ou bien 0 pour sortir de l'application : ");
            	Scanner scanner = new Scanner(System.in);
            	reponse = scanner.nextInt();
    			
    		}while(reponse != 0 && reponse != 1);
    		
    		if(reponse == 0) {
    			
    			sortirDeLApplication();
    		}
    		
    		if(reponse == 1) {
    			
    			System.out.print("Entrez votre nom d'utilisateur : ");
    			Scanner scannerUserName = new Scanner(System.in);
    			String username = scannerUserName.nextLine();
    			
    			System.out.print("Entrez votre mot de passe : ");
    			Scanner scannerPassword = new Scanner(System.in);
    			String password = scannerPassword.nextLine();
    			
    			utilisateur.setUsername(username);
    			utilisateur.setPassword(password);
        		
    			checkUtilisateur = userService.loginS(utilisateur);
    		}
    			
    	}
    	
    	//Le traitement dans le cas o� l'utilisateur est authentifi�
    	if(!checkUtilisateur.equals(null)) {
    		
    		String role = checkUtilisateur.getRole();
    		
    		//Traiter les roles de l'utilisateur
    		//On aura 3 cas possibles : Administrateur, Employe, Administrateur et Employe
    		
    		//Diviser le role en utilisant le d�limiteur ,
    		String[] roles = role.split(",");
    		
    		//La taille de roles
    		int sizeRoles = roles.length;
    		
    		//Traiter les cas :
    		
    		//Un seul role effectu� :
    		if(sizeRoles == 1) {
    			
    			for(int i = 0 ; i < roles.length ; i++) {
    				
    				//Traiter le cas d'un administrateur
    				if(roles[i].equals("Administrateur")) {
    					
    					System.out.println("En tant qu'un administrateur, voici votre menu");
    					
    					System.out.println("0- Quitter l'application");
    					System.out.println("1- Chercher un mat�riel");
    					System.out.println("2- Cr�er un nouveau mat�riel");
    					System.out.println("3- Supprimer un mat�riel");
    					System.out.println("4- Modifier les informations d'un mat�riel");
    					System.out.println("5- Marquer un mat�riel indisponible");
    					System.out.println("6- Allouer un mat�riel");
    					System.out.println("7- Rendre un mat�riel");
    					System.out.println("8- Afficher la liste des mat�riels allou�s par lui m�me");
    					System.out.println("9- Afficher la liste des mat�riels allou�s par chaque utilisateur");
    					System.out.println("10- Afficher la liste de tous les mat�riels");
    					
    					System.out.print("Veuillez le num�ro correspondant � votre fonctionnalit� voulue : ");
    					Scanner scannerNumero = new Scanner(System.in);
    					int numero = scannerNumero.nextInt();
    					
    					//Le switch sur le num�ro
    					switch(numero) {
    					
    					case 0:
    						sortirDeLApplication();
    						break;
    						
    					case 1: 
    						System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez chercher : ");
    						Scanner sc = new Scanner(System.in);
    						Long identifiant = sc.nextLong();
    						
    						//L'�x�cution de la m�thode
    						materiel = materielService.chercherMaterielS(identifiant);
    						break;
    						
    					case 2 :
    						System.out.println("Veuillez taper le nom du mat�riel que vous voulez ajouter : ");
    						Scanner scannerNom = new Scanner(System.in);
    						String nomMateriel = scannerNom.nextLine();
    						
    						System.out.print("Veuillez taper le code du mat�riel que vous voulez ajouter : ");
    						Scanner scannerCode = new Scanner(System.in);
    						String codeMateriel = scannerCode.nextLine();
    						
    						System.out.print("Veuillez taper l'�tat du mat�riel que vous voulez ajouter : ");
    						Scanner scannerEtat = new Scanner(System.in);
    						String etatMateriel = scannerEtat.nextLine();
    						
    						materiel.setName(nomMateriel);
    						materiel.setCode(codeMateriel);
    						materiel.setCode(etatMateriel);
    						
    						//L'�x�cution de la m�thode
    						check = materielService.ajouterNouveauMaterielS(materiel);
    						break;
    						
    					case 3 : 
    						
    						System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez supprimer : ");
    						Scanner scannerSupprimer = new Scanner(System.in);
    						Long identifiantSupprimer = scannerSupprimer.nextLong();
    						
    						//L'�x�cution de la m�thode
    						check = materielService.supprimerMaterielS(identifiantSupprimer);
    						break;
    						
    					case 4 : 
    						
    						System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez modifier : ");
    						Scanner idM = new Scanner(System.in);
    						Long identifiantModifier = idM.nextLong();
    						
    						System.out.println("Veuillez taper le nouveau nom du mat�riel que vous voulez modifier : ");
    						Scanner scannerNomM = new Scanner(System.in);
    						String nomMaterielM = scannerNomM.nextLine();
    						
    						System.out.print("Veuillez taper la nouvelle �tat du mat�riel que vous voulez modifier : ");
    						Scanner scannerEtatM = new Scanner(System.in);
    						String etatMaterielM = scannerEtatM.nextLine();
    						
    						materiel.setName(nomMaterielM);
    						materiel.setCode(etatMaterielM);
    						
    						//L'�x�cution de la m�thode
    						check = materielService.modifierMaterielS(materiel, identifiantModifier);
    						break;
    						
    					case 5 :
    						
    						System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez modifier : ");
    						Scanner idMI = new Scanner(System.in);
    						Long identifiantModifierI = idMI.nextLong();
    						//L'�x�cution de la m�thode 
    						check = materielService.materielIndisponibleS(identifiantModifierI, "Indisponible");
    						break;
    						
    					case 6 :
    						
    						System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez allouer : ");
    						Scanner scAllouer = new Scanner(System.in);
    						Long idAllouer = scAllouer.nextLong();
    						
    						//L'�x�cution de la m�thode 
    						check = materielService.allouerMaterielS(idAllouer, "Alloue", checkUtilisateur.getIdUser());
    						break;
    						
    					case 7 :
    						
    						System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez rendre : ");
    						Scanner scRendre = new Scanner(System.in);
    						Long idRendre = scRendre.nextLong();
    						
    						//L'�x�cution de la m�thode
    						check = materielService.rendreMaterielS(idRendre, "Restitue");
    						break;
    					
    					case 8 :
    						
    						System.out.println("La liste des mat�riels allou�es par vous-m�me :");
    						
    						//L'�x�cution de la m�thode
    						listMaterielUser = materielService.afficherMaterielUserS(checkUtilisateur.getIdUser());
    						
    						for(Materiel item : listMaterielUser) {
    							
    							System.out.println(item.getName() + " " + item.getCode());
    						}
    						break;
    						
    					case 9 : 
    						
    						System.out.println("Afficher la liste des mat�riels allou�s par chaque utilisateur");
    						
    						//L'�x�cution de la m�htode 
    						listMaterielEveryUser = materielService.afficherMaterielEveryUserS();
    						
    						for(Materiel item : listMaterielEveryUser) {
    							
    							System.out.println(item.getName() + " " + item.getCode());
    						}
    						break;
    						
    					case 10 : 
    						
    						System.out.println("La liste des mat�riels : ");
    						
    						//L'�x�cution de la m�thode 
    						listMateriel = materielService.afficherMaterielS();
    						
    						for(Materiel item : listMateriel) {
    							
    							System.out.println(item.getName() + " " + item.getCode());
    						}
    						break;
    						
    					default : 
    						
    						System.out.println("Veuillez saisir un nombre entre 0 et 10");
    					}
    					
    				}
    				
    				//Traiter le cas d'un employ�
    				if(roles[i].equals("Employe")) {
    					
    					System.out.println("En tant qu'un employ�, voici votre menu");
    					
    					System.out.println("0- Quitter l'application");
    					System.out.println("1- Chercher un mat�riel");
    					System.out.println("2- Allouer un mat�riel");
    					System.out.println("3- Rendre un mat�riel");
    					System.out.println("4- Afficher la liste des mat�riels allou�s par lui m�me");
    					System.out.println("5- Afficher la liste de tous les mat�riels");
    					
    					System.out.print("Veuillez le num�ro correspondant � votre fonctionnalit� voulue : ");
    					Scanner scannerNumero = new Scanner(System.in);
    					int numero = scannerNumero.nextInt();
    					
    					switch(numero) {
    					
    					case 0 :
    						
    						sortirDeLApplication();
    						break;
    						
    					case 1: 
    						
    						System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez chercher : ");
    						Scanner sc = new Scanner(System.in);
    						Long identifiant = sc.nextLong();
    						
    						//L'�x�cution de la m�thode
    						materiel = materielService.chercherMaterielS(identifiant);
    						break;
    						
    					case 2 :
    						
    						System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez allouer : ");
    						Scanner scAllouer = new Scanner(System.in);
    						Long idAllouer = scAllouer.nextLong();
    						
    						//L'�x�cution de la m�thode 
    						check = materielService.allouerMaterielS(idAllouer, "Alloue", checkUtilisateur.getIdUser());
    						break;
    						
    					case 3 :
    						
    						System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez rendre : ");
    						Scanner scRendre = new Scanner(System.in);
    						Long idRendre = scRendre.nextLong();
    						
    						//L'�x�cution de la m�thode
    						check = materielService.rendreMaterielS(idRendre, "Restitue");
    						break;
    						
    					case 4 :
    						
    						System.out.println("La liste des mat�riels allou�es par vous-m�me :");
    						
    						//L'�x�cution de la m�thode
    						listMaterielUser = materielService.afficherMaterielUserS(checkUtilisateur.getIdUser());
    						
    						for(Materiel item : listMaterielUser) {
    							
    							System.out.println(item.getName() + " " + item.getCode());
    						}
    						break;
    						
    					case 5 :
    						
    						System.out.println("La liste des mat�riels : ");
    						
    						//L'�x�cution de la m�thode 
    						listMateriel = materielService.afficherMaterielS();
    						
    						for(Materiel item : listMateriel) {
    							
    							System.out.println(item.getName() + " " + item.getCode());
    						}
    						break;
    						
    					default : 
    						
    						System.out.println("Veuillez saisir un nombre entre 0 et 5");
    					}
    					
    				}
    			}
    				
    				
    		}
    		
    		//Deux roles en m�me temps
    		if(sizeRoles == 2) {
    			
    			System.out.println("En tant qu'un utilisateur mixte, voici votre menu");
    			
    			System.out.println("0- Quitter l'application");
				System.out.println("1- Chercher un mat�riel");
				System.out.println("2- Cr�er un nouveau mat�riel");
				System.out.println("3- Supprimer un mat�riel");
				System.out.println("4- Modifier les informations d'un mat�riel");
				System.out.println("5- Marquer un mat�riel indisponible");
				System.out.println("6- Allouer un mat�riel");
				System.out.println("7- Rendre un mat�riel");
				System.out.println("8- Afficher la liste des mat�riels allou�s par lui m�me");
				System.out.println("9- Afficher la liste des mat�riels allou�s par chaque utilisateur");
				System.out.println("10- Afficher la liste de tous les mat�riels");
    			
				System.out.print("Veuillez le num�ro correspondant � votre fonctionnalit� voulue : ");
				Scanner scannerNumero = new Scanner(System.in);
				int numero = scannerNumero.nextInt();
				
				//Le switch sur le num�ro
				switch(numero) {
				
				case 0:
					sortirDeLApplication();
					break;
					
				case 1: 
					System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez chercher : ");
					Scanner sc = new Scanner(System.in);
					Long identifiant = sc.nextLong();
					
					//L'�x�cution de la m�thode
					materiel = materielService.chercherMaterielS(identifiant);
					break;
					
				case 2 :
					System.out.println("Veuillez taper le nom du mat�riel que vous voulez ajouter : ");
					Scanner scannerNom = new Scanner(System.in);
					String nomMateriel = scannerNom.nextLine();
					
					System.out.print("Veuillez taper le code du mat�riel que vous voulez ajouter : ");
					Scanner scannerCode = new Scanner(System.in);
					String codeMateriel = scannerCode.nextLine();
					
					System.out.print("Veuillez taper l'�tat du mat�riel que vous voulez ajouter : ");
					Scanner scannerEtat = new Scanner(System.in);
					String etatMateriel = scannerEtat.nextLine();
					
					materiel.setName(nomMateriel);
					materiel.setCode(codeMateriel);
					materiel.setCode(etatMateriel);
					
					//L'�x�cution de la m�thode
					check = materielService.ajouterNouveauMaterielS(materiel);
					break;
					
				case 3 : 
					
					System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez supprimer : ");
					Scanner scannerSupprimer = new Scanner(System.in);
					Long identifiantSupprimer = scannerSupprimer.nextLong();
					
					//L'�x�cution de la m�thode
					check = materielService.supprimerMaterielS(identifiantSupprimer);
					break;
					
				case 4 : 
					
					System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez modifier : ");
					Scanner idM = new Scanner(System.in);
					Long identifiantModifier = idM.nextLong();
					
					System.out.println("Veuillez taper le nouveau nom du mat�riel que vous voulez modifier : ");
					Scanner scannerNomM = new Scanner(System.in);
					String nomMaterielM = scannerNomM.nextLine();
					
					System.out.print("Veuillez taper la nouvelle �tat du mat�riel que vous voulez modifier : ");
					Scanner scannerEtatM = new Scanner(System.in);
					String etatMaterielM = scannerEtatM.nextLine();
					
					materiel.setName(nomMaterielM);
					materiel.setCode(etatMaterielM);
					
					//L'�x�cution de la m�thode
					check = materielService.modifierMaterielS(materiel, identifiantModifier);
					break;
					
				case 5 :
					
					System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez modifier : ");
					Scanner idMI = new Scanner(System.in);
					Long identifiantModifierI = idMI.nextLong();
					//L'�x�cution de la m�thode 
					check = materielService.materielIndisponibleS(identifiantModifierI, "Indisponible");
					break;
					
				case 6 :
					
					System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez allouer : ");
					Scanner scAllouer = new Scanner(System.in);
					Long idAllouer = scAllouer.nextLong();
					
					//L'�x�cution de la m�thode 
					check = materielService.allouerMaterielS(idAllouer, "Alloue", checkUtilisateur.getIdUser());
					break;
					
				case 7 :
					
					System.out.print("Veuillez taper l'identifiant du mat�riel que vous voulez rendre : ");
					Scanner scRendre = new Scanner(System.in);
					Long idRendre = scRendre.nextLong();
					
					//L'�x�cution de la m�thode
					check = materielService.rendreMaterielS(idRendre, "Restitue");
					break;
				
				case 8 :
					
					System.out.println("La liste des mat�riels allou�es par vous-m�me :");
					
					//L'�x�cution de la m�thode
					listMaterielUser = materielService.afficherMaterielUserS(checkUtilisateur.getIdUser());
					
					for(Materiel item : listMaterielUser) {
						
						System.out.println(item.getName() + " " + item.getCode());
					}
					break;
					
				case 9 : 
					
					System.out.println("Afficher la liste des mat�riels allou�s par chaque utilisateur");
					
					//L'�x�cution de la m�htode 
					listMaterielEveryUser = materielService.afficherMaterielEveryUserS();
					
					for(Materiel item : listMaterielEveryUser) {
						
						System.out.println(item.getName() + " " + item.getCode());
					}
					break;
					
				case 10 : 
					
					System.out.println("La liste des mat�riels : ");
					
					//L'�x�cution de la m�thode 
					listMateriel = materielService.afficherMaterielS();
					
					for(Materiel item : listMateriel) {
						
						System.out.println(item.getName() + " " + item.getCode());
					}
					break;
					
				default : 
					
					System.out.println("Veuillez saisir un nombre entre 0 et 10");
				}
    			
    		}
    		
    		
    		
    		
    	}
    		
    		
    		
    		
    	

    	
        //Scanner scanner  = new Scanner(System.in);
        publisher.publish(new MyEvent<>(new Livre(), EventType.ADD));
    }

    //La m�thode de r�p�tition d'un traitement
    public void repeterTraitement(int i) {
    	
    	
    	
    }
    
    //La m�thode pour sortir de l'application
    public void sortirDeLApplication() {
        System.exit(0);
    }

}
