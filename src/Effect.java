import java.util.ArrayList;

public class Effect {
    private String effectName;
    private int effectId;
    public String getEffectName() {
        return effectName;
    }
    public void furieDesVents(Creature getEffect){
        if(getEffect.getNumberOfAttacks()==1){
            getEffect.setNumberOfAttacks(getEffect.getNumberOfAttacks()+1);
        }
    }
    public void bouclierDivin(Creature getEffect){
        if(getEffect.isCurrentBouclierDivin() == false){
            getEffect.setCurrentBouclierDivin(true);
        }
    }
}
