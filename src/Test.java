import java.util.ArrayList;

public class Test {
    public static Initialisation init = new Initialisation();
    public static void main(String[] args) {

    init.Initialisation();
    Initialisation.player1.getOnBoard().add(init.getCreaturePool().get(15));
    Initialisation.player1.getOnBoard().add(init.getCreaturePool().get(163));
    Initialisation.player1.getOnBoard().add(init.getCreaturePool().get(422));
    Initialisation.player1.getOnBoard().add(init.getCreaturePool().get(600));
    Initialisation.player1.getOnBoard().add(init.getCreaturePool().get(394));
    Initialisation.player1.getOnBoard().add(init.getCreaturePool().get(500));
    Initialisation.player1.getOnBoard().add(init.getCreaturePool().get(212));

    Initialisation.player2.getOnBoard().add(init.getCreaturePool().get(23));
    Initialisation.player2.getOnBoard().add(init.getCreaturePool().get(290));
    Initialisation.player2.getOnBoard().add(init.getCreaturePool().get(600));
    Initialisation.player2.getOnBoard().add(init.getCreaturePool().get(610));
    Initialisation.player2.getOnBoard().add(init.getCreaturePool().get(350));
    Initialisation.player2.getOnBoard().add(init.getCreaturePool().get(434));
    Initialisation.player2.getOnBoard().add(init.getCreaturePool().get(111));
    }
}
