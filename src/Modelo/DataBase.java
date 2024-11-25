/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Usuario
 */
public class DataBase {
   
    private final String URL="jdbc:mysql://localhost:3306/Publicacion2";
    private final String user="root" ;
    private final String password="12345";
    
    private Connection conexion;

    public DataBase() {
   try{
     conexion=DriverManager.getConnection(URL, user, password);
     System.out.println("conexión Establecida");
   
   }catch(SQLException e){
     System.out.println("error de conexión");
     e.printStackTrace();
    }            
}
   public int Actualizar (String consulta){
   
       try {
           Statement st=conexion.createStatement();
           return st.executeUpdate(consulta);
       } catch (SQLException e){
          e.printStackTrace(); 
       }
       return 0;
   }

private List organizarDatos(ResultSet rs){
List filas=new ArrayList();
    try{

      int numColumnas=rs.getMetaData().getColumnCount();
        while (rs.next()){
         Map<String, Object> renglon=new HashMap();
         for(int i=1; i<=numColumnas; i++){
         String nombreCampo=rs.getMetaData().getColumnName(i);
            Object valor=rs.getObject(nombreCampo);
            renglon.put(nombreCampo, valor);
       }
       filas.add(renglon);
    }
}catch (SQLException e) {
   e.printStackTrace();
}
return filas;
}

public List Listar(String consulta){
ResultSet rs=null;
List resultado=new ArrayList();
try{
     Statement st=conexion.createStatement();
     rs=st.executeQuery(consulta);
     resultado=organizarDatos(rs);

}catch (SQLException e){
    System.out.println("no se realizo la consulta");
    e.printStackTrace();
   }
return resultado;
}
public void cerrarConexion(){
    try {
        conexion.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }   
  }
}