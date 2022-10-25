public class Spell extends Card{
    public int getSpellHpBoost() {
        return spellHpBoost;
    }

    public int getSpellAttBoost() {
        return spellAttBoost;
    }

    public boolean isReusable() {
        return reusable;
    }
    public void setSpellHpBoost(int spellHpBoost) {
        this.spellHpBoost = spellHpBoost;
    }
    public void setSpellAttBoost(int spellAttBoost) {
        this.spellAttBoost = spellAttBoost;
    }

    public void setReusable(boolean reusable) {
        this.reusable = reusable;
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

    private int spellHpBoost;
    private int spellAttBoost;
    private boolean reusable;
    private boolean spellEffect[] = new boolean[47];
}
