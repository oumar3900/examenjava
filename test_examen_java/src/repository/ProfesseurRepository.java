package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Professeur;

public class ProfesseurRepository extends Database{
    //private final String SQL_INSERT="INSERT INTO professeur (nci,nomcomplet,grade ) VALUES (?,?,?);";
    private final  String SQL_SELECT_ALL_PROFESSEUR="select * from professeur" ;
    //private final String SQL_LAST_VALUE_INSERT="SELECT Max(id_professeur) as max FROM professeur";
    private final String SQL_SELECT_PROFESSEUR_BY_ID="SELECT * FROM professeur where id_professeur like ?";

    

      public  List<Professeur> selectAllProfesseurs(){
         List<Professeur> professeurs=new ArrayList<>();
       try {
           openConnexion();
           initPreparedStatement(SQL_SELECT_ALL_PROFESSEUR);
           ResultSet rs= executeSelect();
             while (rs.next()) {
               //Une ligne du ResultSet ==> Une Agence
               Professeur professeur=new Professeur();
               professeur.setId(rs.getInt("id_professeur"));
               professeur.setNom(rs.getString("nom"));
               professeur.setPrenom(rs.getString("prenom"));
               professeur.setTel(rs.getString("tel"));
               professeurs.add(professeur);
             }
             rs.close();
           closeConnexion();
        }
       catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
      }
        return  professeurs;
    }

      public  Professeur selectProfesseurById(int id){
        Professeur professeur=null;
        try {
            openConnexion();
            initPreparedStatement(SQL_SELECT_PROFESSEUR_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = executeSelect();
            professeur=new Professeur(); 
            while (rs.next()) {
                 //Professeur 
                  
                   professeur.setId(rs.getInt("id_professeur")); 
                   professeur.setNom(rs.getString("nom")); 
                   professeur.setPrenom(rs.getString("prenom"));
                  professeur.setTel(rs.getString("tel"));
            }
       } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       return professeur;
      }
}
