
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
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
    @FXML
    private Button btn_limpiar;
    
    private ObservableList<Productos> productos;
    int items=0;
    float[] costo = new float[20];
    String[] nombre = new String[20];
    float[] n_cantidades = new float[20];
    float total[] = new float[20];  
    int folio=0;
    int i2;
    float total_pagar=0;
    float c;
    float cambio;
    String operador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCombos();
        
        // Creo el observablelist
        productos = FXCollections.observableArrayList();

        // Asigno las columnas con los atributos del modelo
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
    }
    
    public void initCombos()
    {        
        Productos p = new Productos();
        ObservableList<Productos> obsProductos = p.getProductos();
        this.cmbProducto.setItems(obsProductos);        
    }
    
    @FXML
    public void compra (ActionEvent event) throws IOException{
        if(txtCantidad.getText().equals(""))
        {
           JOptionPane.showMessageDialog(null, "No hay productos añadidos"); 
        }
        else
        {
         calculo_compra();   
        }      
    }
    
    @FXML
    void limpiar_registros(ActionEvent event) {
        tblListaP.getItems().clear();
        items =0;
        total_pagar=0;
    }

    
    @FXML
    public void agregar (ActionEvent event) throws IOException{
        

        Icon icono = new ImageIcon(getClass().getResource("/Imagenes/pan2.png"));
        Productos p = new Productos();
        p = cmbProducto.getSelectionModel().getSelectedItem();
        
        if(txtCantidad.getText().equals(""))
        {
           JOptionPane.showMessageDialog(null, "No ha ingresado una cantidad"); 
        }
        else
        {      
            float cantidad = Integer.parseInt(this.txtCantidad.getText());
            
            try 
            {
                int res = JOptionPane.showConfirmDialog(null, "¿Realmente quiere agregar " + cantidad + " " + p.getNombre() + " al pedido ", "Confirmar pedido", OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icono);
                         
                if(res==0)
                {
                    this.productos.add(p);
                    this.tblListaP.setItems(productos); 
                    nombre[items] = p.getNombre();
                    costo[items] = p.getPrecio();
                    n_cantidades[items] = cantidad;
                    items= items + 1;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Su pedido se ha cancelado");
                }                      
            } 
        
            catch (NumberFormatException e) 
            {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Formato incorrecto");
                alert.showAndWait();
            }
        }       
    }
    
    @FXML
    public void salir (ActionEvent event) throws IOException{
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
    }
    
    public void calculo_compra()
    {
           
        for(i2=0; i2<items; i2++)
        {
            total[i2] = costo[i2] * n_cantidades[i2];
            JOptionPane.showMessageDialog(null, "Producto: " + nombre[i2] + " \nCosto: " + costo[i2] + " \nCantidad: "+ n_cantidades[i2] + " \nTotal: " + total[i2]);
        }
        
        i2=0;
        
        for(i2=0; i2<items; i2++)
        {
           total_pagar += total[i2];
        }
        
        int res2= JOptionPane.showConfirmDialog(null, "El total del pedido es de: " + total_pagar + " \n ¿Desea confirmar el pedido?", "Confirmar pedido", OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(res2==0)
        {
            c = Float.parseFloat(JOptionPane.showInputDialog("Introduce la cantidad con la que se va a pagar"));
            operador = JOptionPane.showInputDialog(null, "Introduce tu nombre");
            
          if(c<total_pagar)
          {
            JOptionPane.showMessageDialog(null, "Falta dinero para completar el pago");  
            total_pagar=0;
          }
          else
          {
            //Generar ticket
            generar_ticket();
          }
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Su pedido se ha cancelado");
        }
    }
    
    public void generar_ticket()
    {
        System.out.flush();
        folio= folio +1;
        java.util.Date fecha = new Date();
        cambio= c-total_pagar;
        
        System.out.print("\n\n**************************************************\n");
        System.out.print("             Panaderia el Dorado\n");
        System.out.print("     Avenida Tonala #2345 Col. Los camachos\n");
        System.out.print("             Telefono: 3323188514\n");
        System.out.print("         Visitanos en: www.eldorado.com\n");
        System.out.print("    Folio: " + folio + "   Fecha: " + fecha+ "\n\n");
        System.out.print("--------------------------------------------------\n");
        System.out.print("               Producto comprado \n");
        System.out.print("Nombre          Cantidad          Subtotal\n");       
        for(int i3=0; i3<items; i3++)
        {
            System.out.print(""+ nombre[i3] + "             " + n_cantidades[i3] + "             " + total[i3] + "\n");
        }
        System.out.print("--------------------------------------------------\n");
        System.out.print("                       Total:      "+total_pagar+"\n");       
        System.out.print("                       Pago con:   "+ c +"\n\n");
        
        System.out.print("                       Cambio:     "+ cambio +"\n\n");
        System.out.print("--------------------------------------------------\n");
        
        System.out.print("Nombre del operador:          "+ operador );
        
        System.out.print("\n\n**************************************************\n");
        
    }
}
