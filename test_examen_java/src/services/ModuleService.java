package services;

import java.util.List;

import com.mysql.cj.xdevapi.Client;

import entities.Module;
import repository.ModuleRepository;

public class ModuleService {
    
    ModuleRepository moduleRepository=new ModuleRepository();
    public  List<entities.Module>listerModule(){
         return moduleRepository.selectAll();
     }
    public  void ajouterModule(entities.Module module){
        moduleRepository.insert(module);
    }
     public  Module rechercherModuleParId(int id){
        return moduleRepository.selectModuleById(id);
    }
}
