public class Turn extends Game{

    public static void Turn(){
        shopInit();
        Fight fight = new Fight();
        fight.drawFights(Initialisation.players);
        for(int i=0;i<Initialisation.players.size()/2;i++){
            System.out.println("===============================NEXT FIGHT===============================");
            fight.vsFight(fight.getFightOrder().get(2*i),fight.getFightOrder().get(2*i+1));
        }
        System.out.println("==========================END OF FIGHTING PHASE=========================");
        for(int i=0;i<Initialisation.players.size();i++){
            endOfPlayerGame(i);
        }
    }

    public static void playerTestInitializer(int k){
        int j=Initialisation.players.get(k).getShop().getActuallySelling().size()-1;
        if(gameTurn==1){
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

    public static void shopInit(){
        for(int i=0;i<Initialisation.players.size();i++){
            Initialisation.players.get(i).shopLevelUp();
            Initialisation.players.get(i).testLevelUp();
            System.out.println("Shop level: "+Initialisation.players.get(i).getShopLvl()+" Shop level up cost: "+Initialisation.players.get(i).getShopLevelUpCost());
            Initialisation.players.get(i).getShop().shop(Initialisation.players.get(i).getShopLvl(),i);
        }
    }
}