package dad.gesCon.ui.contacto.telefono;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.contactos.model.TipoTelefono;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TelefonoController implements Initializable {

	@FXML
    private Button cancelarButton;

    @FXML
    private Button nuevoButton;
	
	@FXML
    private TextField telefonoField;

    @FXML
    private ComboBox<TipoTelefono> tipoComboBox;

    @FXML
    private GridPane view;
    
    private TelefonoModel model;
	
	public TelefonoController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelefonoView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = new TelefonoModel();
		
		model.telefonoProperty().bindBidirectional(telefonoField.textProperty());
		model.tipoTelefonoProperty().bindBidirectional(tipoComboBox.valueProperty());
		
		tipoComboBox.getItems().addAll(TipoTelefono.values());
		
		nuevoButton.disableProperty().bind(
				Bindings.when(telefonoField.textProperty().isNotNull().and(tipoComboBox.valueProperty().isNotNull()))
				.then(false)
				.otherwise(true)
		);
	}
	
	public Button getNuevoButton() {
		return nuevoButton;
	}
	
	public Button getCancelarButton() {
		return cancelarButton;
	}

	public TextField getTelefonoField() {
		return telefonoField;
	}

	public ComboBox<TipoTelefono> getTipoComboBox() {
		return tipoComboBox;
	}

	public TelefonoModel getModel() {
		return model;
	}
	
	public GridPane getView() {
		return view;
	}
}
