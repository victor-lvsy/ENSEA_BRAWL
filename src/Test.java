import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
    Initialisation init = new Initialisation();
    init.Initialisation();
    boolean isDead[] = new boolean[2];
    Creature createst1 = new Creature();
    Creature createst2 = new Creature();
    Creature createst3 = new Creature();
    createst1.setCreature(init.getNameList().get(6),"doc/effectListCSV_epure.csv");
    createst2.setCreature(init.getNameList().get(8),"doc/effectListCSV_epure.csv");
    createst3.setCreature(init.getNameList().get(12),"doc/effectListCSV_epure.csv");
    isDead = createst3.attackCreature(createst2);
    }
}
