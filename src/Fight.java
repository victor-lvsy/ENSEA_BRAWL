import java.util.ArrayList;
import java.util.Collections;

public class Fight {
    private ArrayList<Player> fightOrder = new ArrayList<Player>();

    public void drawFights(ArrayList<Player> players){
        ArrayList<Integer> i = new ArrayList<Integer>();
        extractOrder(players, i);
        switch (fightOrder.size()){
            case 6:
            while (fightOrder.get(0).getPrecedentOpponent()==fightOrder.get(1)||
                        fightOrder.get(2).getPrecedentOpponent()==fightOrder.get(3)||
                            fightOrder.get(4).getPrecedentOpponent()==fightOrder.get(5)){
                extractOrder(players, i);
            }
            break;
            case 4:
                while (fightOrder.get(0).getPrecedentOpponent()==fightOrder.get(1)||
                        fightOrder.get(2).getPrecedentOpponent()==fightOrder.get(3)){
                    extractOrder(players, i);
                }
                break;
            case 2:
                while (fightOrder.get(0).getPrecedentOpponent()==fightOrder.get(1)){
                    extractOrder(players, i);
                }
                break;
            default:
                System.out.println("ERROR, cannot extract fight Order");
        }

    }

    private void extractOrder(ArrayList<Player> players, ArrayList<Integer> i) {
        int k;
        for (int j=0 ; j<players.size() ; j++){
            i.add(j);
        }
        k= i.size();
        Collections.shuffle(i);
        for(int j=0 ; j<k/2 ; j++){
            fightOrder.add(players.get(i.get(0)));
            fightOrder.add(players.get(i.get(1)));
            i.remove(0);
            i.remove(0);
        }
    }

    public void vsFight(Player player1, Player player2){
        fightingBoardInitializer(player1);fightingBoardInitializer(player2);
        int rand = (int) Math.floor(Math.random() * 2),i=0;
        boolean continueFight=true;
        if(rand==0){
            while(continueFight==true){
                continueFight=oneTurnFight(player1,player2,i);
                i++;
            }
        }
        else{
            while(continueFight==true){
                continueFight=oneTurnFight(player2,player1,i);
                i++;
            }
        }
    }

    public boolean oneTurnFight(Player first, Player second, int fightTurn){
        int i=0;
        if(first.getCurrentOnBoard().size()!=0 && second.getCurrentOnBoard().size()!=0 && dmgDealerOnBoard(first,second)==true){
            while(first.getCurrentOnBoard().get(i).getAlreadyFight()>fightTurn){
                i++;
            }
            first.attackTurn(second,i);
        }
        else{
            haveLost(first,second);
            return false;
        }
        if(first.getCurrentOnBoard().size()!=0 && second.getCurrentOnBoard().size()!=0 && dmgDealerOnBoard(first,second)==true){
                while(second.getCurrentOnBoard().get(i).getAlreadyFight()>fightTurn){
                    i++;
                }
            second.attackTurn(first,i);
        }
        else{
            haveLost(first,second);
            return false;
        }
        return true;
    }

    public void fightingBoardInitializer(Player toBeInitialized){
        toBeInitialized.getCurrentOnBoard().clear();
        for(Creature creature:toBeInitialized.getOnBoard()){
            Creature toBeAdded= new Creature();
            toBeAdded.copyCreature(creature);
            toBeInitialized.getCurrentOnBoard().add(toBeAdded);
        }
        for (Creature creature : toBeInitialized.getCurrentOnBoard()){
            creature.setAlreadyFight(0);
        }
    }

    public void twoPlayerInitialisation(Player player1, Player player2){

    }

    public ArrayList<Player> getFightOrder() {
        return fightOrder;
    }

    public void haveLost(Player player1,Player player2){
        if(player1.getCurrentOnBoard().size()==0){
            System.out.println(player1.getPlayerName() +" doesn't have anymore creatures on board.");
            for (Creature creature:player2.getCurrentOnBoard()){
                player1.setPlayerHp(player1.getPlayerHp() - creature.getCreatureTier());
            }
            System.out.println(player1.getPlayerName()+" HP is at "+player1.getPlayerHp()+" and "+player2.getPlayerName()+" HP is at "+player2.getPlayerHp());
        }
        if(player2.getCurrentOnBoard().size()==0){
            System.out.println(player2.getPlayerName() +" doesn't have anymore creatures on board.");
            for (Creature creature:player1.getCurrentOnBoard()){
                player2.setPlayerHp(player2.getPlayerHp() - creature.getCreatureTier());
            }
            System.out.println(player1.getPlayerName()+" HP is at "+player1.getPlayerHp()+" and "+player2.getPlayerName()+" HP is at "+player2.getPlayerHp());
        }
        if(player1.getCurrentOnBoard().size()!=0 && player2.getCurrentOnBoard().size()!=0){
            System.out.println("Fight cannot continue further because remaining creatures doesn't have anymore damages");
        }
    }

    public boolean dmgDealerOnBoard(Player player1,Player player2){
        int i=0;
        for (Creature creature:player1.getCurrentOnBoard()){
            if(creature.getCreatureAtt()==0){
                i++;
            }
        }
        for (Creature creature:player2.getCurrentOnBoard()){
            if(creature.getCreatureAtt()==0){
                i++;
            }
        }
        if(i==player1.getCurrentOnBoard().size()+player2.getCurrentOnBoard().size()){
            return false;
        }
        return true;
    }

    public void shuffleBoard(Player player){
        Collections.shuffle(player.getCurrentOnBoard());
    }

    public void jaiBesoinDePlusDheures (Player possessor, Player receiver, Creature mana){
        int manaIndex = possessor.getCurrentOnBoard().indexOf(mana);
        Creature crea = new Creature();
        int alea = (int) Math.floor(Math.random() * receiver.getCurrentOnBoard().size());
        crea=receiver.getCurrentOnBoard().get(alea);
        receiver.getCurrentOnBoard().remove(alea);
        receiver.getCurrentOnBoard().add(alea,mana);
        possessor.getCurrentOnBoard().remove(manaIndex);
        possessor.getCurrentOnBoard().add(manaIndex,crea);
    }
}
