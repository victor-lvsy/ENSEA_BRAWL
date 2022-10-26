import java.util.ArrayList;

public class Player {
    private String PlayerName;
    private int PlayerHp;
    private int PlayerGolds;
    private Player precedentOpponent = null;
    public Shop shop = new Shop();
    private ArrayList<Card> hand = new ArrayList<Card>();
    private ArrayList<Creature> onBoard = new ArrayList<Creature>();
    private ArrayList<Creature> currentOnBoard = new ArrayList<Creature>();
    public Player(String playerName, int playerHp, int playerGolds) {
        PlayerName = playerName;
        PlayerHp = playerHp;
        PlayerGolds = playerGolds;
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
    public void attackTurn(Player toBeFight,int willFight){
        boolean isDead[] = new boolean[2];
        ArrayList <Integer> haveTaunt = new ArrayList<Integer>();
        int alea,i=0,furieDesVents=0,j=0;
        if(this.currentOnBoard.get(willFight).getEffectList()[20]==true){
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
                    System.out.println(this.currentOnBoard.get(willFight) + " has been removed from "+this.PlayerName+" hand");
                    this.currentOnBoard.remove(willFight);
                }
                if (isDead[1]==true){
                    System.out.println(toBeFight.currentOnBoard.get(alea) + " has been removed from "+toBeFight.PlayerName+" currentBoard");
                    toBeFight.currentOnBoard.remove(alea);
                }
                System.out.println("_____________________________ROUND FINISHED_____________________________");
            }
        }
    }
    private void buyCreature(int indexOfCreature){
        this.hand.add(shop.actuallySelling.get(indexOfCreature));
        shop.actuallySelling.remove(indexOfCreature);
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
        return PlayerName;
    }

}
