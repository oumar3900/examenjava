package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entities.Module;
import entities.Professeur;

public class ModuleRepository extends Database{
    private final  String SQL_SELECT_ALL="select * from module" ;
    private final  String SQL_INSERT="INSERT INTO module (nomModule) VALUES (?)";
    private final String SQL_SELECT_MODULE_BY_ID="SELECT * FROM module where id_module like ?";
    //select
    public  List<entities.Module> selectAll(){
         List<entities.Module> modules=new ArrayList<>();
       try {
           openConnexion();
           initPreparedStatement(SQL_SELECT_ALL);
           ResultSet rs= executeSelect();
             while (rs.next()) {
               //Une ligne du ResultSet ==> Une Agence
               Module module=new Module();
               module.setId(rs.getInt("id_module"));
               module.setNomModule(rs.getString("nomModule"));
               modules.add(module);
             }
             rs.close();
           closeConnexion();
        }
       catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
      }
        return  modules;
    }


    public  void insert(Module module){
        openConnexion();
        try {
            initPreparedStatement(SQL_INSERT);
            statement.setString(1, module.getNomModule());
            int nbreLigne=executeUpdate();
           closeConnexion();
         } catch (SQLException e) {
          e.printStackTrace();
         }
         }

         public  Module selectModuleById(int id){
        Module module=null;
        try {
            openConnexion();
            initPreparedStatement(SQL_SELECT_MODULE_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = executeSelect();
            module=new Module(); 
            while (rs.next()) {
                 //Professeur 
                  
                   module.setId(rs.getInt("id_module")); 
                   module.setNomModule(rs.getString("nomModule")); 
            }
       } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       return module;
      }
}
