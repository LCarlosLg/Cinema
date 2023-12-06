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
public class Function {
 
    private Movie movie;
  private Hall hall;
  private int id;
  private String date;
  
    /**
     * @return the movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * @param movie the movie to set
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * @return the hall
     */
    public Hall getHall() {
        return hall;
    }

    /**
     * @param hall the hall to set
     */
    public void setHall(Hall hall) {
        this.hall = hall;
    }

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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    public static List<Function> getAll(){
     List<Function> functions = new ArrayList<>();
     try{
     Connection conexion = MySQLConnection.get();

     PreparedStatement statement = conexion.prepareStatement("SELECT m.idMovie, m.nameMovie, m.duration, m.category, m.description, m.nameActors, h.numberHall, h.availability,j.idFunction, j.date FROM functionm j INNER JOIN movie m ON j.idMovie = m.idMovie INNER JOIN hall h ON j.idHall = h.idHall");
     
     
     ResultSet resultSet = statement.executeQuery();
      //En esta parte del codigo extraemos todos los datos de la tabla movie y de la tabla hall y los enlazamos con la de function
     while(resultSet.next()){
         Function f = new Function();
         f.setId(resultSet.getInt(9));
         Movie m = new Movie();
         m.setId(1);
         m.setName(resultSet.getString(2));
         m.setDuration(resultSet.getString(3));
         m.setCategory(resultSet.getString(4));
         m.setDescription(resultSet.getString(5));
         m.setNameActors(resultSet.getString(6));
         f.setMovie(m);
         Hall h = new Hall();
         h.setNumber(resultSet.getString(7));
         h.setAvailability(resultSet.getInt(8));
         f.setHall(h);
         f.setDate(resultSet.getString(10));
         functions.add(f);
     }
     }catch(SQLException ex){
      
     }
   return functions;
   }
   //Esto es para agregar datos
   public boolean save(int idHall, int idMovie, String date){
     boolean result = false;
     try{
         Connection   conexion = MySQLConnection.get();
         String query = "INSERT INTO functionm (idMovie, idHall, date) VALUES (?, ?, ?)";
         PreparedStatement statement = conexion.prepareStatement(query);
         statement.setInt(1, idMovie);
         statement.setInt(2, idHall);
         statement.setString(3, date);
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
    public boolean update(int id,int idHall, int idMovie, String date){
     boolean result = false;
     try{
         Connection   conexion = MySQLConnection.get();
         String query = "UPDATE functionm SET idMovie = ?, idHall = ?, date = ? WHERE idFunction = ?";
         PreparedStatement statement = conexion.prepareStatement(query);
         statement.setInt(1, idMovie);
         statement.setInt(2, idHall);
         statement.setString(3, date);
         statement.setInt(4, id);
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
         String query = "DELETE FROM functionm WHERE idFunction = ?";
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

