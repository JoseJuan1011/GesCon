package dad.gesCon.ui.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import dad.contactos.model.Agenda;
import dad.contactos.model.Contacto;
import dad.gesCon.ui.contacto.ContactoController;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {
	
	@FXML
    private MenuItem abrirItem;

    @FXML
    private Button addContactoButton;

    @FXML
    private ListView<Contacto> contactosList;

    @FXML
    private Button deleteContactoButton;

    @FXML
    private MenuItem guardarItem;

    @FXML
    private MenuItem nuevoItem;

    @FXML
    private BorderPane rightView;

    @FXML
    private MenuItem salirItem;

    @FXML
    private VBox view;
    
    private MainModel model = new MainModel();
    
    private ContactoController contactoController;
	
	public MainController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		contactoController = new ContactoController();
		
		model.getAgendaFX().contactosProperty().bindBidirectional(model.contactosProperty());
		model.contactosProperty().bindBidirectional(contactosList.itemsProperty());
		
		model.contactoSeleccionadoProperty().bind(contactosList.getSelectionModel().selectedItemProperty());
		
		model.contactoSeleccionadoProperty().addListener((obv, ov, nv) -> onContactoSeleccionadoChangedAction(obv,ov,nv));
		
		contactoController.getAnteriorContactoButton().setOnAction(e -> {
			int indiceSeleccionado = contactosList.getSelectionModel().getSelectedIndex();
			contactosList.getSelectionModel().clearAndSelect(indiceSeleccionado-1);
		});
		
		contactoController.getSiguienteContactoButton().setOnAction(e -> {
			int indiceSeleccionado = contactosList.getSelectionModel().getSelectedIndex();
			contactosList.getSelectionModel().clearAndSelect(indiceSeleccionado+1);
		});
		
	}
	
	private void onContactoSeleccionadoChangedAction(ObservableValue<? extends Contacto> obv, Contacto ov, Contacto nv) {
		if (ov != null) {
			//unbindBidirectional(ov)
			contactoController.getModel().nombreProperty().unbindBidirectional(ov.nombreProperty());
			contactoController.getModel().apellidosProperty().unbindBidirectional(ov.apellidosProperty());
			contactoController.getModel().fechaNacimientoProperty().unbindBidirectional(ov.fechaNacimientoProperty());
			contactoController.getModel().telefonosProperty().unbindBidirectional(ov.telefonosProperty());
			contactoController.getModel().fotoProperty().unbindBidirectional(ov.fotoProperty());
		}
		
		if (nv != null) {
			//bindBidirectional(nv)
			if (model.contactosProperty().indexOf(model.getContactoSeleccionado()) == 0) {
				contactoController.getAnteriorContactoButton().setDisable(true);
				contactoController.getSiguienteContactoButton().setDisable(false);
			}
			
			else if (model.contactosProperty().indexOf(model.getContactoSeleccionado()) == model.contactosProperty().getSize()-1) {
				contactoController.getSiguienteContactoButton().setDisable(true);
				contactoController.getAnteriorContactoButton().setDisable(false);
			}
			
			else {
				contactoController.getSiguienteContactoButton().setDisable(false);
				contactoController.getAnteriorContactoButton().setDisable(false);
			}
			contactoController.getModel().nombreProperty().bindBidirectional(nv.nombreProperty());
			contactoController.getModel().apellidosProperty().bindBidirectional(nv.apellidosProperty());
			contactoController.getModel().fechaNacimientoProperty().bindBidirectional(nv.fechaNacimientoProperty());
			contactoController.getModel().telefonosProperty().bindBidirectional(nv.telefonosProperty());
			contactoController.getModel().fotoProperty().bindBidirectional(nv.fotoProperty());
		}
	}

	@FXML
    void onAbrirAction(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File("."));
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Agenda","*.agenda"),
				new ExtensionFilter("Todos los Archivos","*.*")
		);
		File archivoAgenda = fc.showOpenDialog(App.primaryStage);
		if (!Objects.isNull(archivoAgenda)) {
			try {
				model.setAgendaFX(Agenda.load(archivoAgenda));
				model.contactosProperty().bindBidirectional(model.getAgendaFX().contactosProperty());
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error al cargar la agenda.");
				alert.showAndWait();
			}
		}
    }

    @FXML
    void onAddContactoAction(ActionEvent event) {
    	Contacto contacto = new Contacto();
    	contacto.setNombre("Sin Nombre");
    	contacto.setApellidos("Sin Apellidos");
    	contacto.setFoto(new Image(getClass().getResourceAsStream("/images/no-photo-128x128.png")));
    	model.contactosProperty().add(contacto);
    }

    @FXML
    void onDeleteContactoButton(ActionEvent event) {
    	Contacto contacto = model.getContactoSeleccionado();
    	Alert alert = new Alert(AlertType.CONFIRMATION,"¿Está seguro?");
    	alert.setHeaderText("Se va a eliminar el contacto "+contacto.getNombre());
    	alert.showAndWait()
    		.filter(response -> response == ButtonType.OK)
    		.ifPresent(response -> {
    			model.getContactos().remove(contacto);
    		});	
    }

    @FXML
    void onGuardarAction(ActionEvent event) {
    	FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File("."));
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Agenda","*.agenda"),
				new ExtensionFilter("Todos los Archivos","*.*")
		);
		File archivoAgenda = fc.showSaveDialog(App.primaryStage);
		try {
			if (!Objects.isNull(archivoAgenda)) {
				model.getAgendaFX().save(archivoAgenda);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error al guardar la agenda.");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
    }

    @FXML
    void onListClickedEvent(MouseEvent event) {
    	if (!Objects.isNull(model.getContactoSeleccionado())) {
    		rightView.setCenter(contactoController.getView());
    		contactoController.getView().setDisable(false);
    	}
    	else {
    		contactoController.getView().setDisable(true);
    	}
    }
    
    @FXML
    void onNuevoAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.WARNING,"¿Seguro que desea continuar?");
    	alert.setHeaderText("Se dispone a crear una nueva agenda. \n"
    			+ "Si tiene información sin guardar se perderá para siempre");
    	alert.setTitle("Nueva agenda");
    	alert.initOwner(App.primaryStage);
    	alert.getButtonTypes().add(ButtonType.CANCEL);
    	alert.showAndWait()
    		.filter(response -> response == ButtonType.OK)
    		.ifPresent(response -> {
    			model.setAgendaFX(new Agenda());
    			model.contactosProperty().clear();
    		});
    }

    @FXML
    void onSalirAction(ActionEvent event) {
    	App.primaryStage.close();
    	System.exit(0);
    }

	public MenuItem getAbrirItem() {
		return abrirItem;
	}

	public Button getAddContactoButton() {
		return addContactoButton;
	}

	public ListView<Contacto> getContactosList() {
		return contactosList;
	}

	public Button getDeleteContactoButton() {
		return deleteContactoButton;
	}

	public MenuItem getGuardarItem() {
		return guardarItem;
	}

	public MenuItem getNuevoItem() {
		return nuevoItem;
	}

	public BorderPane getRightView() {
		return rightView;
	}

	public MenuItem getSalirItem() {
		return salirItem;
	}

	public VBox getView() {
		return view;
	}
}
