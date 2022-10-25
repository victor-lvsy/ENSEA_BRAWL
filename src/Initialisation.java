import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Initialisation {
    int i=0;
    public static Player player1 = new Player("zssnu",30,3);
    public static Player player2 = new Player("Sltmapoule",30,3);
    public static Player player3 = new Player("Eitonan",30,3);
    public static Player player4 = new Player("I rule ENSEA",30,3);
    public static Player player5 = new Player("PPZ",30,3);
    public static Player player6 = new Player("PixelBlot",30,3);

    public ArrayList<String> getNameList() {
        return nameList;
    }
    private ArrayList<String> nameList = new ArrayList<String>();
    private ArrayList<Integer> tierList = new ArrayList<Integer>();
    private ArrayList<Creature> creaturePool = new ArrayList<Creature>();
    public void Initialisation(){
;        try {
            FileReader fr = new FileReader("doc/effectListCSV_epure.csv");
            BufferedReader br = new BufferedReader(fr);
            String li = br.readLine();
            while (li != null) {
                Integer firstComma = li.indexOf(';');
                this.nameList.add((li.substring(0, firstComma)));
                Integer secondComma = li.indexOf(';', firstComma + 1);
                Integer thirdComma = li.indexOf(';', secondComma + 1);
                this.tierList.add(Integer.valueOf((li.substring(secondComma + 1, thirdComma))));
                li = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(String name:nameList){
            switch (this.tierList.get(i)){
                case 0: creatingTypeOfCreature(name,17);break;
                case 1: creatingTypeOfCreature(name,15);break;
                case 2: creatingTypeOfCreature(name,13);break;
                case 3: creatingTypeOfCreature(name,11);break;
                case 4: creatingTypeOfCreature(name,9);break;
                case 5: creatingTypeOfCreature(name,6);break;
            }
            i++;
        }
    }

    private void creatingTypeOfCreature(String name,int i){
        for (int j=0;j<i;j++){
            Creature creature =new Creature();
            creature.setCreature(name,"doc/effectListCSV_epure.csv");
            creaturePool.add(creature);
        }
    }

    public void setCreaturePool(ArrayList<Creature> creaturePool) {
        this.creaturePool = creaturePool;
    }
    public ArrayList<Creature> getCreaturePool() {
        return creaturePool;
    }
}
