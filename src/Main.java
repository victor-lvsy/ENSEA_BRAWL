import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Hello world");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        at.start();
    }

    AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long l) {


        }
    };

    public static void main(String[] args) {
        launch(args);
    }


}
