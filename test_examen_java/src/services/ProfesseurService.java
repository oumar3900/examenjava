package services;

import java.util.List;

import entities.Professeur;
import repository.ProfesseurRepository;

public class ProfesseurService {
    
    ProfesseurRepository professeurRepository=new ProfesseurRepository();
    public  List<Professeur>listerProfesseurs(){
         return professeurRepository.selectAllProfesseurs();
     }

     public  Professeur rechercherProfesseurParId(int id){
        return professeurRepository.selectProfesseurById(id);
    }
}
