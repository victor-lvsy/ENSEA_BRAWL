import java.util.ArrayList;
import java.util.Collections;

public class Fight {
    private ArrayList<Player> fightOrder = new ArrayList<Player>();

    public void drawFights(ArrayList<Player> players){
        ArrayList<Integer> i = new ArrayList<Integer>();
        for (int j=0 ; j<players.size() ; j++){
            i.add(j);
        }
        Collections.shuffle(i);
        for(int j=0 ; j<3 ; j++){
            if(players.get(i.get(0))!=players.get(i.get(1)).getPrecedentOpponent()){
                fightOrder.add(players.get(i.get(0)));
                fightOrder.add(players.get(i.get(1)));
                i.remove(0);
                i.remove(0);
            }

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
        ArrayList <Creature> creatures = (ArrayList<Creature>) toBeInitialized.getOnBoard().clone();
        toBeInitialized.getCurrentOnBoard().addAll(creatures);
        for (Creature creature : toBeInitialized.getOnBoard()){
            creature.setAlreadyFight(0);
        }
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
}
