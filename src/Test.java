import java.util.ArrayList;

public class Test {
    public static Initialisation init = new Initialisation();
    public static Player playerSave = new Player("Ghost",0,0);
    public static void main(String[] args) {
    init.Initialisation();
    playerTestInitialliser();
    Initialisation.players.get(0).invertCards(0,5);
    Initialisation.players.get(0).sellCreature(0,"Board");
    Fight fight = new Fight();
    fight.drawFights(Initialisation.players);
        for(int i=0;i<3;i++){
            System.out.println("===============================NEXT FIGHT===============================");
            fight.vsFight(fight.getFightOrder().get(2*i),fight.getFightOrder().get(2*i+1));
        }
        System.out.println("==========================END OF FIGHTING PHASE=========================");
        Initialisation.players.get(0).setPlayerHp(0);
        endOfPlayerGame(0);
    }

    public static void playerTestInitialliser(){
        int j=5;
        for(int i=0;i<6;i++){
            Initialisation.players.get(i).getShop().shop(5);
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
        j=Initialisation.players.get(0).getHand().size();
        for(int i = 0; i<j;i++){
            Initialisation.players.get(0).handToBoard(0);
        }
        j=Initialisation.players.get(1).getHand().size();
        for(int i = 0; i<j;i++){
            Initialisation.players.get(1).handToBoard(0);
        }
        j=Initialisation.players.get(2).getHand().size();
        for(int i = 0; i<j;i++){
            Initialisation.players.get(2).handToBoard(0);
        }
        j=Initialisation.players.get(3).getHand().size();
        for(int i = 0; i<j;i++){
            Initialisation.players.get(3).handToBoard(0);
        }
        j=Initialisation.players.get(4).getHand().size();
        for(int i = 0; i<j;i++){
            Initialisation.players.get(4).handToBoard(0);
        }
        j=Initialisation.players.get(5).getHand().size();
        for(int i = 0; i<j;i++){
            Initialisation.players.get(5).handToBoard(0);
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
