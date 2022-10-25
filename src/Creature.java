import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Creature extends Card{
    private int creatureHp;
    private int creatureAtt;
    private int creatureTier;
    private String archetype = new String();
    private Spell currentDiploma = new Spell();
    private Spell toBeGeneratedDiploma = new Spell();
    private Spell currentItem;
    private Spell paniersBio;
    private boolean effectList[]=new boolean[47];
    private int numberOfAttacks;
    private boolean currentBouclierDivin;
    private boolean currentCamouflage;
    public boolean[] getEffectList() {
        return effectList;
    }
    public int getCreatureHp() {
        return creatureHp;
    }
    public int getCreatureAtt() {
        return creatureAtt;
    }
    public void setCreatureHp(int creatureHp) {
        this.creatureHp = creatureHp;
    }
    public void setCreatureAtt(int creatureAtt) {
        this.creatureAtt = creatureAtt;
    }
    public boolean isCurrentCamouflage() {
        return currentCamouflage;
    }
    public void setCurrentCamouflage(boolean currentCamouflage) {
        this.currentCamouflage = currentCamouflage;
    }
    public boolean isCurrentBouclierDivin() {
        return currentBouclierDivin;
    }
    public void setCurrentBouclierDivin(boolean currentBouclierDivin) {
        this.currentBouclierDivin = currentBouclierDivin;
    }
    public int getNumberOfAttacks() {
        return numberOfAttacks;
    }
    public void setNumberOfAttacks(int numberOfAttacks) {
        this.numberOfAttacks = numberOfAttacks;
    }
    public void setToFalseEffectList(boolean toBeSet[]){
        for(int i=0;i<=46;i++) {
            toBeSet[i] = false;
        }
    }
    public void setCreature(String Name, String fileName){
        setToFalseEffectList(this.effectList);
        this.toBeGeneratedDiploma.setSpellEffect(this.effectList);
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
                    this.effectList[stringToInt(li.substring(fifthComma + 1, sixthComma))]=true;
                    this.toBeGeneratedDiploma.setThisSpellEffect(stringToInt(li.substring(eighthComma + 1)),true);
                }
                li = br.readLine();
            }
            } catch(FileNotFoundException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            }
    }
    public void stringToIntALEAStats(String toBeConverted1,String toBeConverted2){
        if (toBeConverted1.equals("ALEA")){
            try {
                int number = Integer.parseInt(toBeConverted2);
                this.creatureAtt = (int) (Math.random() * (number));
                this.creatureHp=number-this.creatureAtt+1;
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
     }
        else {
            try {
                this.creatureAtt = Integer.parseInt(toBeConverted1);
                this.creatureHp = Integer.parseInt(toBeConverted2);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void stringToIntALEADiploma(String toBeConverted1,String toBeConverted2){
        if (toBeConverted1.equals("ALEA")){
            try {
                int number = Integer.parseInt(toBeConverted2);
                this.toBeGeneratedDiploma.setSpellAttBoost((int) (Math.random() * (number)));
                this.toBeGeneratedDiploma.setSpellHpBoost(number-this.toBeGeneratedDiploma.getSpellAttBoost()+1);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        else {
            try {
                this.toBeGeneratedDiploma.setSpellAttBoost(Integer.parseInt(toBeConverted1));
                this.toBeGeneratedDiploma.setSpellHpBoost(Integer.parseInt(toBeConverted2));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
    }
    public int stringToInt(String toBeConverted){
            try {
                int number = Integer.parseInt(toBeConverted);
                return number;
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        return 0;
        }
    public void effectAdder(){
        int i=0;
        for (boolean b : this.currentDiploma.getSpellEffect()){
            if(this.toBeGeneratedDiploma.getThisSpellEffect(i)!=this.currentDiploma.getThisSpellEffect(i)){
                this.toBeGeneratedDiploma.setThisSpellEffect(i,true);
            }
        }
    }
    public boolean[] attackCreature(Creature isAttacked){
        boolean isDead[] = new boolean[2];
        isDead[0]=false;isDead[1]=false;
        if(isAttacked.getEffectList()[6]==false){
            if(isAttacked.isCurrentBouclierDivin()==false){
               isAttacked.setCreatureHp(isAttacked.creatureHp-this.creatureAtt);
               this.setCreatureHp(this.creatureHp- isAttacked.creatureAtt);
               if(this.creatureHp<=0){
                   isDead[0]=true;
               }
                if(isAttacked.creatureHp<=0){
                    isDead[1]=true;
                }
            }
        }
        isAttacked.setCurrentBouclierDivin(false);
        this.setCurrentCamouflage(false);
        return isDead;
    }

}
