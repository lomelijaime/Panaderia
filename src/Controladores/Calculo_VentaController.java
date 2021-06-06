/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Calculo_VentaController implements Initializable {

    @FXML
    private Label txtTotal;
    @FXML
    private TextField txtPago;
    @FXML
    private Label txtCambio;
    @FXML
    private Button btnTicket;
    @FXML
    private Button btnCancelar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void generarTicket (ActionEvent event){
        
    }
    
    @FXML
    public void salir (ActionEvent event){
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }
    
}
