package dad.gesCon.ui.contacto;

import java.time.LocalDate;

import dad.contactos.model.Telefono;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class ContactoModel {

	private StringProperty nombre;

	private StringProperty apellidos;

	private ObjectProperty<LocalDate> fechaNacimiento;

	private ListProperty<Telefono> telefonos;

	private ObjectProperty<Image> foto;

	public ContactoModel() {
		nombre = new SimpleStringProperty();
		apellidos = new SimpleStringProperty();
		fechaNacimiento = new SimpleObjectProperty<>();
		telefonos = new SimpleListProperty<>();
		foto = new SimpleObjectProperty<>();
	}

	public final StringProperty nombreProperty() {
		return this.nombre;
	}

	public final String getNombre() {
		return this.nombreProperty().get();
	}

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final StringProperty apellidosProperty() {
		return this.apellidos;
	}

	public final String getApellidos() {
		return this.apellidosProperty().get();
	}

	public final void setApellidos(final String apellidos) {
		this.apellidosProperty().set(apellidos);
	}

	public final ObjectProperty<LocalDate> fechaNacimientoProperty() {
		return this.fechaNacimiento;
	}

	public final LocalDate getFechaNacimiento() {
		return this.fechaNacimientoProperty().get();
	}

	public final void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimientoProperty().set(fechaNacimiento);
	}

	public final ListProperty<Telefono> telefonosProperty() {
		return this.telefonos;
	}

	public final ObservableList<Telefono> getTelefonos() {
		return this.telefonosProperty().get();
	}

	public final void setTelefonos(final ObservableList<Telefono> telefonos) {
		this.telefonosProperty().set(telefonos);
	}

	public final ObjectProperty<Image> fotoProperty() {
		return this.foto;
	}

	public final Image getFoto() {
		return this.fotoProperty().get();
	}

	public final void setFoto(final Image foto) {
		this.fotoProperty().set(foto);
	}

}
