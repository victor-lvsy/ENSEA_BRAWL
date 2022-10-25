import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {

    private String PlayerName;
    private int PlayerHp;
    private ArrayList<Card> hand;
    private ArrayList<Creature> onBoard;
    private ArrayList<Creature> currentOnBoard;
    public ArrayList<Creature> getOnBoard() {
        return onBoard;
    }
    public ArrayList<Creature> getCurrentOnBoard() {
        return currentOnBoard;
    }
    public void setCurrentOnBoard(ArrayList<Creature> currentOnBoard) {
        this.currentOnBoard = currentOnBoard;
    }
    public void attackTurn(Player toBeFight,int willFight){
        boolean isDead[] = new boolean[2];
        ArrayList <Integer> haveTaunt = null;
        int alea,i=0;
        for (Creature creature : toBeFight.getCurrentOnBoard()){
            if(creature.getEffectList()[35]==true){
                haveTaunt.add(i);
            }
            i++;
        }
        if (haveTaunt != null){
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
            isDead = this.onBoard.get(willFight).attackCreature(toBeFight.getCurrentOnBoard().get(haveTaunt.get(alea)));
        }
        else{
            alea=(int) (Math.random() * (toBeFight.getCurrentOnBoard().size()));
            while(toBeFight.getCurrentOnBoard().get(haveTaunt.get(alea)).getEffectList()[8]==true){
                alea=(int) (Math.random() * (toBeFight.getCurrentOnBoard().size()));
                if(i==toBeFight.getCurrentOnBoard().size()){
                    toBeFight.getCurrentOnBoard().get(alea).setEffectList(false,8);
                    System.out.println("Camouflage is set to false because of impossibility to attack");
                }
            }
            isDead = this.onBoard.get(willFight).attackCreature(toBeFight.getCurrentOnBoard().get(alea));
        }
        if (isDead[0]==true){
            this.currentOnBoard.remove(willFight);
        }
        if (isDead[1]==true){
            toBeFight.currentOnBoard.remove(alea);
        }
    }

}
