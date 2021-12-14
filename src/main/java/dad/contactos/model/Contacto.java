package dad.contactos.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import dad.contactos.model.adapter.ImageAdapter;
import dad.contactos.model.adapter.LocalDateAdapter;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

@XmlType
public class Contacto {
	private StringProperty nombre;
	private StringProperty apellidos;
	private ObjectProperty<Image> foto;
	private ObjectProperty<LocalDate> fechaNacimiento;
	private ListProperty<Telefono> telefonos;

	public Contacto(String nombre, String apellidos, LocalDate fechaNacimiento) {
		this.nombre = new SimpleStringProperty(this, "nombre", nombre);
		this.apellidos = new SimpleStringProperty(this, "apellidos", apellidos);
		this.foto = new SimpleObjectProperty<Image>(this, "foto");
		this.fechaNacimiento = new SimpleObjectProperty<LocalDate>(this, "fechaNacimiento", fechaNacimiento);
		this.telefonos = new SimpleListProperty<>(this, "telefonos", FXCollections.observableArrayList());
	}

	public Contacto() {
		this("", "", null);
	}

	public final StringProperty nombreProperty() {
		return this.nombre;
	}

	@XmlAttribute
	public final String getNombre() {
		return this.nombreProperty().get();
	}

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final StringProperty apellidosProperty() {
		return this.apellidos;
	}

	@XmlAttribute
	public final String getApellidos() {
		return this.apellidosProperty().get();
	}

	public final void setApellidos(final String apellidos) {
		this.apellidosProperty().set(apellidos);
	}

	public final ObjectProperty<LocalDate> fechaNacimientoProperty() {
		return this.fechaNacimiento;
	}

	@XmlAttribute
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public final LocalDate getFechaNacimiento() {
		return this.fechaNacimientoProperty().get();
	}

	public final void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimientoProperty().set(fechaNacimiento);
	}

	public final ListProperty<Telefono> telefonosProperty() {
		return this.telefonos;
	}

	@XmlElement(name="telefono")
	@XmlElementWrapper(name="telefonos")
	public final ObservableList<Telefono> getTelefonos() {
		return this.telefonosProperty().get();
	}

	public final void setTelefonos(final ObservableList<Telefono> telefonos) {
		this.telefonosProperty().set(telefonos);
	}

	public final ObjectProperty<Image> fotoProperty() {
		return this.foto;
	}

	@XmlElement
	@XmlJavaTypeAdapter(ImageAdapter.class)
	public final Image getFoto() {
		return this.fotoProperty().get();
	}

	public final void setFoto(final Image foto) {
		this.fotoProperty().set(foto);
	}

	@Override
	public String toString() {
		return (getNombre() + " " + getApellidos()).trim();
	}
}
