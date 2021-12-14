package dad.gesCon.ui.contacto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import dad.contactos.model.Telefono;
import dad.contactos.model.TipoTelefono;
import dad.gesCon.ui.contacto.telefono.TelefonoController;
import dad.gesCon.ui.main.App;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ContactoController implements Initializable {

	@FXML
    private Button EliminarImagenButton;

    @FXML
    private Button anteriorContactoButton;

    @FXML
    private TextField apellidosField;

    @FXML
    private Button cambiarImagenButton;

    @FXML
    private Button eliminarTelefonoButton;

    @FXML
    private DatePicker fechaNacimientoPicker;
    
    @FXML
    private ImageView fotoView;

    @FXML
    private TextField nombreField;

    @FXML
    private Button nuevoTelefonoButton;

    @FXML
    private TableColumn<Telefono, String> numeroColumn;

    @FXML
    private Button siguienteContactoButton;

    @FXML
    private TableView<Telefono> telefonosTable;

    @FXML
    private TableColumn<Telefono, TipoTelefono> tipoColumn;

    @FXML
    private GridPane view;
	
    private ContactoModel model = new ContactoModel();

    private ObjectProperty<Image> NO_PHOTO;
    
    private TelefonoController telefonoController;
    
    private Stage telefonoStage;
    
	public ContactoController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NO_PHOTO = new SimpleObjectProperty<Image>(new Image(getClass().getResourceAsStream("/images/no-photo-128x128.png")));
		
		telefonoController = new TelefonoController();
		
		numeroColumn.setCellValueFactory(new PropertyValueFactory<Telefono, String>("numero"));
		tipoColumn.setCellValueFactory(new PropertyValueFactory<Telefono, TipoTelefono>("tipo"));
		
		model.nombreProperty().bindBidirectional(nombreField.textProperty());
		model.apellidosProperty().bindBidirectional(apellidosField.textProperty());
		model.fechaNacimientoProperty().bindBidirectional(fechaNacimientoPicker.valueProperty());
		model.telefonosProperty().bindBidirectional(telefonosTable.itemsProperty());
		model.fotoProperty().bindBidirectional(fotoView.imageProperty());
		
		model.fotoProperty().addListener((obv, ov, nv) -> {
			model.setFoto(
					(Objects.isNull(nv) ? NO_PHOTO.get() : nv)
			);
		});
		
		telefonoController.getNuevoButton().setOnAction(e -> {
			Telefono telefono = new Telefono(telefonoController.getModel().getTelefono(), telefonoController.getModel().getTipoTelefono());
	    	model.telefonosProperty().add(telefono);
	    	telefonoStage.close();
		});
		
		telefonoController.getCancelarButton().setOnAction(e -> {
			telefonoStage.close();
		});
		
		eliminarTelefonoButton.disableProperty().bind(model.telefonosProperty().emptyProperty());
	}

    @FXML
    void onCambiarImagenAction(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.setInitialDirectory(new File("."));
    	fc.getExtensionFilters().addAll(
    			new ExtensionFilter("Todas las Imágenes","*.jpg","*.png","*.bmp"),
    			new ExtensionFilter("Todos los archivos","*.*")
    	);
    	try {
    		File fichero = fc.showOpenDialog(App.primaryStage);
			Image foto = new Image(fichero.toURI().toURL().toExternalForm());
			model.setFoto(foto);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error al poner foto al contacto "+model.getNombre());
			alert.showAndWait();
		}
    }

    @FXML
    void onEliminarImagenAction(ActionEvent event) {
    	model.setFoto(null);
    }

    @FXML
    void onEliminarTelefonoAction(ActionEvent event) {
    	Telefono seleccionado = telefonosTable.getSelectionModel().getSelectedItem();
    	Alert alert = new Alert(AlertType.CONFIRMATION, "¿Estás seguro?");
    	alert.setHeaderText("Se va a eliminar el telefono "+seleccionado.getNumero()+"("+seleccionado.getTipo()+")");
    	alert.showAndWait()
    		.filter(response -> response == ButtonType.OK)
    		.ifPresent(response -> {
    			model.telefonosProperty().remove(seleccionado);
    		});	
    }

    @FXML
    void onNuevoTelefonoAction(ActionEvent event) {
    	telefonoStage = new Stage();
    	telefonoStage.setTitle("Nuevo Teléfono");
    	telefonoStage.setScene(new Scene(telefonoController.getView()));
    	telefonoStage.initOwner(App.primaryStage);
    	telefonoStage.showAndWait();
    }

	public Button getEliminarImagenButton() {
		return EliminarImagenButton;
	}

	public Button getAnteriorContactoButton() {
		return anteriorContactoButton;
	}

	public TextField getApellidosField() {
		return apellidosField;
	}

	public Button getCambiarImagenButton() {
		return cambiarImagenButton;
	}

	public Button getEliminarTelefonoButton() {
		return eliminarTelefonoButton;
	}

	public DatePicker getFechaNacimientoPicker() {
		return fechaNacimientoPicker;
	}

	public TextField getNombreField() {
		return nombreField;
	}

	public Button getNuevoTelefonoButton() {
		return nuevoTelefonoButton;
	}

	public TableColumn<Telefono, String> getNumeroColumn() {
		return numeroColumn;
	}

	public Button getSiguienteContactoButton() {
		return siguienteContactoButton;
	}

	public TableView<Telefono> getTelefonosTable() {
		return telefonosTable;
	}

	public TableColumn<Telefono, TipoTelefono> getTipoColumn() {
		return tipoColumn;
	}
	
	public ContactoModel getModel() {
		return model;
	}

	public GridPane getView() {
		return view;
	}
}
