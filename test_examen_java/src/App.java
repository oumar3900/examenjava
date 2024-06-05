import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Client;

import entities.Cours;
import entities.Professeur;
import services.CoursService;
import services.ModuleService;
import services.ProfesseurService;

public class App {
    public static void main(String[] args) throws Exception {
        int choix;
        Scanner sc=new Scanner(System.in);
        ModuleService moduleService=new ModuleService();
        CoursService coursService=new CoursService();
        ProfesseurService professeurService= new ProfesseurService();
        //Dependances Repository
        //Dependances
       
        do {
            System.out.println("1-Ajouter un Module");
            System.out.println("2-Lister les Module"); 
            System.out.println("3-Créer un cours"); 
            System.out.println("4-Lister les  tous les cours");
            System.out.println("5-Lister les  les cours  d’un module et d’un professeur");
            System.out.println("6-Quitter");
             choix=sc.nextInt();
             sc.nextLine();
             
            switch (choix) {
                case 1:
                     System.out.println("Entrer le nom du Module à ajouter");
                     String nom=sc.nextLine(); 
                     entities.Module module=new entities.Module();
                     module.setNomModule(nom);
                    moduleService.ajouterModule(module);
                    break;
                    
                case 2:
                System.out.println("les modules sont les suivantes :");
                List<entities.Module> modules= moduleService.listerModule();
                for (entities.Module md : modules) {
                    System.out.println("Nom du module:  "+ md.getNomModule());
                    System.out.println("=================================");
                }
                break;

                case 3:
                Cours cours=new Cours();
                System.out.println("Entrez la date du cours (AAAA-MM-JJ):");
                cours.setDate(LocalDate.parse(sc.nextLine()));
        
                System.out.println("Entrez l'heure de début du cours (HH:MM):");
                cours.setHeureDb(LocalTime.parse(sc.nextLine()));
        
                System.out.println("Entrez l'heure de fin du cours (HH:MM):");
                cours.setHeureFin(LocalTime.parse(sc.nextLine()));
                
                System.out.println("Chosir un module :");
                modules= moduleService.listerModule();
                for (entities.Module md : modules) {
                    System.out.println(md.getId()+""+"Nom du module:  "+ md.getNomModule());
                    System.out.println("=================================");
                }
                int id=sc.nextInt();
                module=moduleService.rechercherModuleParId(id);
                cours.setModule(module);

                System.out.println("Chosir un professeur :");
                List<Professeur>professeurs= professeurService.listerProfesseurs();
                for (Professeur pr : professeurs) {
                    System.out.println(pr.getId()+""+"Nom du prof:  "+ pr.getPrenom()+" "+pr.getNom());
                    System.out.println("=================================");
                }
                id=sc.nextInt();
                Professeur professeur =new Professeur();
                professeur=professeurService.rechercherProfesseurParId(id);
                cours.setProfesseur(professeur);
                coursService.ajouterCours(cours);

              break;
                case 4:

                System.out.println("les cours sont les suivantes :");
                List<Cours> listeCours= coursService.listerCours();
                for (Cours cr : listeCours) {
                    System.out.println(cr.getModule().getNomModule());
                    System.out.println(cr.getProfesseur().getPrenom()+" "+cr.getProfesseur().getNom());
                    System.out.println("=================================");
                }
                   
                case 5:
                
                    break;
                                }

        } while (choix!=8);
    }
}
