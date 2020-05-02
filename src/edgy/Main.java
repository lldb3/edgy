package edgy;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.opencv.core.Core;

public class Main extends Application
{
	/**
	 * The main class for a JavaFX application. It creates and handle the main
	 * window with its resources (style, graphics, etc.).
	 *
	 * This application apply the Canny filter to the camera video stream or try
	 * to remove a uniform background with the erosion and dilation operators.
	 *
	 */
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			// load the FXML resource
			FXMLLoader loader = new FXMLLoader(getClass().getResource("layout.fxml"));
			BorderPane root = (BorderPane) loader.load();

			// set a whitesmoke background
			root.setStyle("-fx-background-color: whitesmoke;");
			// create and style a scene
			Scene scene = new Scene(root, 800, 600);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// create the stage with the given title and the previously created
			// scene
			primaryStage.setTitle("Edgy Motion detection app");
			primaryStage.setScene(scene);

			// show the GUI
			primaryStage.show();

			// get the controller
			Controller controller = loader.getController();
			controller.init();

			// set the proper behavior on closing the application
			primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we)
				{
					controller.setClosed();
				}
			}));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		// load the native OpenCV library
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		launch(args);
	}
}
