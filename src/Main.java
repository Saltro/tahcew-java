import controllers.IndexController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import network.Session;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL location = getClass().getResource("resources/views/index.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

        Parent root = fxmlLoader.load();

        IndexController indexController = fxmlLoader.getController();
        Session session = new Session();
        indexController.setSession(session);

        Scene scene = new Scene(root, 1024, 600);
        primaryStage.setTitle("Tahcew Java版");
        primaryStage.setScene(scene);
        // 退出时关闭 session
        primaryStage.setOnCloseRequest(event -> session.close());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
