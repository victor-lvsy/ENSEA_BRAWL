import java.util.ArrayList;

public class Shop {
    private int level=3;
    ArrayList<Creature> actuallySelling = new ArrayList<Creature>();
    ArrayList<Creature> canBeSold = new ArrayList<Creature>();

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
        for (Integer j : indexToBeRemoved) {
            Test.init.getCreaturePool().remove(j);
        }
        switch (level){
            case 1: setShop(3);break;
            case 2: setShop(4);break;
            case 3: setShop(4);break;
            case 4: setShop(5);break;
            case 5: setShop(6);break;
        }
        i=0;
        indexToBeRemoved.clear();
        for(Creature creature : canBeSold){
            Test.init.getCreaturePool().add(creature);
            indexToBeRemoved.add(i);
            i++;
        }
        for (Integer j : indexToBeRemoved) {
            canBeSold.remove(j);
        }
        i=0;
        indexToBeRemoved.clear();
        for(Creature creature : actuallySelling){
            Test.init.getCreaturePool().add(creature);
            indexToBeRemoved.add(i);
            i++;
        }
        for (Integer j : indexToBeRemoved) {
            actuallySelling.remove(j);
        }
    }

    private void setShop(int numberOfDraw){
        int choice;
        for(int i=0;i<numberOfDraw;i++){
            choice = (int) (Math.random() * (canBeSold.size()));
            actuallySelling.add(canBeSold.get(choice));
            canBeSold.remove(choice);
        }
    }
}
