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
    Pane pane = new  Pane(creature2.getPane());
    Pane pane2 = new Pane(creature.getPane());


    @Override
    public void start(Stage primaryStage) throws Exception{
        creature.reduit();
        creature.setCardName("Prof d'info");
        creature2.setCardName("Directeur des etudes");
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        group.getChildren().add(pane);
        group.getChildren().add(pane2);
        pane.setScaleX(primaryStage.getWidth()/200);
        pane.setScaleY((primaryStage.getHeight()-37)/317);
        pane2.setScaleX(1.8);
        pane2.setScaleY(0.5);
        pane2.setTranslateX(900);
        pane2.setTranslateY(10);
        pane.setTranslateX(670);
        pane.setTranslateY(240);
        pane2.setRotate(180);
        at.start();
        creature.getShopLevel().setText(String.valueOf(creature.getCreatureTier()));
        creature2.getShopLevel().setText(String.valueOf(creature2.getCreatureTier()));
        creature2.setCreature("Directeur des etudes","doc/effectListCSV_epure.csv");
        creature.setCreature("Prof d'info","doc/effectListCSV_epure.csv");
        creature.update();
        creature2.update();

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
