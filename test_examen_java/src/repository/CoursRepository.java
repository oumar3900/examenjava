package repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Client;

import entities.Cours;
import entities.Module;
import entities.Professeur;

public class CoursRepository extends Database{
    private final  String SQL_SELECT_ALL="select * cours" ;
    private final  String SQL_INSERT="INSERT INTO cours (id, date, heureDb, heureFin, id_module, id_professeur) VALUES (?,?,?,?,?,?)";
    //select
    public  List<Cours> selectAll(){
         List<Cours> cours=new ArrayList<>();
       try {
           openConnexion();
           initPreparedStatement(SQL_SELECT_ALL);
           ResultSet rs= executeSelect();
             while (rs.next()) {
               //Une ligne du ResultSet ==> Une Agence
               Professeur professeur=new Professeur();
               professeur.setId(rs.getInt("id_professeur"));
               professeur.setNom(rs.getString("nom"));
               professeur.setPrenom(rs.getString("prenom"));
               professeur.setTel(rs.getString("tel"));

               Module module=new Module();
               module.setId(rs.getInt("id_module"));
               module.setNomModule(rs.getString("nomModule"));

                 Cours cr=new Cours();
                 cr.setId(rs.getInt("id"));
                 cr.setDate(rs.getDate("date").toLocalDate());
                 cr.setHeureDb(rs.getTime("heureDb").toLocalTime());
                 cr.setHeureFin(rs.getTime("heureFin").toLocalTime());
                 cr.setProfesseur(professeur);
                 cr.setModule(module);
                 cours.add(cr);
             }
             rs.close();
           closeConnexion();
        }
       catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
      }
        return  cours;
    }
    public  void insert(Cours cours){
        openConnexion();
        try {
            initPreparedStatement(SQL_INSERT);
            statement.setDate(1, Date.valueOf(cours.getDate()));
            statement.setTime(2, Time.valueOf(cours.getHeureDb()));
            statement.setTime(3, Time.valueOf(cours.getHeureFin()));
            statement.setInt(4, cours.getModule().getId());
            statement.setInt(5, cours.getProfesseur().getId());
            int nbreLigne=executeUpdate();
           closeConnexion();
         } catch (SQLException e) {
          e.printStackTrace();
         }
         }
}
