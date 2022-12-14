import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Shop {
    private int level;
    private ArrayList<Creature> actuallySelling = new ArrayList<Creature>();
    private ArrayList<Creature> canBeSold = new ArrayList<Creature>();
    public ArrayList<Creature> getCanBeSold() {
        return canBeSold;
    }
    public ArrayList<Creature> getActuallySelling() {
        return actuallySelling;
    }

    public void shop(int lvl, int player) {
        level = lvl;
        ArrayList<Integer> indexToBeRemoved = new ArrayList<Integer>();
        ArrayList<Creature> removeBuffer = new ArrayList<>();
        for (Creature creature: Initialisation.players.get(player).getOnBoard()){
            if(creature.isStudent()==true && creature.getTimeOnBoard()>=3){
                for(Creature creature1:Initialisation.players.get(player).getOnBoard()){
                    if(creature1.getEffectList()[38]==true){
                        creature.getToBeGeneratedDiploma().setSpellAttBoost(creature.getToBeGeneratedDiploma().getSpellAttBoost()+2);
                        creature.getToBeGeneratedDiploma().setSpellHpBoost(creature.getToBeGeneratedDiploma().getSpellHpBoost()+2);
                    }
                }
                creature.effectAdder();
                Initialisation.players.get(player).getHand().add(creature.generateDiploma());
                if(creature.getEffectList()[45]==true){
                    Initialisation.players.get(player).getHand().add(creature.generateDiploma());
                }
                removeBuffer.add(creature);
            }
        }
        for(Creature creature: removeBuffer){
            Creature hasBeenRemoved = new Creature();
            hasBeenRemoved.setCreature(creature.getCardName(),"doc/effectListCSV_epure.csv");
            Game.init.getCreaturePool().add(creature);
            Initialisation.players.get(player).getOnBoard().remove(creature);
        }
        int i = 0;
        for (Creature creature : Game.init.getCreaturePool()) {
            if (creature.getCreatureTier() <= level) {
                canBeSold.add(creature);
                indexToBeRemoved.add(i);
            }
            i++;
        }
        Collections.sort(indexToBeRemoved, Collections.reverseOrder());
        for (Integer j : indexToBeRemoved) {
            Game.init.getCreaturePool().remove((int) j);
        }
        switch (level) {
            case 1:
                setShop(3);
                break;
            case 2:
                setShop(4);
                break;
            case 3:
                setShop(4);
                break;
            case 4:
                setShop(5);
                break;
            case 5:
                setShop(6);
                break;
        }
        if (Initialisation.players.get(player).getPlayerName() != "zssnu") {
            Turn.playerTestInitializer(player);
        }
        Game.init.getCreaturePool().addAll(actuallySelling);
    }

    public void clearShop(){
        actuallySelling.clear();
    }

    private void setShop(int numberOfDraw) {
        int choice;
        for (int i = 0; i < numberOfDraw; i++) {
            choice = (int) (Math.random() * (canBeSold.size()));
            actuallySelling.add(canBeSold.get(choice));
            canBeSold.remove(choice);
        }
        Game.init.getCreaturePool().addAll(canBeSold);
        canBeSold.clear();
        System.out.println("==================================SHOP===================================");
        for (Creature creature : actuallySelling) {
            System.out.println(creature);
        }
        System.out.println("========================================================================");
    }

}
