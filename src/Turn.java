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

    public static void playerTestInitializer(){
        int j=5;
        if(gameTurn>1){
            for(int i = 0; i<3;i++){
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
    }

    public static void shopInit(){
        for(int i=0;i<Initialisation.players.size();i++){
            Initialisation.players.get(i).setShopLevelUpCost(Initialisation.players.get(i).getShopLevelUpCost()-1);
            Initialisation.players.get(i).testLevelUp();
            System.out.println("Shop level: "+Initialisation.players.get(i).getShopLvl()+" Shop level up cost: "+Initialisation.players.get(i).getShopLevelUpCost());
            Initialisation.players.get(i).getShop().shop(Initialisation.players.get(i).getShopLvl());
        }
    }
}
