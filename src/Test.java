public class Test {
    public static void main(String[] args) {
    boolean isDead[] = new boolean[2];
    Creature createst1 = new Creature();
    Creature createst2 = new Creature();
    Creature createst3 = new Creature();
    createst1.setCreature("Intervenant","doc/effectListCSV_epure.csv");
    createst2.setCreature("Le Rat","doc/effectListCSV_epure.csv");
    createst3.setCreature("Directeur des etudes","doc/effectListCSV_epure.csv");
    isDead = createst1.attackCreature(createst2);
    }
}
