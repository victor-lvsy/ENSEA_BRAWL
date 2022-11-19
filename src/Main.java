import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application  {
    Creature creature = new Creature();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Scene scene = new Scene(creature.getPane());
        primaryStage.setTitle("Hello world");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        at.start();
    }

    AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long l) {
            creature.setCardName("Directeur des etudes");
            creature.setCreature(String.valueOf(creature.getCardName()),"doc/effectListCSV_epure.csv");
            creature.getAttack().setText(String.valueOf(creature.getCreatureAtt()));
            creature.getHealth().setText(String.valueOf(creature.getCreatureHp()));
            creature.getShopLevel().setText(String.valueOf(creature.getCreatureTier()));
            creature.update();


        }
    };

    public static void main(String[] args) {
        launch(args);
    }


}
