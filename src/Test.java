import java.util.ArrayList;

public class Test {
    public static Initialisation init = new Initialisation();
    public static void main(String[] args) {
    init.Initialisation();
    playerTestInitialliser();
    Fight fight = new Fight();
    fight.drawFights(Initialisation.players);
    fight.vsFight(Initialisation.players.get(0),Initialisation.players.get(1));
    }

    public static void playerTestInitialliser(){
        for(int i =0; i<6;i++){
            Initialisation.players.get(0).getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
            Initialisation.players.get(1).getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
            Initialisation.players.get(2).getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
            Initialisation.players.get(3).getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
            Initialisation.players.get(4).getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
            Initialisation.players.get(5).getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
        }
    }
}
