import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Creature extends Card {
    private int creatureHp;
    private int creatureAtt;
    private int creatureTier;
    private String archetype = new String();
    private Spell currentDiploma = new Spell();
    private Spell toBeGeneratedDiploma = new Spell();
    private Spell currentItem;
    private Spell paniersBio;
    private boolean effectList[] = new boolean[47];
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

    public void setToFalseEffectList(boolean toBeSet[]) {
        for (int i = 0; i <= 46; i++) {
            toBeSet[i] = false;
        }
    }

    public void setCreature(String Name, String fileName) {
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

    public boolean[] attackCreature(Creature defender) {
        boolean isDead[] = new boolean[2];
        isDead[0] = false;isDead[1] = false;
        int bd=gestionBoucliersDivins(this.getEffectList()[7],defender.getEffectList()[7]);
        switch (bd){
            case 0: defender.setCreatureHp(defender.creatureHp - this.creatureAtt);
                this.setCreatureHp(this.creatureHp - defender.creatureAtt);
                System.out.println("Attacker did " + this.creatureAtt + " damages and Defender did " + defender.creatureAtt + " damages");
                isDead=isDead(this.creatureHp,defender.creatureHp);
                break;
            case 1: defender.setCreatureHp(defender.creatureHp - this.creatureAtt);
                System.out.println("Attacker did " + this.creatureAtt + " damages and Defender did 0 damages");
                isDead=isDead(this.creatureHp,defender.creatureHp);
                voidingBD(this,defender);
                break;
            case 2: this.setCreatureHp(this.creatureHp - defender.creatureAtt);
                System.out.println("Attacker did 0 damages and Defender did " + defender.creatureAtt + " damages");
                isDead=isDead(this.creatureHp,defender.creatureHp);
                voidingBD(this,defender);
                break;
            case 3: System.out.println("Attacker did 0 damages and Defender did 0 damages");
                isDead=isDead(this.creatureHp,defender.creatureHp);
                voidingBD(this,defender);
                break;
            default:
        }
        defender.setEffectList(false, 7);
        this.setEffectList(false, 8);
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
    public boolean[] isDead(int i,int j){
        boolean isDead[] = new boolean[2];
        isDead[0] = false;isDead[1] = false;
        if (i <= 0) {
            isDead[0] = true;
            System.out.println("Attacker died fighting");
        }
        if (j <= 0) {
            isDead[1] = true;
            System.out.println("Defender died fighting");
        }
        return isDead;
    }
    public void voidingBD(Creature attacker, Creature defender){
        if(defender.creatureAtt>0){
            attacker.setEffectList(false, 7);
        }
        if(attacker.creatureAtt>0){
            defender.setEffectList(false, 7);
        }
    }
}
