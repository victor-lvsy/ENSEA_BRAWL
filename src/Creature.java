import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Creature extends Card {
    private int creatureHp;

    private int creatureAtt;

    private int creatureTier;

    private String archetype = new String();

    private Spell currentDiploma = new Spell();

    private Spell toBeGeneratedDiploma = new Spell();

    private Spell currentItem;

    private Spell paniersBio;

    private int alreadyFight;

    private int indexOfBoard;

    private boolean effectList[] = new boolean[48];

    public int getIndexOfBoard() {
        return indexOfBoard;
    }

    public void setIndexOfBoard(int indexOfBoard) {
        this.indexOfBoard = indexOfBoard;
    }

    private int numberOfAttacks;

    public void setEffectList(boolean effect, int i) {
        this.effectList[i] = effect;
    }

    public boolean[] getEffectList() {
        return effectList;
    }

    public int getCreatureHp() {
        return creatureHp;
    }

    public int getCreatureAtt() {
        return creatureAtt;
    }

    public String getArchetype() {
        return archetype;
    }

    public int getCreatureTier() {
        return creatureTier;
    }

    public void setCreatureHp(int creatureHp) {
        this.creatureHp = creatureHp;
    }

    public void setCreatureAtt(int creatureAtt) {
        this.creatureAtt = creatureAtt;
    }

    public int getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public void setNumberOfAttacks(int numberOfAttacks) {
        this.numberOfAttacks = numberOfAttacks;
    }

    public Spell getToBeGeneratedDiploma() {
        return toBeGeneratedDiploma;
    }

    public void setToFalseEffectList(boolean toBeSet[]) {
        for (int i = 0; i <= 47; i++) {
            toBeSet[i] = false;
        }
    }

    public void setCreature(String Name, String fileName) {
        setToFalseEffectList(this.effectList);
        setToFalseEffectList(this.toBeGeneratedDiploma.getSpellEffect());
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String li = br.readLine();
            while (li != null) {
                Integer firstComma = li.indexOf(';');
                if (Name.equals(li.substring(0, firstComma))) {
                    this.setCardName(li.substring(0, firstComma));
                    Integer secondComma = li.indexOf(';', firstComma + 1);
                    this.archetype = li.substring(firstComma + 1, secondComma);
                    Integer thirdComma = li.indexOf(';', secondComma + 1);
                    this.creatureTier = stringToInt(li.substring(secondComma + 1, thirdComma));
                    Integer fourthComma = li.indexOf(';', thirdComma + 1);
                    Integer fifthComma = li.indexOf(';', fourthComma + 1);
                    Integer sixthComma = li.indexOf(';', fifthComma + 1);
                    Integer seventhComma = li.indexOf(';', sixthComma + 1);
                    Integer eighthComma = li.indexOf(';', seventhComma + 1);
                    stringToIntALEAStats(li.substring(thirdComma + 1, fourthComma), li.substring(fourthComma + 1, fifthComma));
                    stringToIntALEADiploma(li.substring(sixthComma + 1, seventhComma), li.substring(seventhComma + 1, eighthComma));
                    this.effectList[stringToInt(li.substring(fifthComma + 1, sixthComma))] = true;
                    this.toBeGeneratedDiploma.setThisSpellEffect(stringToInt(li.substring(eighthComma + 1)), true);
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
    }

    public void stringToIntALEAStats(String toBeConverted1, String toBeConverted2) {
        if (toBeConverted1.equals("ALEA")) {
            try {
                int number = Integer.parseInt(toBeConverted2);
                this.creatureAtt = (int) (Math.random() * (number));
                this.creatureHp = number - this.creatureAtt + 1;
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                this.creatureAtt = Integer.parseInt(toBeConverted1);
                this.creatureHp = Integer.parseInt(toBeConverted2);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void stringToIntALEADiploma(String toBeConverted1, String toBeConverted2) {
        if (toBeConverted1.equals("ALEA")) {
            try {
                int number = Integer.parseInt(toBeConverted2);
                this.toBeGeneratedDiploma.setSpellAttBoost((int) (Math.random() * (number)));
                this.toBeGeneratedDiploma.setSpellHpBoost(number - this.toBeGeneratedDiploma.getSpellAttBoost() + 1);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                this.toBeGeneratedDiploma.setSpellAttBoost(Integer.parseInt(toBeConverted1));
                this.toBeGeneratedDiploma.setSpellHpBoost(Integer.parseInt(toBeConverted2));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int stringToInt(String toBeConverted) {
        try {
            int number = Integer.parseInt(toBeConverted);
            return number;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void effectAdder() {
        int i = 0;
        for (boolean b : this.currentDiploma.getSpellEffect()) {
            if (this.toBeGeneratedDiploma.getThisSpellEffect(i) != this.currentDiploma.getThisSpellEffect(i)) {
                this.toBeGeneratedDiploma.setThisSpellEffect(i, true);
            }
        }
    }

    public int[] attackCreature(Creature defender) {
        int isDead[] = {0,0,0,0,0};
        int bd=gestionBoucliersDivins(this.getEffectList()[7],defender.getEffectList()[7]);
        isDead = attackWithBDKnown(defender, isDead, bd);
        this.setEffectList(false, 8);
        this.setAlreadyFight(this.getAlreadyFight()+1);
        return isDead;
    }

    public int[] cleave(Creature defender, Player isAttacked){
        int isDead[] = {0,0,0,0,0};
        int sizeOfBoard=isAttacked.getCurrentOnBoard().size();
        int bd=gestionBoucliersDivins(this.getEffectList()[7],defender.getEffectList()[7]);
        isDead = attackWithBDKnown(defender, isDead, bd);
        if (isAttacked.getCurrentOnBoard().indexOf(defender)==0 && isAttacked.getCurrentOnBoard().indexOf(defender)==sizeOfBoard-1) {}
        else if(isAttacked.getCurrentOnBoard().indexOf(defender)==0){
            isDead[0]=3;
            boolean bd2=isAttacked.getCurrentOnBoard().get(1).getEffectList()[7];
            if (bd2==false){
                isAttacked.getCurrentOnBoard().get(1).setCreatureHp(isAttacked.getCurrentOnBoard().get(1).getCreatureHp()-this.getCreatureAtt());
                System.out.println("Attacker "+ this.getCardName()+" did " + this.getCreatureAtt() + "additional damages to" + isAttacked.getCurrentOnBoard().get(1).getCardName() +"due to cleave");
                isDead[4]=simpleIsDead(isAttacked.getCurrentOnBoard().get(1).getCreatureHp());
            }
            if(this.getCreatureAtt()>0){
                isAttacked.getCurrentOnBoard().get(1).getEffectList()[7]=false;
            }
        }
        else if(isAttacked.getCurrentOnBoard().indexOf(defender)==sizeOfBoard-1){
            isDead[0]=2;
            boolean bd2=isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).getEffectList()[7];
            if (bd2==false){
                isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).setCreatureHp(isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).getCreatureHp()-this.getCreatureAtt());
                System.out.println("Attacker "+ this.getCardName()+" did " + this.getCreatureAtt() + "additional damages to" + isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).getCardName() +"due to cleave");
                isDead[3]=simpleIsDead(isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).getCreatureHp());
            }
            if(this.getCreatureAtt()>0){
                isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).getEffectList()[7]=false;
            }
        }
        else{
            isDead[0]=1;
            boolean bd2=isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).getEffectList()[7],
                        bd3=isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)+1).getEffectList()[7];
            if (bd2==false){
                isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).setCreatureHp(isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).getCreatureHp()-this.getCreatureAtt());
                System.out.println("Attacker "+ this.getCardName()+" did " + this.getCreatureAtt() + "additional damages to" + isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).getCardName() +"due to cleave");
                isDead[3]=simpleIsDead(isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).getCreatureHp());
            }
            if (bd3==false){
                isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)+1).setCreatureHp(isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)+1).getCreatureHp()-this.getCreatureAtt());
                System.out.println("Attacker "+ this.getCardName()+" did " + this.getCreatureAtt() + "additional damages to" + isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)+1).getCardName() +"due to cleave");
                isDead[4]=simpleIsDead(isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)+1).getCreatureHp());
            }
            if(this.getCreatureAtt()>0){
                isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)+1).getEffectList()[7]=false;
            }
            if(this.getCreatureAtt()>0){
                isAttacked.getCurrentOnBoard().get(isAttacked.getCurrentOnBoard().indexOf(defender)-1).getEffectList()[7]=false;
            }
        }

        return isDead;
    }

    private int[] attackWithBDKnown(Creature defender, int[] isDead, int bd) {
        switch (bd){
            case 0: defender.setCreatureHp(defender.getCreatureHp() - this.getCreatureAtt());
                this.setCreatureHp(this.getCreatureHp() - defender.getCreatureAtt());
                System.out.println("Attacker "+ this.getCardName()+" did " + this.getCreatureAtt() + " damages and Defender "+defender.getCardName()+" did " + defender.getCreatureAtt() + " damages");
                isDead=isDead(this.getCreatureHp(),defender.getCreatureHp());
                break;
            case 1: defender.setCreatureHp(defender.getCreatureHp() - this.getCreatureAtt());
                System.out.println("Attacker "+ this.getCardName() +" did " + this.getCreatureAtt() + " damages and Defender "+defender.getCardName()+" did 0 damages");
                isDead=isDead(this.getCreatureHp(),defender.getCreatureHp());
                voidingBD(this,defender);
                break;
            case 2: this.setCreatureHp(this.getCreatureHp() - defender.getCreatureAtt());
                System.out.println("Attacker "+this.getCardName()+" did 0 damages and Defender "+defender.getCardName()+" did " + defender.getCreatureAtt() + " damages");
                isDead=isDead(this.getCreatureHp(),defender.getCreatureHp());
                voidingBD(this,defender);
                break;
            case 3: System.out.println("Attacker "+ this.getCardName()+" did 0 damages and Defender "+defender.getCardName()+" did 0 damages");
                isDead=isDead(this.getCreatureHp(),defender.getCreatureHp());
                voidingBD(this,defender);
                break;
            default:
        }
        return isDead;
    }

    public int gestionBoucliersDivins(boolean attacker, boolean defender) {
        if (attacker == true && defender == false) {
            return 1;
        }
        if (attacker == false && defender == true) {
            return 2;
        }
        if (attacker == true && defender == true) {
            return 3;
        }
        return 0;
    }

    public int[] isDead(int i,int j){
        int isDead[] = {0,0,0,0,0};
        if (i <= 0) {
            isDead[1] = 1;
            System.out.println("Attacker died fighting");
        }
        if (j <= 0) {
            isDead[2] = 1;
            System.out.println("Defender died fighting");
        }
        return isDead;
    }
    
    public int simpleIsDead(int i){
        int j=0;
        if (i <= 0) {
            j = 1;
            System.out.println("Additional defender died");
        }
        return j;
    }

    public void voidingBD(Creature attacker, Creature defender){
        if(defender.getCreatureAtt()>0){
            attacker.setEffectList(false, 7);
        }
        if(attacker.getCreatureAtt()>0){
            defender.setEffectList(false, 7);
        }
    }

    public int getAlreadyFight() {
        return alreadyFight;
    }

    public void setAlreadyFight(int alreadyFight) {
        this.alreadyFight = alreadyFight;
    }

    public void copyCreature(Creature creature){
        this.creatureAtt=creature.getCreatureAtt();
        this.creatureHp=creature.creatureHp;
        this.creatureTier=creature.creatureTier;
        this.archetype=creature.archetype;
        this.toBeGeneratedDiploma=creature.toBeGeneratedDiploma;
        this.currentDiploma=creature.currentDiploma;
        this.effectList=creature.effectList.clone();
        this.currentItem=creature.currentItem;
        this.paniersBio=creature.paniersBio;
        this.setCardName(creature.getCardName());

    }

    public void stealStatAtt(Creature toBeSteal,int n){
        if (toBeSteal.getCreatureAtt()-n>=0){
            toBeSteal.setCreatureAtt(toBeSteal.getCreatureAtt()-n);
            this.setCreatureAtt(this.getCreatureAtt()+n);
            System.out.println(toBeSteal.getCardName()+" Attack has been stolen, it is now "+toBeSteal.getCreatureAtt()+" and "+this.getCardName()+" Attack is now "+this.getCreatureAtt()+".");
        } else if (toBeSteal.getCreatureAtt()<0) {
            this.setCreatureAtt(this.getCreatureAtt()+toBeSteal.getCreatureAtt());
            toBeSteal.setCreatureAtt(0);
            System.out.println(toBeSteal.getCardName()+" Attack has been stolen, it is now 0 and "+this.getCardName()+" Attack is now "+this.getCreatureAtt()+".");
        }
        else {
            System.out.println(toBeSteal.getCardName()+" Attack cannot be stolen, it is already 0.");
        }
    }

    public void volDAssos(Player opponent){
        for(Creature creature:opponent.getCurrentOnBoard()){
            if(creature.getCardName().contains("Tresorier")){
                this.setCreatureAtt(this.getCreatureAtt()+1);
                this.setCreatureHp(this.getCreatureHp()+1);
            }
        }
        System.out.println("This creature "+this.getCardName()+" Attack is now: "+this.getCreatureAtt()+" and Hp is now: "+this.getCreatureHp());
    }

    public void ardoiseKfet(Player opponent){
        if(opponent.getPlayerGolds()<2){
            opponent.setPlayerHp(opponent.getPlayerHp()-1);
            System.out.println("Player "+opponent.getPlayerName()+" lost 1 Hp because he had not enough golds");
        }
        else {
            opponent.setPlayerGolds(opponent.getPlayerGolds()-2);
            System.out.println("Player "+opponent.getPlayerName()+" lost 2 golds");
        }
    }

    public void annulationDactivite(Player opponent){
        ArrayList<String> archList=new ArrayList<String>();
        ArrayList<Creature> save=new ArrayList<>();
        if(opponent.getCurrentOnBoard().size()>0){
            for(Creature creature: opponent.getCurrentOnBoard()){
                archList.add(creature.getArchetype());
            }
            int rand = (int) Math.floor(Math.random() * archList.size());
            String nerfedArch = archList.get(rand);
            for(Creature creature: opponent.getCurrentOnBoard()){
                if (creature.getArchetype().equals(nerfedArch)){
                    if(creature.getCreatureAtt()>0){
                        creature.setCreatureAtt(creature.getCreatureAtt()-1);
                    }
                    if(creature.getCreatureHp()>0){
                        creature.setCreatureHp(creature.getCreatureHp()-1);
                        if(creature.getCreatureHp()<=0){
                            System.out.println(creature.getCardName() + " has been removed from "+opponent.getPlayerName() +" currentBoard");
                            save.add(creature);
                        }
                    }
                }
            }
            for(int i=0;i<save.size();i++){
                opponent.getCurrentOnBoard().remove(save.get(0));
            }
        }
    }

    public void jeSuisAleatoArt(){
        int tab[];
        int i=0;
        tab= new int[]{7, 31, 35, 46};
        int rand = (int) Math.floor(Math.random() * 4);
        i++;
        while (this.getEffectList()[tab[rand]]==true && i<4){
            if(rand==3){
                rand=0;
            }
            else {rand++;}
            i++;
        }
        if(i<4){
            this.getEffectList()[tab[rand]]=true;
        }
        else {
            System.out.println(this.getCardName()+" has already all the effects");
        }
    }

    public void nousSommesAleatoART(Player player){
        int tab[];
        int i=0;
        tab= new int[]{7, 46, 47};
        ArrayList<Creature> bda= new ArrayList<Creature>();
        int rand = (int) Math.floor(Math.random() * 3);

        for(Creature creature: player.getCurrentOnBoard()){
            if (creature.getArchetype().equals("BDA")){
                bda.add(creature);
            }
        }
        int alea = (int) Math.floor(Math.random() * bda.size());
        i++;
        while (bda.get(alea).getEffectList()[tab[rand]]==true && i<3){
            if(rand==2){
                rand=0;
            }
            else {rand++;}
            i++;
        }
        if(i<3){
            bda.get(alea).getEffectList()[tab[rand]]=true;
        }
        else {
            System.out.println(bda.get(alea).getCardName()+" has already all the effects");
        }
    }

    public void armureEnPcbRecycle(Player player){
        for (Creature creature:player.getCurrentOnBoard()){
            if(creature.effectList[35]==true){
                creature.setCreatureHp(creature.getCreatureHp()+2);
            }
        }
    }

    public void karsherisationDesFaibles(Player player){
        for (Creature creature:player.getCurrentOnBoard()){
            int j=0;
            for(int i=0;i<creature.toBeGeneratedDiploma.getSpellEffect().length;i++){
                if(creature.toBeGeneratedDiploma.getSpellEffect()[i]==true){
                    j++;
                }
            }
            if(creature.toBeGeneratedDiploma.getSpellAttBoost()!=0 || creature.toBeGeneratedDiploma.getSpellHpBoost()!=0 || j!=1){
                if(creature.currentDiploma.getSpellAttBoost()==0 && creature.currentDiploma.getSpellHpBoost()==0 && creature.currentDiploma.getSpellEffect()[0]==false){
                    if(creature.getCreatureAtt()-5>=0){
                        creature.setCreatureAtt(creature.getCreatureAtt()-5);
                    }
                    else{
                        creature.setCreatureAtt(0);
                    }
                }
            }
        }
    }

    public void sippingDeSubventions(Player defender, Player attacker ){
        int alea;
        if(defender.getCurrentOnBoard().size()>0){
            alea = (int) Math.floor(Math.random() * defender.getCurrentOnBoard().size());
            while (defender.getCurrentOnBoard().get(alea).getCreatureAtt()==0){
                alea = (int) Math.floor(Math.random() * defender.getCurrentOnBoard().size());
            }
            this.setCreatureAtt(this.getCreatureAtt()+1);
            defender.getCurrentOnBoard().get(alea).setCreatureAtt(defender.getCurrentOnBoard().get(alea).getCreatureAtt()-1);
            attacker.getOnBoard().get(this.getIndexOfBoard()).setCreatureAtt(attacker.getOnBoard().get(this.getIndexOfBoard()).getCreatureAtt()+1);
        }
        else {
            System.out.println("No one can be sipped");
        }
    }

    public void dontTalkAboutBruno(Creature creature, Player attacker){
        int alea = ((int) Math.floor(Math.random() * 2));
        creature.creatureAtt=creature.creatureAtt-alea;
        creature.creatureHp=creature.creatureHp-(2-alea);
        attacker.getOnBoard().get(creature.indexOfBoard).creatureAtt=attacker.getOnBoard().get(creature.indexOfBoard).creatureAtt-alea;
        attacker.getOnBoard().get(creature.indexOfBoard).creatureHp=attacker.getOnBoard().get(creature.indexOfBoard).creatureHp-(2-alea);
    }

    public void deviensAleatoART(){
        int tab[];
        int i=0;
        tab= new int[]{7, 31, 35, 46, 47};
        int rand = (int) Math.floor(Math.random() * 5);
        i++;
        while (this.getEffectList()[tab[rand]]==true && i<5){
            if(rand==4){
                rand=0;
            }
            else {rand++;}
            i++;
        }
        if(i<5){
            this.getEffectList()[tab[rand]]=true;
        }
        else {
            System.out.println(this.getCardName()+" has already all the effects");
        }
    }

    public Spell generateDiploma(){
        Spell diploma = new Spell();
        diploma.cloneDiploma(this.getToBeGeneratedDiploma());
        return diploma;
    }

}
