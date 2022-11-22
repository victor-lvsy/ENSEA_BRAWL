import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Main extends Application  {
    private Group group = new Group();
    private Initialisation init = new Initialisation();
    private ArrayList<Creature> creas = new ArrayList<>();
    private Button button = new Button("Passe");
    private Pane pane = new Pane(group);
    private int i = 0;
    private Scene scene = new Scene(pane);
    Stage stage = new Stage();



    @Override
    public void start(Stage stage) throws Exception{
        init.Initialisation();
        for (String name : init.getNameList()) {
            Creature tamp = new Creature();
            tamp.setCreature(name,"doc/effectListCSV_epure.csv");
            creas.add(tamp);
        }
        group.getChildren().add(creas.get(i).getPane());
        creas.get(i).entiere();
        creas.get(i).getPane().setTranslateX(680);
        creas.get(i).getPane().setTranslateY(250);
        creas.get(i).getPane().setScaleX(2);
        creas.get(i).getPane().setScaleY(2);
        group.getChildren().add(button);
        stage.setScene(scene);


        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                group.getChildren().remove(creas.get(i).getPane());
                i++;
                group.getChildren().add(creas.get(i).getPane());
                creas.get(i).entiere();
                creas.get(i).getPane().setTranslateX(680);
                creas.get(i).getPane().setTranslateY(250);
                creas.get(i).getPane().setScaleX(2);
                creas.get(i).getPane().setScaleY(2);

            }
        });

        stage.setMaximized(true);
        stage.show();


    }

    AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long l) {
            pane = creas.get(i).getPane();
            scene = new Scene(pane);
            stage.setScene(scene);


        }
    };



}
