import java.util.ArrayList;

public class Game {
    public static Initialisation init = new Initialisation();

    public static ArrayList<Creature> boardSave = new ArrayList<Creature>();

    public static void Game() {
        init.Initialisation();
        Fight fight = new Fight();
        fight.drawFights(Initialisation.players);
        for(int i=0;i<3;i++){
            System.out.println("===============================NEXT FIGHT===============================");
            fight.vsFight(fight.getFightOrder().get(2*i),fight.getFightOrder().get(2*i+1));
        }
        System.out.println("==========================END OF FIGHTING PHASE=========================");
    }

    public void endOfPlayerGame(int toBeTested){
        if(Initialisation.players.get(toBeTested).getPlayerHp()<=0){
            boardSave.clear();
            System.out.println(Initialisation.players.get(toBeTested).getPlayerName()+"is eliminated, his hp fell to 0.");
            boardSave.addAll(Initialisation.players.get(toBeTested).getOnBoard());
            Initialisation.players.remove(toBeTested);
        }
    }
}
