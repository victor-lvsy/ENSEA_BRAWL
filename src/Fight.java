import java.util.ArrayList;
import java.util.Collections;

public class Fight {

    private ArrayList<Player> fightOrder = new ArrayList<Player>();
    private class Rivaux {
        Creature creature;
        int playerParent;

        public void setCreature(Creature creature) {
            this.creature = creature;
        }

        public void setPlayerParent(int playerParent) {
            this.playerParent = playerParent;
        }
    }

    public ArrayList<Player> getFightOrder() {
        return fightOrder;
    }

    public void drawFights(ArrayList<Player> players) {
        ArrayList<Integer> i = new ArrayList<Integer>();
        extractOrder(players, i);
        switch (fightOrder.size()) {
            case 6:
                while (fightOrder.get(0).getPrecedentOpponent() == fightOrder.get(1) ||
                        fightOrder.get(2).getPrecedentOpponent() == fightOrder.get(3) ||
                        fightOrder.get(4).getPrecedentOpponent() == fightOrder.get(5)) {
                    extractOrder(players, i);
                }
                break;
            case 4:
                while (fightOrder.get(0).getPrecedentOpponent() == fightOrder.get(1) ||
                        fightOrder.get(2).getPrecedentOpponent() == fightOrder.get(3)) {
                    extractOrder(players, i);
                }
                break;
            case 2:
                while (fightOrder.get(0).getPrecedentOpponent() == fightOrder.get(1)) {
                    extractOrder(players, i);
                }
                break;
            default:
                System.out.println("ERROR, cannot extract fight Order");
        }

    }

    private void extractOrder(ArrayList<Player> players, ArrayList<Integer> i) {
        int k;
        for (int j = 0; j < players.size(); j++) {
            i.add(j);
        }
        k = i.size();
        Collections.shuffle(i);
        for (int j = 0; j < k / 2; j++) {
            fightOrder.add(players.get(i.get(0)));
            fightOrder.add(players.get(i.get(1)));
            i.remove(0);
            i.remove(0);
        }
    }

    public void vsFight(Player player1, Player player2) {
        twoPlayerInitialisation(player1, player2);
        int rand = (int) Math.floor(Math.random() * 2), i = 0, j = 0;
        boolean continueFight = true;
        if (rand == 0) {
            while (continueFight == true) {
                continueFight = oneTurnFight(player1, player2, i);
                i++;
            }
        } else {
            while (continueFight == true) {
                continueFight = oneTurnFight(player2, player1, j);
                j++;
            }
        }
    }

    public boolean oneTurnFight(Player first, Player second, int fightTurn) {
        int i = 0;
        if (first.getCurrentOnBoard().size() != 0 && second.getCurrentOnBoard().size() != 0 && dmgDealerOnBoard(first, second) == true) {
            while (first.getCurrentOnBoard().get(i).getAlreadyFight() > fightTurn) {
                i++;
            }
            first.attackTurn(second, i);
        } else {
            haveLost(first, second);
            return false;
        }
        if (first.getCurrentOnBoard().size() != 0 && second.getCurrentOnBoard().size() != 0 && dmgDealerOnBoard(first, second) == true) {
            while (second.getCurrentOnBoard().get(i).getAlreadyFight() > fightTurn) {
                i++;
            }
            second.attackTurn(first, i);
        } else {
            haveLost(first, second);
            return false;
        }
        return true;
    }

    public void fightingBoardInitializer(Player toBeInitialized) {
        int i, j=toBeInitialized.getExil().size()-1;
        toBeInitialized.getCurrentOnBoard().clear();
        if(j>0){
            for (i = 0;i<toBeInitialized.getExil().size();i++){
                if(toBeInitialized.getExil().get(j).exilTime==1){
                    toBeInitialized.getExil().get(j).creature.getToBeGeneratedDiploma().setSpellAttBoost(toBeInitialized.getExil().get(j).creature.getToBeGeneratedDiploma().getSpellAttBoost()+5);
                    toBeInitialized.getExil().get(j).creature.getToBeGeneratedDiploma().setSpellHpBoost(toBeInitialized.getExil().get(j).creature.getToBeGeneratedDiploma().getSpellHpBoost()+5);
                    toBeInitialized.getOnBoard().add(toBeInitialized.getExil().get(j).creature);
                    toBeInitialized.getExil().remove(j);
                }
                else{
                    toBeInitialized.getExil().get(j).exilTime=toBeInitialized.getExil().get(j).exilTime+1;
                }
                j--;
            }
        }
        for (i=0;i<toBeInitialized.getOnBoard().size();i++) {
            Creature toBeAdded = new Creature();
            toBeAdded.copyCreature(toBeInitialized.getOnBoard().get(i));
            toBeAdded.setIndexOfBoard(i);
            toBeInitialized.getCurrentOnBoard().add(toBeAdded);
        }
        for (Creature creature : toBeInitialized.getCurrentOnBoard()) { //ajouter l'archetype BDLS pour panier bio*2
            creature.setAlreadyFight(0);
            creature.setCreatureHp(creature.getCreatureHp()+creature.getCurrentDiploma().getSpellHpBoost()+creature.getPaniersBio());
            creature.setCreatureAtt(creature.getCreatureAtt()+creature.getCurrentDiploma().getSpellAttBoost()+creature.getPaniersBio());
            for(i = 0; i<48;i++){
                if(creature.getCurrentDiploma().getThisSpellEffect(i)==true){
                    creature.getEffectList()[i]=true;
                }
            }
        }
    }

    public void twoPlayerInitialisation(Player player1, Player player2) {
        fightingBoardInitializer(player1);
        fightingBoardInitializer(player2);
        initPlayerBeginningOfTurn(player2, player1);
        initPlayerBeginningOfTurn(player1, player2);

    }

    private void initPlayerBeginningOfTurn(Player player2, Player player1) {
        for (Creature creature : player1.getCurrentOnBoard()) {
            for (int i = 1; i < 47; i++) {
                if (creature.getEffectList()[i] == true){
                    switch (i) {
                        case 1:
                            creature.volDAssos(player2);
                            break;
                        case 2:
                            creature.ardoiseKfet(player2);
                            break;
                        case 3:
                            creature.annulationDactivite(player2);
                            break;
                        case 4:
                            creature.jeSuisAleatoArt();
                            break;
                        case 5:
                            creature.nousSommesAleatoART(player1);
                            break;
                        case 6:
                            jaiBesoinDePlusDheures(player1, player2, creature);
                            break;
                        case 13:
                            creature.armureEnPcbRecycle(player1);
                            break;
                        case 14:
                            player1.boostAleatoART();
                        case 17:
                            player1.cestQuoiUneListe();
                            break;
                        case 37:
                            shuffleBoard(player2);
                            break;
                        case 39:
                            creature.karsherisationDesFaibles(player2);
                            break;
                        case 40:
                            creature.sippingDeSubventions(player2, player1);
                            break;
                        case 42:
                            creature.deviensAleatoART();
                            break;
                        default:
                            break;
                    }
                }

            }
        }
        tauntEtRivalite(player1, player2);
    }

    public void haveLost(Player player1, Player player2) {
        if (player1.getCurrentOnBoard().size() == 0) {
            System.out.println(player1.getPlayerName() + " doesn't have anymore creatures on board.");
            for (Creature creature : player2.getCurrentOnBoard()) {
                player1.setPlayerHp(player1.getPlayerHp() - creature.getCreatureTier());
            }
            System.out.println(player1.getPlayerName() + " HP is at " + player1.getPlayerHp() + " and " + player2.getPlayerName() + " HP is at " + player2.getPlayerHp());
        }
        if (player2.getCurrentOnBoard().size() == 0) {
            System.out.println(player2.getPlayerName() + " doesn't have anymore creatures on board.");
            for (Creature creature : player1.getCurrentOnBoard()) {
                player2.setPlayerHp(player2.getPlayerHp() - creature.getCreatureTier());
            }
            System.out.println(player1.getPlayerName() + " HP is at " + player1.getPlayerHp() + " and " + player2.getPlayerName() + " HP is at " + player2.getPlayerHp());
        }
        if (player1.getCurrentOnBoard().size() != 0 && player2.getCurrentOnBoard().size() != 0) {
            System.out.println("Fight cannot continue further because remaining creatures doesn't have anymore damages");
        }
    }

    public boolean dmgDealerOnBoard(Player player1, Player player2) {
        int i = 0;
        for (Creature creature : player1.getCurrentOnBoard()) {
            if (creature.getCreatureAtt() == 0) {
                i++;
            }
        }
        for (Creature creature : player2.getCurrentOnBoard()) {
            if (creature.getCreatureAtt() == 0) {
                i++;
            }
        }
        if (i == player1.getCurrentOnBoard().size() + player2.getCurrentOnBoard().size()) {
            return false;
        }
        return true;
    }

    public void shuffleBoard(Player player) {
        Collections.shuffle(player.getCurrentOnBoard());
    }

    public void jaiBesoinDePlusDheures(Player possessor, Player receiver, Creature mana) {
        int manaIndex = possessor.getCurrentOnBoard().indexOf(mana);
        Creature crea;
        int alea = (int) Math.floor(Math.random() * receiver.getCurrentOnBoard().size());
        crea = receiver.getCurrentOnBoard().get(alea);
        receiver.getCurrentOnBoard().remove(alea);
        receiver.getCurrentOnBoard().add(alea, mana);
        possessor.getCurrentOnBoard().remove(manaIndex);
        possessor.getCurrentOnBoard().add(manaIndex, crea);
    }

    public void tauntEtRivalite(Player player1, Player player2) {
        int isDead[];
        ArrayList<Rivaux> rivaux = new ArrayList<>();
        Rivaux rival = new Rivaux();
        for (Creature creature : player1.getCurrentOnBoard()) {
            if (creature.getEffectList()[36] == true) {
                rival.creature = creature;
                rival.playerParent = 1;
                rivaux.add(rival);
            }
        }
        for (Creature creature : player2.getCurrentOnBoard()) {
            if (creature.getEffectList()[36] == true) {
                rival.creature = creature;
                rival.playerParent = 2;
                rivaux.add(rival);
            }
        }
        while (rivaux.size() > 1) {
            int rand = (int) Math.floor(Math.random() * rivaux.size()), rand2 = rand + 1;
            if (rand2 >= rivaux.size()) {
                rand2 = 0;
            }
            System.out.println("______________________________TENSION INIT______________________________");
            isDead = rivaux.get(rand).creature.attackCreature(rivaux.get(rand2).creature);
            if (isDead[1] == 1) {
                switch (rivaux.get(rand).playerParent) {
                    case 1:
                        System.out.println(rivaux.get(rand).creature.getCardName() + " has been removed from " + player1.getPlayerName() + " currentBoard");
                        player1.getCurrentOnBoard().remove(rivaux.get(rand).creature);
                        rivaux.remove(rand);
                        break;
                    case 2:
                        System.out.println(rivaux.get(rand).creature.getCardName() + " has been removed from " + player2.getPlayerName() + " currentBoard");
                        player2.getCurrentOnBoard().remove(rivaux.get(rand).creature);
                        rivaux.remove(rand);
                        break;
                }
            }
            else {
                rivaux.remove(rand);
            }
            if (isDead[2] == 1) {
                if (rand2 >= rivaux.size()) {
                    rand2--;
                }
                switch (rivaux.get(rand2).playerParent) {
                    case 1:
                        System.out.println(rivaux.get(rand2).creature.getCardName() + " has been removed from " + player1.getPlayerName() + " currentBoard");
                        player1.getCurrentOnBoard().remove(rivaux.get(rand2).creature);
                        rivaux.remove(rand2);
                        break;
                    case 2:
                        System.out.println(rivaux.get(rand2).creature.getCardName() + " has been removed from " + player2.getPlayerName() + " currentBoard");
                        player2.getCurrentOnBoard().remove(rivaux.get(rand2).creature);
                        rivaux.remove(rand2);
                        break;
                }
            }
            else {
                if (rand2 >= rivaux.size()) {
                    rand2--;
                }
                rivaux.remove(rand2);
            }
            System.out.println("_____________________________TENSION INIT END_____________________________");
        }
    }
}