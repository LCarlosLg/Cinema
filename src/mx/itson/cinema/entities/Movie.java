/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.cinema.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.cinema.persistence.MySQLConnection;

/**
 *
 * @author Luis Lopez, Adlemi Duarte, Marco Lopez, Jonathan Felix. 
 */
public class Movie {

     private int id;
    private String name;
   private String duration;
   private String category;
   private String description;
   private String nameActors;
   
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the nameActors
     */
    public String getNameActors() {
        return nameActors;
    }

    /**
     * @param nameActors the nameActors to set
     */
    public void setNameActors(String nameActors) {
        this.nameActors = nameActors;
    }
     public static List<Movie> getAll(String filtro){
     List<Movie> movies = new ArrayList<>();
     try{
     Connection conexion = MySQLConnection.get();

     PreparedStatement statement = conexion.prepareStatement("SELECT * FROM movie WHERE nameMovie LIKE ?");
     statement.setString(1, "%"+ filtro + "%");
     
     ResultSet resultSet = statement.executeQuery();
     //En esta parte del codigo extraemos todos los datos de la tabla movie
     while(resultSet.next()){
         Movie m = new Movie();
         m.setId(resultSet.getInt(1));
         m.setName(resultSet.getString(2));
         m.setDuration(resultSet.getString(3));
          m.setCategory(resultSet.getString(4));
           m.setDescription(resultSet.getString(5));
            m.setNameActors(resultSet.getString(6));
        movies.add(m);
     }
     }catch(SQLException ex){
      
     }
   return movies;
   }
   //Esto es para agregar datos
   public boolean save(String name, String duration, String category, String description, String nameActors){
     boolean result = false;
     try{
         Connection   conexion = MySQLConnection.get();
         String query = "INSERT INTO movie (nameMovie, duration, category, description, nameActors) VALUES (?, ?, ?, ?, ?)";
         PreparedStatement statement = conexion.prepareStatement(query);
         statement.setString(1, name);
         statement.setString(2, duration);
         statement.setString(3, category);
         statement.setString(4, description);
         statement.setString(5, nameActors);
         statement.execute();
         //Hace return para el numero de filas afectadas, si retorna 1 resultara ser true y si no pues false.
         result = statement.getUpdateCount() == 1;
         //Se cierra la conexion
         conexion.close();
     }catch (Exception ex){
         System.err.println("Error: " + ex.getMessage());
     }
   return result;
   }
   //Esta parte del codigo acepta los cambios agregados.
    public boolean update(int id, String name, String duration, String category, String description, String nameActors){
     boolean result = false;
     try{
         Connection   conexion = MySQLConnection.get();
         String query = "UPDATE movie SET nameMovie = ?, duration = ?, category = ?, description = ?, nameActors = ? WHERE idMovie = ?";
         PreparedStatement statement = conexion.prepareStatement(query);
         statement.setString(1, name);
         statement.setString(2, duration);
         statement.setString(3, category);
         statement.setString(4, description);
         statement.setString(5, nameActors);
         statement.setInt(6, id);
         statement.execute();
         //Hace return para el numero de filas afectadas, si retorna 1 resultara ser true y si no pues false.
         result = statement.getUpdateCount() == 1;
         //Se cierra la conexion
         conexion.close();
     }catch (Exception ex){
         System.err.println("Error: " + ex.getMessage());
     }
   return result;
   }
    //Esta parte del codigo es para borrar datos de la tabla 
    public boolean delete(int id){
     boolean result = false;
     try{
         Connection   conexion = MySQLConnection.get();
         String query = "DELETE FROM movie WHERE idMovie = ?";
         PreparedStatement statement = conexion.prepareStatement(query);
         statement.setInt(1, id);
         statement.execute();
         //Hace return para el numero de filas afectadas, si retorna 1 resultara ser true y si no pues false.
         result = statement.getUpdateCount() == 1;
         //Se cierra la conexion
         conexion.close();
     }catch (Exception ex){
         System.err.println("Error: " + ex.getMessage());
     }
   return result;
    
       
   }
}
