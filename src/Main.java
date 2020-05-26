
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends javafx.application.Application {



    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("display.fxml"));
        stage.setTitle("Olympia");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args){
        System.out.println("---------------------------");
        System.out.println("---------OlympiaRL---------");
        System.out.println("---------------------------");
        launch(args);

    }
}

