package dad.gesCon.ui.main;

import dad.contactos.model.Agenda;
import dad.contactos.model.Contacto;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class MainModel {

	private ObjectProperty<Agenda> agendaFX;

	private ListProperty<Contacto> contactos;
	
	private ObjectProperty<Contacto> contactoSeleccionado;
	
	public MainModel() {
		agendaFX = new SimpleObjectProperty<>(new Agenda());
		contactos = new SimpleListProperty<>();
		contactoSeleccionado = new SimpleObjectProperty<>();
	}

	public final ObjectProperty<Agenda> agendaFXProperty() {
		return this.agendaFX;
	}

	public final Agenda getAgendaFX() {
		return this.agendaFXProperty().get();
	}

	public final void setAgendaFX(final Agenda agendaFX) {
		this.agendaFXProperty().set(agendaFX);
	}

	public final ListProperty<Contacto> contactosProperty() {
		return this.contactos;
	}

	public final ObservableList<Contacto> getContactos() {
		return this.contactosProperty().get();
	}

	public final void setContactos(final ObservableList<Contacto> contactos) {
		this.contactosProperty().set(contactos);
	}

	public final ObjectProperty<Contacto> contactoSeleccionadoProperty() {
		return this.contactoSeleccionado;
	}

	public final Contacto getContactoSeleccionado() {
		return this.contactoSeleccionadoProperty().get();
	}

	public final void setContactoSeleccionado(final Contacto contactoSeleccionado) {
		this.contactoSeleccionadoProperty().set(contactoSeleccionado);
	}

}
