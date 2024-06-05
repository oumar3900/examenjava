package services;

import java.util.List;

import entities.Cours;
import repository.CoursRepository;

public class CoursService {
     CoursRepository coursRepository=new CoursRepository();
    public  List<Cours>listerCours(){
         return coursRepository.selectAll();
     }
    public  void ajouterCours(Cours cours){
        coursRepository.insert(cours);
    }
}
