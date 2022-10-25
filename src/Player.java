import java.util.ArrayList;

public class Player {

    private String PlayerName;

    private int PlayerHp;

    private ArrayList<Card> hand;

    public ArrayList<Creature> getOnBoard() {
        return onBoard;
    }

    private ArrayList<Creature> onBoard;

    public void attackTurn(Player toBeFight,int willFight){
        ArrayList <Integer> bouvierDivin;
        int alea,i=0;
        for (Creature creature : toBeFight.getOnBoard()){
            if(creature.getEffectList()[35]==true){

            }
            i++;
        }
        alea=(int) (Math.random() * (toBeFight.getOnBoard().size()));
        this.onBoard.get(willFight).attackCreature(toBeFight.getOnBoard().get(alea));
    }
}
