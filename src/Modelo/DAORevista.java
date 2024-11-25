/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Time;
import java.util.*;

/**
 *
 * @author PC33-B5
 */
public class DAORevista {
    public Revista Insertar( String titulo, int ayo, String issn,
            Float precio,   java.sql.Time Horaventa){
         String transaccion = "INSERT INTO Revista(titulo,ayo,issn,precio,Horaventa) VAlUES ('"
                +titulo +"', '"
                +ayo +"', '"
                +issn +"', '"
                +precio +"', '"
                +Horaventa +"')";
              if (new DataBase().Actualizar(transaccion) > 0 ){
           return new Revista( titulo, ayo ,issn ,precio ,
                   Horaventa);         
    }
       return null;  
}
    public int Actualizar (int numero, String titulo, int ayo,
           String issn, float precio, java.sql.Time Horaventa){
        
    String transaccion = "UPDATE Revista SET titulo='"
               + titulo +"', ayo='"
               + ayo +"', issn='"
               + issn +"', precio='"
               + precio +"',Horaventa='"
               + Horaventa +"' WHERE numeros="
               + numero;
    
               
     return new DataBase().Actualizar(transaccion);
    }
       public List obtenerDatos() {
           String transaccion = "SELECT * FROM Revista";
           List<Map> registros = new DataBase().Listar(transaccion);
       List<Revista> revistas = new ArrayList<>();
       
       for (Map registro : registros){
           Revista revi = new Revista((int) registro.get("numeros"),
                   (String) registro.get("titulo"),
                   (int) registro.get("ayo"),
                   (String) registro.get("issn"),
           (Float) registro.get("precio"),
           (java.sql.Time) registro.get("Horaventa"));
           revistas.add(revi);
       }
   return revistas;
}
       public int Eliminar(int id){
   String transaccion ="DELETE FROM Revista WHERE numeros='"+ id + "'";
   return new DataBase().Actualizar(transaccion);
       }

   


}