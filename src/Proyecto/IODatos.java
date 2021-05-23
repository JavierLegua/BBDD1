/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author javierlegua
 */
public class IODatos {

    private static final String RUTA = "jdbc:mysql://34.229.80.204:3306/javier";
    private static final String USU = "javier";
    private static final String PASS = "javier";
    
    public static void añadirUsu(Alumno a) {
       
        try (Connection con = DriverManager.getConnection(RUTA, USU, PASS);){
            
        String sql = "insert into ejemplo values(?,?)";
        
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, a.getNombre());
        pt.setString(2, a.getEdad());
        pt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
    
    public static javax.swing.table.DefaultTableModel cargarTablaUsuarios(){
        DefaultTableModel mTabla= new DefaultTableModel();

        int cont=0;

        try (Connection con = DriverManager.getConnection(RUTA, USU, PASS);){
            String select = "select * from user";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(select);

            mTabla.addColumn("Nombre");
            mTabla.addColumn("Contraseña");

            rs = st.executeQuery(select);

            while (rs.next()) {
                Object[] fila = {rs.getString(1), rs.getString(2)};
                mTabla.addRow(fila);


            }


            } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
            }


        return mTabla;

    }

    public static void borrarUsu(String nombre) {
       
        try (Connection con = DriverManager.getConnection(RUTA, USU, PASS);){
            
            String sql = "delete from ejemplo where nombre='" + nombre + "'";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    static void modUsu(String nombre, String edad) {
       
        try (Connection con = DriverManager.getConnection(RUTA, USU, PASS);){
            
            String sql = "update ejemplo edad = '" + edad +"' where nombre = '" + nombre +"'";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
 
    
}
