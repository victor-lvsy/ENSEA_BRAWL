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

    public Player(String newPlayerName, int newPlayerHp, int newPlayerGolds) {
        playerName = newPlayerName;
        playerHp = newPlayerHp;
        playerGolds = newPlayerGolds;
        archetypeList= new int[]{0, 0, 0, 0, 0, 0};
    }

    public ArrayList<Creature> getOnBoard() {
        return onBoard;
    }

    public ArrayList<Creature> getCurrentOnBoard() {
        return currentOnBoard;
    }

    public void setCurrentOnBoard() {
        this.currentOnBoard = currentOnBoard;
    }

    public Shop getShop() {
        return shop;
    }

    public int[] getArchetypeList() {
        return archetypeList;
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

    public void boostAleatoART(Player player){
        int rand = (int) Math.floor(Math.random() * player.getCurrentOnBoard().size());
        player.getCurrentOnBoard().get(rand).setCreatureAtt(player.getCurrentOnBoard().get(rand).getCreatureAtt()+1);
        player.getCurrentOnBoard().get(rand).setCreatureHp(player.getCurrentOnBoard().get(rand).getCreatureHp()+1);
        rand = (int) Math.floor(Math.random() * player.getCurrentOnBoard().size());
        player.getCurrentOnBoard().get(rand).setCreatureAtt(player.getCurrentOnBoard().get(rand).getCreatureAtt()+1);
        player.getCurrentOnBoard().get(rand).setCreatureHp(player.getCurrentOnBoard().get(rand).getCreatureHp()+1);
    }
}
