package dad.contactos.ui;

import java.io.File;
import java.time.LocalDate;

import dad.contactos.model.Agenda;
import dad.contactos.model.Contacto;
import dad.contactos.model.Telefono;
import dad.contactos.model.TipoTelefono;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
		
	@Override
	public void start(Stage primaryStage) throws Exception {

		File fichero = new File("fotos/Chuck_Norris.jpg");
		Image imagen = new Image(fichero.toURI().toURL().toExternalForm());

		Contacto c1 = new Contacto();
		c1.setNombre("Chuck");
		c1.setApellidos("Norris");
		c1.setFechaNacimiento(LocalDate.of(1956, 3, 25));
		c1.setFoto(imagen);
		c1.getTelefonos().add(new Telefono("922102030", TipoTelefono.CASA));
		c1.getTelefonos().add(new Telefono("666777888", TipoTelefono.FAX));

		Agenda agenda = new Agenda();
		agenda.getContactos().addAll(c1);
		agenda.save(new File("contactos.agenda"));
		
		Platform.exit();
		
	}

	
	public static void main(String[] args) {
		launch(args);
	}

}
