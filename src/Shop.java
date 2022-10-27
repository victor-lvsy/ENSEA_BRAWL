import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Shop {
    private int level=5;

    private ArrayList<Creature> actuallySelling = new ArrayList<Creature>();

    private ArrayList<Creature> canBeSold = new ArrayList<Creature>();

    public void Shop(){
        ArrayList<Integer> indexToBeRemoved = new ArrayList<Integer>();
        int i=0;
        for (Creature creature : Test.init.getCreaturePool()) {
            if(creature.getCreatureTier()<=level){
                canBeSold.add(creature);
                indexToBeRemoved.add(i);
            }
            i++;
        }
        Collections.sort(indexToBeRemoved, Collections.reverseOrder());
        for (Integer j : indexToBeRemoved) {
            Test.init.getCreaturePool().remove((int) j);
        }
        switch (level){
            case 1: setShop(3);break;
            case 2: setShop(4);break;
            case 3: setShop(4);break;
            case 4: setShop(5);break;
            case 5: setShop(6);break;
        }

    }

    private void setShop(int numberOfDraw){
        int choice;
        for(int i=0;i<numberOfDraw;i++){
            choice = (int) (Math.random() * (canBeSold.size()));
            actuallySelling.add(canBeSold.get(choice));
            canBeSold.remove(choice);
        }
        Test.init.getCreaturePool().addAll(canBeSold);
        canBeSold.clear();
        System.out.println("================================BOUTIQUE================================");
        for (Creature creature:actuallySelling){
            System.out.println(creature);
        }
        System.out.println("========================================================================");
    }
    public ArrayList<Creature> getCanBeSold() {
        return canBeSold;
    }
    public ArrayList<Creature> getActuallySelling() {
        return actuallySelling;
    }
}
