import java.util.ArrayList;

public class Test {
    public static Initialisation init = new Initialisation();
    public static void main(String[] args) {
    init.Initialisation();
    playerTestInitialliser();
    Fight fight = new Fight();
    fight.drawFights(Initialisation.players);
        for(int i=0;i<3;i++){
            System.out.println("===============================NEXT FIGHT===============================");
            fight.vsFight(fight.getFightOrder().get(2*i),fight.getFightOrder().get(2*i+1));
        }
        System.out.println("==========================END OF FIGHTING PHASE=========================");
    }

    public static void playerTestInitialliser(){
        int j=5;
        for(int i=0;i<6;i++){
            Initialisation.players.get(i).getShop().Shop();
        }
        for(int i = 0; i<6;i++){
            Initialisation.players.get(0).buyCreature(j);
            Initialisation.players.get(1).buyCreature(j);
            Initialisation.players.get(2).buyCreature(j);
            Initialisation.players.get(3).buyCreature(j);
            Initialisation.players.get(4).buyCreature(j);
            Initialisation.players.get(5).buyCreature(j);
            j--;
        }
        for(int i = 0; i<6;i++){
            Initialisation.players.get(0).handToBoard(0);
            Initialisation.players.get(1).handToBoard(0);
            Initialisation.players.get(2).handToBoard(0);
            Initialisation.players.get(3).handToBoard(0);
            Initialisation.players.get(4).handToBoard(0);
            Initialisation.players.get(5).handToBoard(0);
            j--;
        }
    }
}
