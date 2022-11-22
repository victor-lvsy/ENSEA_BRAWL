import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private int j = 0;
    private Scene scene = new Scene(pane);
    Stage stage = new Stage();
    private ArrayList<ImageView> imgs = new ArrayList<>();


    @Override
    public void start(Stage stage) throws Exception{
        init.Initialisation();
        for (String name : init.getNameList()) {
            Creature tamp = new Creature();
            tamp.setCreature(name,"doc/effectListCSV_epure.csv");
            creas.add(tamp);
        }
        imgs.add(new ImageView(new Image("file:ImageJeu/joueur1.png")));
        imgs.add(new ImageView(new Image("file:ImageJeu/joueur2.png")));
        imgs.add(new ImageView(new Image("file:ImageJeu/joueur3.png")));
        imgs.add(new ImageView(new Image("file:ImageJeu/joueur4.png")));
        imgs.add(new ImageView(new Image("file:ImageJeu/joueur5.png")));
        imgs.add(new ImageView(new Image("file:ImageJeu/joueur6.png")));
        imgs.add(new ImageView(new Image("file:ImageJeu/tavernier.png")));

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
                if (i<creas.size()-1) {
                    group.getChildren().remove(creas.get(i).getPane());
                    i++;
                    group.getChildren().add(creas.get(i).getPane());
                    creas.get(i).entiere();
                    creas.get(i).getPane().setTranslateX(680);
                    creas.get(i).getPane().setTranslateY(250);
                    creas.get(i).getPane().setScaleX(2);
                    creas.get(i).getPane().setScaleY(2);
                } else if (i == creas.size()){
                    group.getChildren().remove(creas.get(i-1).getPane());
                    group.getChildren().remove(imgs.get(i+1-creas.size()));
                    i++;
                    group.getChildren().add(imgs.get(i+1-creas.size()));
                    imgs.get(i+1-creas.size()).setTranslateX(680);
                    imgs.get(i+1-creas.size()).setTranslateY(250);
                    imgs.get(i+1-creas.size()).setScaleX(2);
                    imgs.get(i+1-creas.size()).setScaleY(2);
                } else {
                    group.getChildren().remove(imgs.get(i+1-creas.size()));
                    i++;
                    group.getChildren().add(imgs.get(i+1-creas.size()));
                    imgs.get(i+1-creas.size()).setTranslateX(680);
                    imgs.get(i+1-creas.size()).setTranslateY(250);
                    imgs.get(i+1-creas.size()).setScaleX(2);
                    imgs.get(i+1-creas.size()).setScaleY(2);
                }


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
