public class Turn{
    private Boolean shopPhase = true;
    static Fight fight = new Fight();
    private Player enemy;

    public static void Turn(){

        fight.drawFights(Initialisation.players);
        for(int i=0;i<Initialisation.players.size()/2;i++){
            System.out.println("===============================NEXT FIGHT===============================");
            fight.vsFight(fight.getFightOrder().get(2*i),fight.getFightOrder().get(2*i+1));
        }
        System.out.println("==========================END OF FIGHTING PHASE=========================");
        for(int i=0;i<Initialisation.players.size();i++){
            Game.endOfPlayerGame(i);
        }
    }

    public static void playerTestInitializer(int k){
        int j=Initialisation.players.get(k).getShop().getActuallySelling().size()-1;
        if(Game.gameTurn==1){
            for(int i = 0; i<3;i++){
                Initialisation.players.get(k).buyCreature(j);
                j--;
            }
            j=Initialisation.players.get(k).getHand().size();
            for(int i = 0; i<j;i++){
                Initialisation.players.get(k).handToBoard(0);
            }
        }
    }



    public Player player1Enemy(){
        return fight.searchPlayer1Enemy();
    }
}
