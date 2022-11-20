import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private int playerHp;
    private int playerGolds;
    private Player precedentOpponent = null;
    public Shop shop = new Shop();
    private int shopLvl=1;
    private int shopLevelUpCost=5;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private ArrayList<Creature> onBoard = new ArrayList<Creature>();
    private ArrayList<Creature> currentOnBoard = new ArrayList<Creature>();
    private int archetypeList[];
    private final Pane pane = new Pane();
    Button fight = new Button("Passer au combat");
    Button lose = new Button("Lose");
    private String output;
    private final Counter timerShop = new Counter();
    public void clear(){
        output = "";
    }
    public Pane getPaneShop() {
        return pane;
    }
    public String getOutput() {
        return output;
    }
    public Counter getTimerShop() {
        return timerShop;
    }


    public void Init() {
        for (Card card : shop.getActuallySelling()) {
            pane.getChildren().add(card.getButton());
        }
        for (Card card : onBoard) {
            pane.getChildren().add(card.getButton());
        }
        for (Card card : onBoard) {
            pane.getChildren().add(card.getButton());
        }
    }

    public void InitShop() {
        pane.getChildren().add(fight);
        pane.getChildren().add(lose);
        pane.getChildren().add(timerShop.getTime());
        fight.setVisible(true);
        fight.setOnMouseClicked(mouseEvent -> output = "PLAY_FIGHT");
        lose.setVisible(true);
        lose.setOnMouseClicked(mouseEvent -> output = "LOST");
        timerShop.getTime().setFont(new Font("Comic sans MS", 20));
    }

    public Player(String newPlayerName, int newPlayerHp, int newPlayerGolds) {
        playerName = newPlayerName;
        playerHp = newPlayerHp;
        playerGolds = newPlayerGolds;
        archetypeList= new int[]{0, 0, 0, 0, 0, 0};
    }

    public void update(double width, double height, State state) {
        if (timerShop.getBool() && state == State.PLAY_SHOP) {
            this.output = "PLAY_FIGHT";
        }
        lose.setTranslateX(10);
        lose.setTranslateY(10);
        fight.setTranslateX((width - fight.getWidth()) / 2);
        fight.setTranslateY((height - fight.getHeight()) / 2);
        timerShop.getTime().setTranslateX(width - timerShop.getTime().getText().length()*10 - 20);
        timerShop.getTime().setTranslateY(height - 42);

        for (int i = 0; i < shop.getActuallySelling().size(); i++) {
            if (shop.getActuallySelling().get(i).getAction() == 1) {
                shop.getActuallySelling().get(i).clear();

                hand.add(shop.getActuallySelling().get(i));
                shop.getActuallySelling().remove(i);

            }
        }

        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getAction() == 2) {
                hand.get(i).clear();

                hand.add(hand.get(i));
                hand.remove(i);

            }
        }

        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getAction() == 3) {
                hand.get(i).clear();
                hand.get(i).getCardView().setVisible(false);
                hand.get(i).getButton().setVisible(false);
                hand.remove(i);

            }
        }


        for (Card card : shop.getActuallySelling()) {
            if (!pane.getChildren().contains(card.getCardView())) {
                pane.getChildren().add(card.getCardView());
            }
        }
        for (Card card : onBoard) {
            if (!pane.getChildren().contains(card.getCardView())) {
                pane.getChildren().add(card.getCardView());
            }
        }
        for (Card card : hand) {
            if (!pane.getChildren().contains(card.getCardView())) {
                pane.getChildren().add(card.getCardView());
            }
        }
        placeCards(width, height, shop.getActuallySelling(),onBoard,hand, true);

    }

    public void placeCards(double totalWidth, double totalHeight, ArrayList<Creature> enemy, ArrayList<Creature> board,
                           ArrayList<Card> hand, Boolean shopPhase) {
        int pasHorizontalBoardPlayer = (int) (totalWidth / (2 * board.size() + 1));
        int pasVertical = (int) (totalHeight / 7);
        for (int i = 0; i < board.size(); i++) {
            board.get(i).getCardView().setX((2 * i + 1) * pasHorizontalBoardPlayer);
            board.get(i).getCardView().setY(4 * pasVertical);
            board.get(i).getButton().setTranslateX((2 * i + 1) * pasHorizontalBoardPlayer);
            board.get(i).getButton().setTranslateY(4 * pasVertical + 60);
        }
        int pasHorizontalHandPlayer = (int) (totalWidth / (2 * hand.size() + 1));
        for (int i = 0; i < hand.size(); i++) {
            hand.get(i).getCardView().setX((2 * i + 1) * pasHorizontalHandPlayer);
            hand.get(i).getCardView().setY(6 * pasVertical);
            hand.get(i).getButton().setTranslateX((2 * i + 1) * pasHorizontalHandPlayer);
            hand.get(i).getButton().setTranslateY(6 * pasVertical + 60);
        }
        int pasHorizontalBoardEnemy = (int) (totalWidth / (2 * enemy.size() + 1));
        for (int i = 0; i < enemy.size(); i++) {
            enemy.get(i).getCardView().setX((2 * i + 1) * pasHorizontalBoardEnemy);
            enemy.get(i).getCardView().setY(2 * pasVertical);
            enemy.get(i).getButton().setTranslateX((2 * i + 1) * pasHorizontalBoardEnemy);
            enemy.get(i).getButton().setTranslateY(2 * pasVertical + 60);
        }

        if (shopPhase) {
            for (int i = 0; i < board.size(); i++) {
                for (int j = i + 1; j < board.size(); j++)
                    if (board.get(i).isBool() && board.get(j).isBool()) {
                        Creature creaTampon = board.get(i);
                        board.set(i, board.get(j));
                        board.set(j, creaTampon);
                        board.get(i).clear();
                        board.get(i).setNewBool(false);
                        board.get(j).clear();
                        board.get(j).setNewBool(false);
                    }
            }

            for (Card card : enemy) {
                if (card.isBool()) {
                    card.getButton().setVisible(true);
                    card.getCardView().setEffect(new Shadow(1, Color.BLACK));
                    card.getButton().setText("Buy");
                }
            }
            for (Card card : hand) {
                if (card.isBool()) {
                    card.getButton().setVisible(true);
                    card.getCardView().setEffect(new Shadow(1, Color.BLACK));

                    card.getButton().setText("Put");
                }
            }
            for (Card card : board) {
                if (card.isBool()) {
                    card.getButton().setVisible(true);
                    card.getCardView().setEffect(new Shadow(1, Color.BLACK));
                    card.getButton().setText("Sell");
                }
            }

            ArrayList<Card> tamp = new ArrayList<>();
            tamp.addAll(hand);
            tamp.addAll(board);
            tamp.addAll(enemy);
            for ( Card card : tamp){
                if (card.isNewBool()){
                    for (Card card0 : enemy) {
                        card0.clear();
                    }
                    for (Card card0 : hand) {
                        card0.clear();
                    }
                    for (Card card0 : board) {
                        card0.clear();
                    }
                    card.setNewBool(false);
                    card.setBool(true);
                }

            }

        }

    }


    public ArrayList<Creature> getOnBoard() {
        return onBoard;
    }
    public ArrayList<Creature> getCurrentOnBoard() {
        return currentOnBoard;
    }
    public Shop getShop() {
        return shop;
    }
    public int[] getArchetypeList() {
        return archetypeList;
    }

    public void setCurrentOnBoard() {
        this.currentOnBoard = currentOnBoard;
    }
    public void setArchetypeList(int[] archetypeList) {
        this.archetypeList = archetypeList;
    }

    public void attackTurn(Player toBeFight, int willFight){
        boolean isDead[] = new boolean[2];
        ArrayList <Integer> haveTaunt = new ArrayList<Integer>();
        int alea,i=0,furieDesVents=0,j=0;
        if(this.currentOnBoard.get(willFight).getEffectList()[46]==true){
            furieDesVents=1;
        }
        for(j=0; j == furieDesVents; j++){
            if(this.currentOnBoard.get(willFight).getCreatureHp()<=0){
                System.out.println("This creature "+this.currentOnBoard.get(willFight).getCardName() +" is dead impossible to attack");
            }
            else{
                for (Creature creature : toBeFight.getCurrentOnBoard()){
                    if(creature.getEffectList()[35]==true){
                        haveTaunt.add(i);
                    }
                    i++;
                }
                if (haveTaunt.size()!=0){
                    i=1;
                    alea=(int) (Math.random() * (haveTaunt.size()));
                    while(toBeFight.getCurrentOnBoard().get(haveTaunt.get(alea)).getEffectList()[8]==true){
                        alea=(int) (Math.random() * (haveTaunt.size()));
                        i++;
                        if(i==haveTaunt.size()){
                            toBeFight.getCurrentOnBoard().get(haveTaunt.get(alea)).setEffectList(false,8);
                            System.out.println("Camouflage is set to false because of impossibility to attack");
                        }
                    }
                    alea=haveTaunt.get(alea);
                    System.out.println( this.getPlayerName() +" "+ this.getCurrentOnBoard().get(willFight).getCardName() + " attacked " +toBeFight.getPlayerName()+" "+toBeFight.currentOnBoard.get(alea));
                    isDead = this.onBoard.get(willFight).attackCreature(toBeFight.getCurrentOnBoard().get(alea));
                }
                else{
                    int k=1;
                    alea=(int) (Math.random() * (toBeFight.getCurrentOnBoard().size()));
                    while(toBeFight.getCurrentOnBoard().get(alea).getEffectList()[8]==true){
                        alea=(int) (Math.random() * (toBeFight.getCurrentOnBoard().size()));
                        if(k==toBeFight.getCurrentOnBoard().size()){
                            toBeFight.getCurrentOnBoard().get(alea).setEffectList(false,8);
                            System.out.println("Camouflage is set to false because of impossibility to attack");
                        }
                        k++;
                    }
                    System.out.println( this.getPlayerName() +" "+ this.getCurrentOnBoard().get(willFight).getCardName() + " attacked " +toBeFight.getPlayerName()+" "+toBeFight.currentOnBoard.get(alea));
                    isDead = this.currentOnBoard.get(willFight).attackCreature(toBeFight.getCurrentOnBoard().get(alea));
                }
                if (isDead[0]==true){
                    System.out.println(this.currentOnBoard.get(willFight) + " has been removed from "+this.playerName +" currentBoard");
                    this.currentOnBoard.remove(willFight);
                }
                if (isDead[1]==true){
                    System.out.println(toBeFight.currentOnBoard.get(alea) + " has been removed from "+toBeFight.playerName +" currentBoard");
                    toBeFight.currentOnBoard.remove(alea);
                }
                System.out.println("_____________________________ROUND FINISHED_____________________________");
            }
        }
    }

    public void buyCreature(int indexOfCreature){
        if(shop.getActuallySelling().get(indexOfCreature).getEffectList()[10]==false){
            if(this.getPlayerGolds()>=3){
                this.hand.add(shop.getActuallySelling().get(indexOfCreature));
                this.setPlayerGolds(this.getPlayerGolds()-3);
                System.out.println("Creature: "+shop.getActuallySelling().get(indexOfCreature).getCardName()+" has been bought");
                shop.getActuallySelling().remove(indexOfCreature);
            }
            else{
                System.out.println("Not enough golds to buy this creature");
            }
        }
        else{
            this.hand.add(shop.getActuallySelling().get(indexOfCreature));
            System.out.println("Creature: "+shop.getActuallySelling().get(indexOfCreature).getCardName()+" has been bought");
            shop.getActuallySelling().remove(indexOfCreature);
        }
    }

    public void sellCreature(int indexOfCreature, String i){
        if(i=="Hand"){
            Creature creature = new Creature();
            creature.setCreature(this.hand.get(indexOfCreature).getCardName(),"doc/effectListCSV_epure.csv");
            Game.init.getCreaturePool().add(creature);
            System.out.println("Creature: "+this.hand.get(indexOfCreature).getCardName()+" has been sold");
            if(this.hand.get(indexOfCreature).getCardName()=="Absenteiste"){
                this.setPlayerGolds(cardValue());
            }
            else {
                this.setPlayerGolds(this.getPlayerGolds()+1);
            }
            this.hand.remove(indexOfCreature);
        }
        if(i=="Board"){
            Creature creature = new Creature();
            creature.setCreature(this.onBoard.get(indexOfCreature).getCardName(),"doc/effectListCSV_epure.csv");
            Game.init.getCreaturePool().add(creature);
            System.out.println("Creature: "+this.onBoard.get(indexOfCreature).getCardName()+" has been sold");
            if(this.onBoard.get(indexOfCreature).getCardName()=="Absenteiste"){
                this.setPlayerGolds(cardValue());
            }
            else {
                this.setPlayerGolds(this.getPlayerGolds()+1);
            }
            this.onBoard.remove(indexOfCreature);
        }
        else {
            System.out.println("ERROR, argument has to be set to Board or Hand");
        }
    }

    public int cardValue(){
        int i=0;
        for (Creature creature:this.onBoard){
            if(creature.getEffectList()[23]==true){
                i=i+2;
            }
        }
        if(i==0){
            i++;
        }
        return i;
    }

    public void handToBoard(int indexOfHand){
        if(this.hand.get(indexOfHand) instanceof Creature){
            this.onBoard.add((Creature) this.hand.get(indexOfHand));
            this.hand.remove(indexOfHand);
        }
        else {
            System.out.println("This card "+ this.hand.get(indexOfHand).getCardName() +" is not a creature");
        }
    }

    public void boardToHand(int indexOfHand){
            this.hand.add( this.getOnBoard().get(indexOfHand));
            this.getOnBoard().remove(indexOfHand);
    }

    public void invertCards(int toBeInverted1,int toBeInverted2){
        this.onBoard.set(toBeInverted1,this.onBoard.set(toBeInverted2,this.onBoard.get(toBeInverted1)));

    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void setOnBoard(ArrayList<Creature> onBoard) {
        this.onBoard = onBoard;
    }

    public Player getPrecedentOpponent() {
        return precedentOpponent;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public void setPlayerHp(int playerHp) {
        this.playerHp = playerHp;
    }

    public int getPlayerGolds() {
        return playerGolds;
    }

    public int getShopLvl() {
        return shopLvl;
    }

    public void setPlayerGolds(int golds) {
        playerGolds = golds;
    }

    public void setShopLvl(int shopLvl) {
        this.shopLvl = shopLvl;
    }

    public void shopLevelUp(){
        if(shopLvl<5){
            shopLevelUpCost--;
            playerGolds--;
            testLevelUp();
    }
    else {
        System.out.println("Last shop level reached");
    }
    }

    public int getShopLevelUpCost() {
        return shopLevelUpCost;
    }

    public void setShopLevelUpCost(int shopLevelUpCost) {
        this.shopLevelUpCost = shopLevelUpCost;
    }

    public void testLevelUp(){
        if(shopLevelUpCost==0){
            switch (shopLvl){
                case 1: shopLevelUpCost=8;shopLvl++;System.out.println("SHOP LEVEL UP");break;
                case 2: shopLevelUpCost=10;shopLvl++;System.out.println("SHOP LEVEL UP");break;
                case 3: shopLevelUpCost=12;shopLvl++;System.out.println("SHOP LEVEL UP");break;
                case 4: shopLvl++;System.out.println("SHOP LEVEL UP, last level reached");break;
            }
        }
    }

    public void cestQuoiUneListe(){
        this.archetypeList[0]=this.archetypeList[0]+2;//à modifier
    }

    public void tauntEtRivalite(Player player1,Player player2){
        boolean isDead[]=new boolean[]{false,false};
        ArrayList<Creature>rivaux=new ArrayList<>();
        for(Creature creature:player1.getCurrentOnBoard()){
            if(creature.getEffectList()[36]==true){
                rivaux.add(creature);
            }
        }
        for(Creature creature:player2.getCurrentOnBoard()){
            if(creature.getEffectList()[36]==true){
                rivaux.add(creature);
            }
        }
        while(rivaux.size()>1){
            int rand = (int) Math.floor(Math.random() * rivaux.size()), rand2 = rand+1;
            if(rand2>=rivaux.size()){rand2=0;}
            isDead=rivaux.get(rand).attackCreature(rivaux.get(rand2));
            if (isDead[0]==true){
                System.out.println(player1.currentOnBoard.get(rand) + " has been removed from "+player1.playerName +" currentBoard");
                player1.currentOnBoard.remove(rivaux.get(rand));
                rivaux.remove(rand);
            }
            if (isDead[1]==true){
                System.out.println(player2.currentOnBoard.get(rand2) + " has been removed from "+player2.playerName +" currentBoard");
                player2.currentOnBoard.remove(rivaux.get(rand2));
            }
        }
    }
}
