
package modelos;

import conexiondb.ConexionMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Productos {
    private int id;
    private String nombre;
    private float precio;

    public Productos() {
    }

    public Productos(int id, String nombre, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    public ObservableList<Productos> getProductos() {
        ObservableList<Productos> obs = FXCollections.observableArrayList();
        try {

            // Abro la conexion
            ConexionMySQL conexion = new ConexionMySQL("localhost", "panaderia", "root", "");

            // realizo la consulta
            conexion.ejecutarConsulta("select * from productos");

            ResultSet rs = conexion.getResultSet();
            
            // recorro los resultados
            while(rs.next()){
                
                // se toman los datos
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                float precio = rs.getFloat("precio");
                
                // Creo el cliente
                Productos p = new Productos(id, nombre, precio);
                
                obs.add(p);
                
            }
            
            // Cierro la conexion
            conexion.cerrarConexion();
            

        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obs;
    }
}
