import java.awt.image.TileObserver;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Player {

    private String playerName;
    private int playerHp;
    private int playerGolds;
    private Player precedentOpponent = null;
    public Shop shop = new Shop();
    private int shopLvl=5;
    private int shopLevelUpCost=5;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private ArrayList<Creature> onBoard = new ArrayList<Creature>();
    private ArrayList<Creature> currentOnBoard = new ArrayList<Creature>();
    private int archetypeList[];
    private ArrayList<Creature>deadBuffer= new ArrayList<>();
    public class Exile {
        Creature creature;
        int exilTime;

        public void setCreature(Creature creature) {
            this.creature = creature;
        }

        public void setExilTime(int exilTime) {
            this.exilTime= exilTime;
        }
    }
    private ArrayList<Exile> exil = new ArrayList<>();

    public Player(String newPlayerName, int newPlayerHp, int newPlayerGolds) {
        playerName = newPlayerName;
        playerHp = newPlayerHp;
        playerGolds = newPlayerGolds;
        archetypeList= new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    }

    public ArrayList<Creature> getOnBoard() {
        return onBoard;
    }
    public ArrayList<Creature> getCurrentOnBoard() {
        return currentOnBoard;
    }
    public void setCurrentOnBoard() {
        this.currentOnBoard = currentOnBoard;
    }
    public ArrayList<Exile> getExil() {
        return exil;
    }
    public Shop getShop() {
        return shop;
    }
    public int[] getArchetypeList() {
        return archetypeList;
    }
    public void setArchetypeList(int[] archetypeList) {
        this.archetypeList = archetypeList;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
    public void setOnBoard(ArrayList<Creature> onBoard) {
        this.onBoard = onBoard;
    }
    public Player getPrecedentOpponent() {
        return precedentOpponent;
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getPlayerHp() {
        return playerHp;
    }
    public void setPlayerHp(int playerHp) {
        this.playerHp = playerHp;
    }
    public int getPlayerGolds() {
        return playerGolds;
    }
    public int getShopLvl() {
        return shopLvl;
    }
    public void setPlayerGolds(int golds) {
        playerGolds = golds;
    }
    public void setShopLvl(int shopLvl) {
        this.shopLvl = shopLvl;
    }
    public void shopFreeLevelUp(){
        if(shopLvl<5){
            shopLevelUpCost--;
            testLevelUp();
        }
        else {
            System.out.println("Last shop level reached");
        }
    }
    public int getShopLevelUpCost() {
        return shopLevelUpCost;
    }
    public void setShopLevelUpCost(int shopLevelUpCost) {
        this.shopLevelUpCost = shopLevelUpCost;
    }

    public void attackTurn(Player toBeFight, int willFight){
        int isDead[];
        ArrayList <Integer> haveTaunt = new ArrayList<Integer>();
        Creature creatureBuffer = new Creature();
        creatureBuffer = this.currentOnBoard.get(willFight);
        int alea,i=0;
        if(creatureBuffer.getEffectList()[46]==true){
            if(creatureBuffer.getCreatureHp()<=0){
                System.out.println("This creature "+creatureBuffer.getCardName() +" is dead impossible to attack");
            }
            else{
                for (Creature creature : toBeFight.getCurrentOnBoard()){
                    if(creature.getEffectList()[35]==true || creature.getEffectList()[36]==true){
                        haveTaunt.add(i);
                    }
                    i++;
                }
                if (haveTaunt.size()!=0 && this.currentOnBoard.get(willFight).getEffectList()[43]==false){
                    i=1;
                    alea=(int) (Math.random() * (haveTaunt.size()));
                    while(toBeFight.getCurrentOnBoard().get(haveTaunt.get(alea)).getEffectList()[8]==true){
                        alea=(int) (Math.random() * (haveTaunt.size()));
                        i++;
                        if(i==haveTaunt.size()){
                            toBeFight.getCurrentOnBoard().get(haveTaunt.get(alea)).setEffectList(false,8);
                            System.out.println("Camouflage is set to false because of impossibility to attack");
                        }
                    }
                    alea=haveTaunt.get(alea);
                    System.out.println( this.getPlayerName() +" "+ this.getCurrentOnBoard().get(willFight).getCardName() + " attacked " +toBeFight.getPlayerName()+" "+toBeFight.currentOnBoard.get(alea));
                    if(creatureBuffer.getEffectList()[9]==true){
                        isDead = creatureBuffer.cleave(toBeFight.getCurrentOnBoard().get(alea),toBeFight);
                    }
                    else {
                        isDead = creatureBuffer.attackCreatureWithPlayerParent(toBeFight.getCurrentOnBoard().get(alea),this);
                    }
                }
                else{
                    int k=1;
                    alea=(int) (Math.random() * (toBeFight.getCurrentOnBoard().size()));
                    while(toBeFight.getCurrentOnBoard().get(alea).getEffectList()[8]==true){
                        alea=(int) (Math.random() * (toBeFight.getCurrentOnBoard().size()));
                        if(k==toBeFight.getCurrentOnBoard().size()){
                            toBeFight.getCurrentOnBoard().get(alea).setEffectList(false,8);
                            System.out.println("Camouflage is set to false because of impossibility to attack");
                        }
                        k++;
                    }
                    System.out.println( this.getPlayerName() +" "+ this.getCurrentOnBoard().get(willFight).getCardName() + " attacked " +toBeFight.getPlayerName()+" "+toBeFight.currentOnBoard.get(alea));
                    if(creatureBuffer.getEffectList()[9]==true){
                        isDead = creatureBuffer.cleave(toBeFight.getCurrentOnBoard().get(alea),toBeFight);
                    }
                    else {
                        isDead = creatureBuffer.attackCreatureWithPlayerParent(toBeFight.getCurrentOnBoard().get(alea),this);
                    }
                }
                this.deadBuffer.clear();
                toBeFight.deadBuffer.clear();
                if (isDead[1]==1){
                    if(creatureBuffer.isReincarnation()==false){
                        System.out.println(creatureBuffer + " has been removed from "+this.playerName +" currentBoard");
                        this.deadBuffer.add(creatureBuffer);
                        this.currentOnBoard.remove(creatureBuffer);
                    }
                }
                if (isDead[4]==1){
                    System.out.println(toBeFight.currentOnBoard.get(alea+1) + " has been removed from "+toBeFight.playerName +" currentBoard");
                    toBeFight.deadBuffer.add(toBeFight.currentOnBoard.get(alea+1));
                    toBeFight.currentOnBoard.remove(alea+1);
                }
                if (isDead[2]==1){
                    System.out.println(toBeFight.currentOnBoard.get(alea) + " has been removed from "+toBeFight.playerName +" currentBoard");
                    toBeFight.deadBuffer.add(toBeFight.currentOnBoard.get(alea));
                    toBeFight.currentOnBoard.remove(alea);
                }
                if (isDead[3]==1){
                    System.out.println(toBeFight.currentOnBoard.get(alea-1) + " has been removed from "+toBeFight.playerName +" currentBoard");
                    toBeFight.deadBuffer.add(toBeFight.currentOnBoard.get(alea-1));
                    toBeFight.currentOnBoard.remove(alea-1);
                }
                uponDeathEffects(toBeFight, this);
                uponDeathEffects(this, toBeFight);

                System.out.println("_____________________________ROUND FINISHED_____________________________");
            }
        }
        haveTaunt.clear();
        i=0;
        if(toBeFight.getCurrentOnBoard().size()!=0) {
            if (this.deadBuffer.size() < 0) {
                System.out.println("This creature already is dead impossible to attack");
            } else {
                for (Creature creature : toBeFight.getCurrentOnBoard()) {
                    if (creature.getEffectList()[35] == true || creature.getEffectList()[36] == true) {
                        haveTaunt.add(i);
                    }
                    i++;
                }
                if (haveTaunt.size() != 0) {
                    i = 1;
                    alea = (int) (Math.random() * (haveTaunt.size()));
                    while (toBeFight.getCurrentOnBoard().get(haveTaunt.get(alea)).getEffectList()[8] == true) {
                        alea = (int) (Math.random() * (haveTaunt.size()));
                        i++;
                        if (i == haveTaunt.size()) {
                            toBeFight.getCurrentOnBoard().get(haveTaunt.get(alea)).setEffectList(false, 8);
                            System.out.println("Camouflage is set to false because of impossibility to attack");
                        }
                    }
                    alea = haveTaunt.get(alea);
                    System.out.println(this.getPlayerName() + " " + creatureBuffer.getCardName() + " attacked " + toBeFight.getPlayerName() + " " + toBeFight.currentOnBoard.get(alea));
                    if (creatureBuffer.getEffectList()[9] == true) {
                        isDead = creatureBuffer.cleave(toBeFight.getCurrentOnBoard().get(alea), toBeFight);
                    } else {
                        isDead = creatureBuffer.attackCreatureWithPlayerParent(toBeFight.getCurrentOnBoard().get(alea),this);
                    }
                } else {
                    int k = 1;
                    alea = (int) (Math.random() * (toBeFight.getCurrentOnBoard().size()));
                    while (toBeFight.getCurrentOnBoard().get(alea).getEffectList()[8] == true) {
                        alea = (int) (Math.random() * (toBeFight.getCurrentOnBoard().size()));
                        if (k == toBeFight.getCurrentOnBoard().size()) {
                            toBeFight.getCurrentOnBoard().get(alea).setEffectList(false, 8);
                            System.out.println("Camouflage is set to false because of impossibility to attack");
                        }
                        k++;
                    }
                    System.out.println(this.getPlayerName() + " " + creatureBuffer.getCardName() + " attacked " + toBeFight.getPlayerName() + " " + toBeFight.currentOnBoard.get(alea));
                    if (creatureBuffer.getEffectList()[9] == true) {
                            isDead = creatureBuffer.cleave(toBeFight.getCurrentOnBoard().get(alea), toBeFight);
                        }
                    else {
                            isDead = creatureBuffer.attackCreatureWithPlayerParent(toBeFight.getCurrentOnBoard().get(alea),this);
                        }
                    }
                    this.deadBuffer.clear();
                    toBeFight.deadBuffer.clear();
                    if (isDead[1] == 1) {
                        if (creatureBuffer.isReincarnation() == false) {
                            System.out.println(creatureBuffer + " has been removed from " + this.playerName + " currentBoard");
                            this.deadBuffer.add(creatureBuffer);
                            this.currentOnBoard.remove(creatureBuffer);
                        }
                    }
                     if (isDead[4] == 1) {
                        System.out.println(toBeFight.currentOnBoard.get(alea + 1) + " has been removed from " + toBeFight.playerName + " currentBoard");
                        toBeFight.deadBuffer.add(toBeFight.currentOnBoard.get(alea + 1));
                        toBeFight.currentOnBoard.remove(alea + 1);
                    }
                    if (isDead[2] == 1) {
                        System.out.println(toBeFight.currentOnBoard.get(alea) + " has been removed from " + toBeFight.playerName + " currentBoard");
                        toBeFight.deadBuffer.add(toBeFight.currentOnBoard.get(alea));
                        toBeFight.currentOnBoard.remove(alea);
                    }
                   if (isDead[3] == 1) {
                        System.out.println(toBeFight.currentOnBoard.get(alea - 1) + " has been removed from " + toBeFight.playerName + " currentBoard");
                        toBeFight.deadBuffer.add(toBeFight.currentOnBoard.get(alea - 1));
                        toBeFight.currentOnBoard.remove(alea - 1);
                    }
                    uponDeathEffects(toBeFight, this);
                    uponDeathEffects(this, toBeFight);

                    System.out.println("_____________________________ROUND FINISHED_____________________________");
                }
            }
        }

    private void uponDeathEffects(Player x, Player toBeFight) {
        for(Creature creature: x.deadBuffer){
            for (int k = 1; k < 47; k++) {
                if (creature.getEffectList()[k] == true){
                    switch (k) {
                        case 15:
                            x.vousConnaisezLyfPay();
                            break;
                        case 25:
                            x.delation(toBeFight);
                            break;
                        case 29:
                            x.propagande();
                            break;
                        case 30:
                            x.laSecuriteAvantTout();
                            break;
                        case 32:
                            x.legendNeverDie();
                            break;
                        case 33:
                            x.repopAleatoArt();
                            break;
                    }
                }
            }
        }
    }

    public void buyCreature(int indexOfCreature){
        if(shop.getActuallySelling().get(indexOfCreature).getEffectList()[10]==false){
            if(this.getPlayerGolds()>=3){
                this.hand.add(shop.getActuallySelling().get(indexOfCreature));
                this.setPlayerGolds(this.getPlayerGolds()-3);
                System.out.println("Creature: "+shop.getActuallySelling().get(indexOfCreature).getCardName()+" has been bought");
                shop.getActuallySelling().remove(indexOfCreature);
            }
            else{
                System.out.println("Not enough golds to buy this creature");
            }
        }
        else{
            this.hand.add(shop.getActuallySelling().get(indexOfCreature));
            System.out.println("Creature: "+shop.getActuallySelling().get(indexOfCreature).getCardName()+" has been bought");
            shop.getActuallySelling().remove(indexOfCreature);
        }
    }

    public void sellCreature(int indexOfCreature){
            Creature creature = new Creature();
            creature.setCreature(this.onBoard.get(indexOfCreature).getCardName(),"doc/effectListCSV_epure.csv");
            Game.init.getCreaturePool().add(creature);
            System.out.println("Creature: "+this.onBoard.get(indexOfCreature).getCardName()+" has been sold");
            if(this.onBoard.get(indexOfCreature).getCardName().equals("Absenteiste")){
                this.corruptionDeJuryDePoursuiteDetudes();
            }
            else {
                this.setPlayerGolds(this.getPlayerGolds()+1);
            }
            this.onBoard.remove(indexOfCreature);
    }

    public void handToBoard(int indexOfHand){
        if(this.hand.get(indexOfHand) instanceof Creature){
            if(this.onBoard.size()<Game.boardSize){
                this.onBoard.add((Creature) this.hand.get(indexOfHand));
                this.onBoard.get(this.onBoard.size()-1).setIndexOfBoard(this.onBoard.size()-1);
                for (int i = 1; i < 47; i++) {
                    if (((Creature) this.hand.get(indexOfHand)).getEffectList()[i] == true) {
                        switch (i) {
                            case 11:
                                //cursusEtranger();
                                break;
                            case 14:
                                this.boostAleatoART();
                                break;
                            case 16:
                                this.givePanierBio(3);
                                break;
                            case 18:
                                this.gainsAleatoArt();
                                break;
                            case 27:
                                this.favoritisme();
                                break;
                            case 28:
                                this.givePanierBio(1);
                                break;
                        }
                    }
                }
                this.hand.remove(indexOfHand);
            }
        }
        else {
            System.out.println("This card "+ this.hand.get(indexOfHand).getCardName() +" is not a creature");//à modifier pour poser spells et items
        }
    }

    public void boardToHand(int indexOfHand){
            this.hand.add( this.getOnBoard().get(indexOfHand));
            this.getOnBoard().remove(indexOfHand);
    }

    public void invertCards(int toBeInverted1,int toBeInverted2){
        int i;
        this.onBoard.set(toBeInverted1,this.onBoard.set(toBeInverted2,this.onBoard.get(toBeInverted1)));
        i=this.onBoard.get(toBeInverted1).getIndexOfBoard();
        this.onBoard.get(toBeInverted1).setIndexOfBoard(this.onBoard.get(toBeInverted2).getIndexOfBoard());
        this.onBoard.get(toBeInverted2).setIndexOfBoard(i);
    }

    public void shopLevelUp(){
        if(shopLvl<5){
            shopLevelUpCost--;
            playerGolds--;
            testLevelUp();
    }
    else {
        System.out.println("Last shop level reached");
    }
    }

    public void testLevelUp(){
        if(shopLevelUpCost==0){
            switch (shopLvl){
                case 1: shopLevelUpCost=8;shopLvl++;System.out.println("SHOP LEVEL UP");break;
                case 2: shopLevelUpCost=10;shopLvl++;System.out.println("SHOP LEVEL UP");break;
                case 3: shopLevelUpCost=12;shopLvl++;System.out.println("SHOP LEVEL UP");break;
                case 4: shopLvl++;System.out.println("SHOP LEVEL UP, last level reached");break;
            }
        }
    }

    public void cestQuoiUneListe(Creature creature){
        int indexOfCreature=this.currentOnBoard.indexOf(creature), sizeOfBoard=this.getCurrentOnBoard().size();
        if (indexOfCreature==0 && indexOfCreature==sizeOfBoard-1) {}
        else if(indexOfCreature==0){
            this.currentOnBoard.get(1).setArchetype("BDE");
        }
        else if(indexOfCreature==sizeOfBoard-1){
            this.currentOnBoard.get(indexOfCreature-1).setArchetype("BDE");
        }
        else{
            this.currentOnBoard.get(indexOfCreature-1).setArchetype("BDE");
            this.currentOnBoard.get(indexOfCreature+1).setArchetype("BDE");
        }
    }

    public void boostAleatoART(){
        if(this.getOnBoard().size()>0){
            int rand = (int) Math.floor(Math.random() * this.getOnBoard().size());
            this.getOnBoard().get(rand).setCreatureAtt(this.getOnBoard().get(rand).getCreatureAtt()+1);
            this.getOnBoard().get(rand).setCreatureHp(this.getOnBoard().get(rand).getCreatureHp()+1);
            rand = (int) Math.floor(Math.random() * this.getOnBoard().size());
            this.getOnBoard().get(rand).setCreatureAtt(this.getOnBoard().get(rand).getCreatureAtt()+1);
            this.getOnBoard().get(rand).setCreatureHp(this.getOnBoard().get(rand).getCreatureHp()+1);
        }

    }

    public void cursusEtranger(Creature creature){
        Exile exile = new Exile();
        exile.creature=creature;
        exile.exilTime=0;
        this.exil.add(exile);
        System.out.println(this.getPlayerName()+" "+creature.getCardName()+" has been exiled for 1 turn.");
        this.onBoard.remove(creature);
    }

    public void givePanierBio(int n){
        for (int i = 0; i<n; i++){
            hand.add(Spell.generatePanierBio());
            System.out.println("Panier BIO has been added to "+this.getPlayerName()+" hand.");
        }
    }

    public void gainsAleatoArt(){
        int rand = (int) Math.floor(Math.random() * 4)+1;
        this.setPlayerGolds(this.getPlayerGolds()+rand);
        System.out.println("Le joueur "+this.getPlayerName()+" à gagné "+rand+" golds.");
    }

    public void favoritisme(){
        int rand = (int) Math.floor(Math.random() * this.getOnBoard().size());
        int k = 0;
        while (this.getOnBoard().get(rand).isStudent()==false && k<=this.getOnBoard().size()){
            rand++;
            k++;
            if (rand>=this.getOnBoard().size()){
                rand=0;
            }
        }
        if(k<=this.getOnBoard().size()){
            System.out.println("No on can generate a diploma");
        }
        else {
            this.hand.add(this.getOnBoard().get(rand).generateDiploma());
            System.out.println("Diploma has been added to "+this.getPlayerName()+" hand.");
        }
    }

    public void addSpell(Spell spell, Creature creature){
        if(spell.getCardName().equals("Panier BIO")){
            creature.setPaniersBio(creature.getPaniersBio()+1);
        }
        else{
            creature.getCurrentDiploma().cloneDiploma(spell);
        }
        this.hand.remove(spell);
    }

    public void vousConnaisezLyfPay(){
        this.setPlayerGolds(this.getPlayerGolds()+1);
        System.out.println(this.getPlayerName()+" won 1 gold.");
    }

    public void delation(Player opponent){
        ArrayList<Creature>toBeRemoved=new ArrayList<>();
        for(Creature creature: opponent.currentOnBoard){
            if(creature.getEffectList()[7]==true){
                creature.getEffectList()[7]=false;
                System.out.println(opponent.getPlayerName()+" creature "+creature.getCardName()+" has lost his Bouclier Divin.");
            }
            else if(creature.getCreatureHp()>1){
                creature.setCreatureHp(creature.getCreatureHp()-1);
                System.out.println(opponent.getPlayerName()+" creature "+creature.getCardName()+" has lost 1 hp.");
            }
            else if(creature.getCreatureHp()==1){
                toBeRemoved.add(creature);
            }
        }
        for (Creature creature:toBeRemoved){
            opponent.getCurrentOnBoard().remove(creature);
            System.out.println(creature.getCardName()+" has been removed from "+opponent.getPlayerName()+" currentBoard.");
        }
    }

    public void propagande(){
        Creature creature=new Creature();
        creature.setCreature("CotisantBDE","doc/effectListCSV_epure.csv");
        creature.setInvoctation(true);
        if(this.archetypeList[0]==1){
            creature.setCreatureHp(creature.getCreatureHp()+1);
            if (this.archetypeList[0]==2){
                creature.setCreatureAtt(creature.getCreatureAtt()+1);
                creature.setCreatureHp(creature.getCreatureHp()+1);
            }
            if (this.archetypeList[0]==3){
                creature.getEffectList()[31]=true;
            }
        }
        this.getCurrentOnBoard().add(creature);
        System.out.println("Creature "+creature.getCardName()+" has appeard on "+this.getPlayerName()+" board.");
    }

    public void laSecuriteAvantTout(){
        Creature creature=new Creature();
        creature.setCreature("Vigile","doc/effectListCSV_epure.csv");
        creature.setInvoctation(true);
        if(this.archetypeList[0]==1){
            creature.setCreatureHp(creature.getCreatureHp()+1);
            if (this.archetypeList[0]==2){
                creature.setCreatureAtt(creature.getCreatureAtt()+1);
                creature.setCreatureHp(creature.getCreatureHp()+1);
            }
            if (this.archetypeList[0]==3){
                creature.getEffectList()[31]=true;
            }
        }
        this.getCurrentOnBoard().add(creature);
        System.out.println("Creature "+creature.getCardName()+" has appeard on "+this.getPlayerName()+" board.");
    }

    public void legendNeverDie(){
        ArrayList<Creature>willPop=new ArrayList<>();
        String tab[]={"CotisantBDE","TresorierBDE","VicePresBDE","RespoSoiree","RespoComBDE"};
        int j;
        if(this.getCurrentOnBoard().size()-3>=Game.boardSize){
            j=Game.boardSize-this.getCurrentOnBoard().size()-1;
        }
        else {
            j=4;
        }
        for(int i=0;i<j;i++){
            Creature creature=new Creature();
            int rand = (int) Math.floor(Math.random() * 4);
            creature.setCreature(tab[rand],"doc/effectListCSV_epure.csv");
            creature.setInvoctation(true);
            if(this.archetypeList[0]==1){
                creature.setCreatureHp(creature.getCreatureHp()+1);
                if (this.archetypeList[0]==2){
                    creature.setCreatureAtt(creature.getCreatureAtt()+1);
                    creature.setCreatureHp(creature.getCreatureHp()+1);
                }
                if (this.archetypeList[0]==3){
                    creature.getEffectList()[31]=true;
                }
            }
            willPop.add(creature);
        }
        this.getCurrentOnBoard().addAll(willPop);
        for(Creature creature:willPop){
            System.out.println("Creature "+creature.getCardName()+" has appeard on "+this.getPlayerName()+" board.");
        }
    }

    public void repopAleatoArt(){
        ArrayList<String> nameList = new ArrayList<String>();
        try {
            FileReader fr = new FileReader("doc/effectListCSV_epure.csv");
            BufferedReader br = new BufferedReader(fr);
            String li = br.readLine();
            while (li != null) {
                Integer firstComma = li.indexOf(';');
                Integer secondComma = li.indexOf(';', firstComma + 1);
                Integer thirdComma = li.indexOf(';', secondComma + 1);
                if(Integer.valueOf((li.substring(secondComma + 1, thirdComma)))==1){
                    nameList.add((li.substring(0, firstComma)));
                }
                li = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Creature creature=new Creature();
        int rand = (int) Math.floor(Math.random() * nameList.size());
        creature.setCreature(nameList.get(rand),"doc/effectListCSV_epure.csv");
        creature.setInvoctation(true);
        if(this.archetypeList[0]==1){
            creature.setCreatureHp(creature.getCreatureHp()+1);
            if (this.archetypeList[0]==2){
                creature.setCreatureAtt(creature.getCreatureAtt()+1);
                creature.setCreatureHp(creature.getCreatureHp()+1);
            }
            if (this.archetypeList[0]==3){
                creature.getEffectList()[31]=true;
            }
        }
        this.getCurrentOnBoard().add(creature);
        System.out.println("Creature "+creature.getCardName()+" has appeard on "+this.getPlayerName()+" board.");
    }

    public void corruptionDeJuryDePoursuiteDetudes(){
        for(Creature creature: this.getOnBoard()){
            if(creature.getEffectList()[23]==true){
                this.setPlayerGolds(this.getPlayerGolds()+2);
            }
        }
    }

    public void giveSpell(Creature creature, Spell spell){
        if(spell.getCardName().equals("Panier BIO")){
            creature.setPaniersBio(creature.getPaniersBio()+1);
        }
        else{
            int i;
            creature.getCurrentDiploma().setSpellAttBoost(spell.getSpellAttBoost()+creature.getCurrentDiploma().getSpellAttBoost());
            creature.getCurrentDiploma().setSpellHpBoost(spell.getSpellHpBoost()+creature.getCurrentDiploma().getSpellHpBoost());
            for (i=0; i<48;i++) {
                if (creature.getCurrentDiploma().getThisSpellEffect(i) != spell.getThisSpellEffect(i)) {
                    creature.getCurrentDiploma().setThisSpellEffect(i, true);
                }
            }
        }
    }
}
