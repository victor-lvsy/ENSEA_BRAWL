public class Spell extends Card{
    private int spellHpBoost;

    private int spellAttBoost;

    private boolean spellEffect[] = new boolean[47];

    public int getSpellHpBoost() {
        return spellHpBoost;
    }

    public int getSpellAttBoost() {
        return spellAttBoost;
    }


    public void setSpellHpBoost(int spellHpBoost) {
        this.spellHpBoost = spellHpBoost;
    }

    public void setSpellAttBoost(int spellAttBoost) {
        this.spellAttBoost = spellAttBoost;
    }

    public boolean[] getSpellEffect() {
        return spellEffect;
    }

    public boolean getThisSpellEffect(int i) {
        return spellEffect[i];
    }

    public void setSpellEffect(boolean[] spellEffect) {
        this.spellEffect = spellEffect;
    }

    public void setThisSpellEffect(int toBeSet,boolean active) {
        this.spellEffect[toBeSet] = active;
    }

}
