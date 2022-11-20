import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application  {
    Creature creature = new Creature();
    Creature creature2 = new Creature();
    Group group = new Group();
    Pane pane = new  Pane(creature.getPane());


    @Override
    public void start(Stage primaryStage) throws Exception{
        creature.setCardName("President BDS");
        creature2.setCardName("Directeur des etudes");
        Scene scene = new Scene(group);
        primaryStage.setTitle("Hello world");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        group.getChildren().add(pane);
        group.getChildren().add(creature2.getPane());
        pane.setTranslateX(1000);
        at.start();

    }

    AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long l) {
            creature.setCreature("President BDS","doc/effectListCSV_epure.csv");
            creature.getShopLevel().setText(String.valueOf(creature.getCreatureTier()));
            creature.update();
            creature2.setCreature("Directeur des etudes","doc/effectListCSV_epure.csv");
            creature2.getShopLevel().setText(String.valueOf(creature2.getCreatureTier()));
            creature2.update();
        }
    };

    public static void main(String[] args) {
        launch(args);
    }


}
