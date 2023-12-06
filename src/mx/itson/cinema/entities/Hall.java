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
public class Hall {

    private int id;
 private String number;
 private int availability;
 
 
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
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the availability
     */
    public int getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(int availability) {
        this.availability = availability;
    }
 
    public static List<Hall> getAll(String filtro){
     List<Hall> halls = new ArrayList<>();
     try{
     Connection conexion = MySQLConnection.get();

     PreparedStatement statement = conexion.prepareStatement("SELECT * FROM hall WHERE numberHall LIKE ?");
     statement.setString(1, "%"+ filtro + "%");
     
     ResultSet resultSet = statement.executeQuery();
      //En esta parte del codigo extraemos todos los datos de la tabla hall
     while(resultSet.next()){
         Hall h = new Hall();
         h.setId(resultSet.getInt(1));
         h.setNumber(resultSet.getString(2));
         h.setAvailability(resultSet.getInt(3));
         halls.add(h);
     }
     }catch(SQLException ex){
      
     }
   return halls;
   }
   //Esto es para agregar datos
   public boolean save(String number, int availability){
     boolean result = false;
     try{
         Connection   conexion = MySQLConnection.get();
         String query = "INSERT INTO hall (numberHall, availability) VALUES (?, ?)";
         PreparedStatement statement = conexion.prepareStatement(query);
         statement.setString(1, number);
         statement.setInt(2, availability);
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
    public boolean update(int id, String number, int availability){
     boolean result = false;
     try{
         Connection   conexion = MySQLConnection.get();
         String query = "UPDATE hall SET numberHall = ?, availability = ? WHERE idHall = ?";
         PreparedStatement statement = conexion.prepareStatement(query);
         statement.setString(1, number);
         statement.setInt(2, availability);
         statement.setInt(3, id);
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
         String query = "DELETE FROM hall WHERE idHall = ?";
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
