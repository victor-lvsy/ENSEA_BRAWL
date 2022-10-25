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
                if (i.size() > 2) {
                    i.remove(0);
                    i.remove(0);
                }
            }
            else{
                fightOrder.add(players.get(i.get(0)));
                fightOrder.add(players.get(i.get(2)));
                if (i.size() > 2) {
                    i.remove(0);
                    i.remove(0);
                }
            }
        }
    }

}
