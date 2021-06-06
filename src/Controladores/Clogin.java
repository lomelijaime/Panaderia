package Controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class Clogin implements Initializable 
{
    
    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    boolean estado = false;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }
    
    @FXML
    public void Login(ActionEvent event) throws SQLException, IOException 
    {              
        String user = txtUsername.getText();
        String password = txtPassword.getText();
                
        if(user.equals("") && password.equals(""))
        {
            JOptionPane.showMessageDialog(null, "No ha ingresado un correo o contraseña");
        }
        else
        {            
            try 
            {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba_login", "root", "");
                pst = conn.prepareStatement("SELECT * FROM usuarios WHERE usuario=? AND contraseña=?");
                
                pst.setString(1, user);
                pst.setString(2, password);
                rs = pst.executeQuery();
                
                if(rs.next())
                {             
                    ir_menu();
                }                
                else
                {
                    JOptionPane.showMessageDialog(null, "No se ha podido iniciar sesión");
                }
            } 
            catch (ClassNotFoundException ex) 
            {
                Logger.getLogger(Clogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(Clogin.class.getName()).log(Level.SEVERE, null, ex);
            }              
        } 
    }
    public void ir_menu() throws IOException
    {          
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/menu.fxml"));
        Parent root = loader.load();
        Cmenu controlador = loader.getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        //Cerrar Ventana
        Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
        myStage.close();
        }
}
