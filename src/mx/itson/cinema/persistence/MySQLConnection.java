/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.cinema.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Luis Lopez, Adlemi Duarte, Marco Lopez, Jonathan Felix. 
 */
public class MySQLConnection {
    public static Connection get (){
        Connection connection = null;
        try{
        //Establece una conexión a la base de datos MySQL en el puerto 3308, con el usuario y la contraseña.
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/cinemadb?user=root&password=carlitoss1");
        }catch(Exception ex){
         // Imprime un mensaje de error en caso de que se produzca una excepción al intentar establecer la conexión.
            System.err.print("Error:  "+ ex.getMessage())  ; 
        }

        return connection;
    }
    
}


