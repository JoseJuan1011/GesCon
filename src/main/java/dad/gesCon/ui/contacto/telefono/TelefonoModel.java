package dad.gesCon.ui.contacto.telefono;

import dad.contactos.model.TipoTelefono;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TelefonoModel {

	private StringProperty telefono;

	private ObjectProperty<TipoTelefono> tipoTelefono;

	public TelefonoModel() {
		telefono = new SimpleStringProperty();
		tipoTelefono = new SimpleObjectProperty<>();
	}

	public final StringProperty telefonoProperty() {
		return this.telefono;
	}

	public final String getTelefono() {
		return this.telefonoProperty().get();
	}

	public final void setTelefono(final String telefono) {
		this.telefonoProperty().set(telefono);
	}

	public final ObjectProperty<TipoTelefono> tipoTelefonoProperty() {
		return this.tipoTelefono;
	}

	public final TipoTelefono getTipoTelefono() {
		return this.tipoTelefonoProperty().get();
	}

	public final void setTipoTelefono(final TipoTelefono tipoTelefono) {
		this.tipoTelefonoProperty().set(tipoTelefono);
	}
}
