
package Controladores;

import Controladores.Calculo_VentaController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelos.Productos;

public class Cmenu implements Initializable {

    @FXML
    private Label txtNombre_Operador;
    @FXML
    private ComboBox<Productos> cmbProducto;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button btnCalcular;
    @FXML
    private Button btnSalir;
    @FXML
    private TableView<Productos> tblListaP;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colCantidad;
    @FXML
    private TableColumn colPrecio;
    @FXML
    private Button btnAgregar;
    
    private ObservableList<Productos> productos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCombos();
        
        // Creo el observablelist
        productos = FXCollections.observableArrayList();

        // Asigno las columnas con los atributos del modelo
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
    }
    
    public void initCombos(){
        
        Productos p = new Productos();
        ObservableList<Productos> obsProductos = p.getProductos();
        this.cmbProducto.setItems(obsProductos);
        
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
        
        try {

            // Obtengo los datos del formulario
            cmbProducto.getSelectionModel().getSelectedItem();
            //String nombre = cmbProducto.getSelectionModel();
            int cantidad = Integer.parseInt(this.txtCantidad.getText());
            //float precio = Float.parseFloat(this.precio.getText());

            // Creo una item
            //Productos p = new Productos(nombre, cantidad, );

            // Compruebo si el item esta en el lista
            if (!this.productos.contains(p)) {
                // Lo añado a la lista
                this.productos.add(p);
                // Seteo los items
                this.tblListaP.setItems(productos);
            } else {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Existe el producto");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
        
    }
    
    @FXML
    public void salir (ActionEvent event) throws IOException{
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
    }
    
}
