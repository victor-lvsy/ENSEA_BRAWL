
public class Game {

    public static int gameTurn=1;
    public static Initialisation init = new Initialisation();

    public static Player playerSave = new Player("Ghost",0,0);

    public static void main(String[] args) {
        Turn turn = new Turn();
        init.Initialisation();
        while(Initialisation.players.size()>1){
            turn.Turn();
            gameTurn++;
        }
    }

    public static void endOfPlayerGame(int toBeTested){
        if(Initialisation.players.get(toBeTested).getPlayerHp()<=0){
            int k=1000;
            System.out.println(Initialisation.players.get(toBeTested).getPlayerName()+" is eliminated, his hp fell to 0.");
            playerSave.getCurrentOnBoard().addAll(Initialisation.players.get(toBeTested).getOnBoard());
            Initialisation.players.remove(toBeTested);
            for(int i=0;i<Initialisation.players.size();i++){
                if(Initialisation.players.get(i).getPlayerName()=="Ghost"){
                    k=i;
                }
            }
            if (k!=1000){
                Initialisation.players.remove(k);
            }
            if(Initialisation.players.size()==3||Initialisation.players.size()==5){
                Initialisation.players.add(playerSave);
            }
        }
    }
}
