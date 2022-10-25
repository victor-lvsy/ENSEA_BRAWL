import java.util.ArrayList;

public class Test {
    public static Initialisation init = new Initialisation();
    public static void main(String[] args) {

    init.Initialisation();
    playerTestInitialliser();
    }

    public static void playerTestInitialliser(){
        for(int i =0; i<6;i++){
            Initialisation.player1.getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
            Initialisation.player2.getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
            Initialisation.player3.getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
            Initialisation.player4.getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
            Initialisation.player5.getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
            Initialisation.player6.getOnBoard().add(init.getCreaturePool().get((int) (Math.random() * (init.getCreaturePool().size()))));
        }
    }
}
