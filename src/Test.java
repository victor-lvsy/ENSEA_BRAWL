import java.util.ArrayList;

public class Test {
    public static Initialisation init = new Initialisation();
    public static void main(String[] args) {
    init.Initialisation();
    playerTestInitialliser();
    Fight fight = new Fight();
    fight.drawFights(Initialisation.players);
    for(int i=0;i<3;i++){
        fight.vsFight(fight.getFightOrder().get(2*i),fight.getFightOrder().get(2*i+1));
        System.out.println("===============================NEXT FIGHT===============================");
        }
    }

    public static void playerTestInitialliser(){
        for(int i =0; i<6;i++){
            Initialisation.players.get(0).getOnBoard().add(init.getCreaturePool().get(223+i));
            Initialisation.players.get(1).getOnBoard().add(init.getCreaturePool().get(333+i));
            Initialisation.players.get(2).getOnBoard().add(init.getCreaturePool().get(444+i));
            Initialisation.players.get(3).getOnBoard().add(init.getCreaturePool().get(555+i));
            Initialisation.players.get(4).getOnBoard().add(init.getCreaturePool().get(600+i));
            Initialisation.players.get(5).getOnBoard().add(init.getCreaturePool().get(111+i));
        }
    }
}
