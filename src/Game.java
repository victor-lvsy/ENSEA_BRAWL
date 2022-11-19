import javax.swing.*;
import java.util.ArrayList;

public class Game {

    public static int gameTurn=1;
    public static int boardSize=7;
    public static Initialisation init = new Initialisation();
    public static Player playerSave = new Player("Ghost",999999,0);

    public static void main(String[] args) {
        Turn turn = new Turn();
        init.Initialisation();
        while(Initialisation.players.size()>1 && gameTurn<50){
            turn.Turn();
            gameTurn++;
        }
        System.out.println("Player "+Initialisation.players.get(0).getPlayerName()+" has won!");
    }

    public static void endOfPlayerGame(int toBeTested){
        if(Initialisation.players.get(toBeTested).getPlayerHp()<=0 && Initialisation.players.get(toBeTested).getPlayerName().compareTo("Ghost")!=0){
            int k=1000;
            System.out.println(Initialisation.players.get(toBeTested).getPlayerName()+" is eliminated, his hp fell to 0.");
            playerSave.getOnBoard().addAll(Initialisation.players.get(toBeTested).getOnBoard());
            int j, l=Initialisation.players.get(toBeTested).getOnBoard().size(), m=Initialisation.players.get(toBeTested).getHand().size();
            for(j=0;j<l;j++){
                Initialisation.players.get(toBeTested).sellCreature(0);
            }
            for(j=0;j<m;j++){
                if (Initialisation.players.get(toBeTested).getHand().get(0) instanceof Creature){
                    Creature creature = new Creature();
                    creature.setCreature(Initialisation.players.get(toBeTested).getOnBoard().get(0).getCardName(),"doc/effectListCSV_epure.csv");
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
}
