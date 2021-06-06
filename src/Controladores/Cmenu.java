
package Controladores;

import Controladores.Calculo_VentaController;
import Controladores.Calculo_VentaController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Cmenu implements Initializable {

    @FXML
    private Label txtNombre_Operador;
    @FXML
    private ComboBox txtNombre_Pieza;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button txtAgregar;
    @FXML
    private Button btnCalcular;
    @FXML
    private Button btnSalir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    public void compra (ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Calculo_Venta.fxml"));
        
        Parent root = loader.load();
        Calculo_VentaController controlador = loader.getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    @FXML
    public void agregar (ActionEvent event) throws IOException{
        
    }
    
    @FXML
    public void salir (ActionEvent event) throws IOException{
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
    }
    
}
