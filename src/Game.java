import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {
    static double width;
    static double height;
    Stage stage = new Stage();
    GameEngine gameEngine = new GameEngine();
    Welcome welcome = new Welcome();
    Scene welcomeScene = new Scene(welcome.getWelcome());
    Fight fight = new Fight();
    Scene fightScene = new Scene(fight.getFight());
    Win win = new Win();
    Scene winScene = new Scene(win.getWin());
    Lose lose = new Lose();
    Scene loseScene = new Scene(lose.getLose());
    Turn turn = new Turn();
    public static int gameTurn=1;
    public static int boardSize=7;
    public static Initialisation init = new Initialisation();
    Scene shopScene = new Scene(init.getPlayer1().getPaneShop());
    public static Player playerSave = new Player("Ghost",999999,0);

    public Game() {
        init.getPlayer1().Init();
        init.getPlayer1().InitShop();
        stage.setTitle("ENSEA BRAWL !");
        stage.minWidthProperty().bind(stage.heightProperty().multiply(16/9));
        stage.minHeightProperty().bind(stage.widthProperty().multiply(9/16));

        stage.show();

    }

    public static void endOfPlayerGame(int toBeTested){
        if(Initialisation.players.get(toBeTested).getPlayerHp()<=0 && Initialisation.players.get(toBeTested).getPlayerName().compareTo("Ghost")!=0){
            int k=1000;
            System.out.println(Initialisation.players.get(toBeTested).getPlayerName()+" is eliminated, his hp fell to 0.");
            playerSave.getOnBoard().addAll(Initialisation.players.get(toBeTested).getOnBoard());
            for(Creature creature:playerSave.getOnBoard()){
                creature.setTimeOnBoard(-999999999);
            }
            int j, l=Initialisation.players.get(toBeTested).getOnBoard().size(), m=Initialisation.players.get(toBeTested).getHand().size();
            for(j=0;j<l;j++){
                Initialisation.players.get(toBeTested).sellCreature(0);
            }
            for(j=0;j<m;j++){
                if (Initialisation.players.get(toBeTested).getHand().get(0) instanceof Creature){
                    Creature creature = new Creature();
                    creature.setCreature(Initialisation.players.get(toBeTested).getHand().get(0).getCardName(),"doc/effectListCSV_epure.csv");
                    Game.init.getCreaturePool().add(creature);
                }
                Initialisation.players.get(toBeTested).getHand().remove(0);

            }
            Initialisation.players.remove(toBeTested);
            for(int i=0;i<Initialisation.players.size();i++){
                if(Initialisation.players.get(i).getPlayerName()=="Ghost"){
                    k=i;
                }
            }
            if (k!=1000){
                Initialisation.players.remove(k);
                playerSave.getOnBoard().clear();
            }
            if(Initialisation.players.size()==3||Initialisation.players.size()==5){
                Initialisation.players.add(playerSave);
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        at.start();
    }


    AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long l) {
            width = stage.getWidth();
            height = stage.getHeight();
            gameEngine.update(welcome.getOutput());
            gameEngine.update(init.getPlayer1().getOutput());
            gameEngine.update(fight.getOutput());
            gameEngine.update(lose.getOutput());
            gameEngine.update(win.getOutput());
            welcome.clear();
            init.getPlayer1().clear();
            fight.clear();
            win.clear();
            lose.clear();
            welcome.update(width,height);
            init.getPlayer1().update(width,height,gameEngine.getState());
            if (turn.player1Enemy()!=null){
                fight.update(width,height,gameEngine.getState(),init.getPlayer1(),turn.player1Enemy());
            }
            win.update(width,height);
            lose.update(width,height);
            init.getPlayer1().getTimerShop().update(width,height);
            fight.getTimerFight().update(width,height);
            render();
        }
    };


    public void render(){

        switch (gameEngine.getState()) {
            case WELCOME:
                if (stage.getScene() != welcomeScene)
                {
                    //stage.setWidth(1920);
                    //stage.setHeight(1080);


                    stage.setScene(welcomeScene);
                    init.Initialisation();
                    stage.setMaximized(true);


                }
                break;
            case PLAY_SHOP:
                if (stage.getScene() != shopScene)
                {
                    if(Initialisation.players.size()<1){gameEngine.setState(State.WIN);}
                    if (init.getPlayer1().getPlayerHp()<1){gameEngine.setState(State.LOST);}
                    shopInit();
                    init.getPlayer1().getTimerShop().clear();
                    stage.setScene(shopScene);



                }
                break;
            case PLAY_FIGHT:
                if (stage.getScene() != fightScene)
                {
                    turn.Turn();
                    gameTurn++;
                    fight.getTimerFight().clear();
                    stage.setScene(fightScene);
                }
                break;
            case WIN:
                if (stage.getScene() != winScene)
                {
                    stage.setWidth(500);
                    stage.setHeight(400);
                    stage.setMaximized(false);
                    stage.centerOnScreen();
                    stage.setScene(winScene);
                }
                break;
            case LOST:
                if (stage.getScene() != loseScene)
                {
                    stage.setWidth(500);
                    stage.setHeight(400);
                    stage.setMaximized(false);
                    stage.centerOnScreen();
                    stage.setScene(loseScene);
                }
                break;
        }
    }

    public static void shopInit(){
        for(int i=0;i<Initialisation.players.size();i++){
            Initialisation.players.get(i).shopLevelUp();
            Initialisation.players.get(i).testLevelUp();
            System.out.println("Shop level: "+Initialisation.players.get(i).getShopLvl()+" Shop level up cost: "+Initialisation.players.get(i).getShopLevelUpCost());
            Initialisation.players.get(i).getShop().shop(Initialisation.players.get(i).getShopLvl(),i);
        }
    }
}

