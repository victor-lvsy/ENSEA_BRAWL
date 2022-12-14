import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Initialisation {

    public static ArrayList<Player> players = new ArrayList<Player>();

    int i=0;
    private Player player1 = new Player("zssnu",30,999999, new ImageView(new Image("file:ImageJeu/joueur1.png")));
    private Player player2 = new Player("Sltmapoule",30,999999, new ImageView(new Image("file:ImageJeu/joueur2.png")));
    private Player player3 = new Player("Eitonan",30,999999, new ImageView(new Image("file:ImageJeu/joueur3.png")));
    private Player player4 = new Player("I rule ENSEA",30,999999, new ImageView(new Image("file:ImageJeu/joueur4.png")));
    private Player player5 = new Player("PPZ",30,999999, new ImageView(new Image("file:ImageJeu/joueur5.png")));
    private Player player6 = new Player("PixelBlot",30,999999, new ImageView(new Image("file:ImageJeu/joueur6.png")));
    private ArrayList<String> nameList = new ArrayList<String>();
    private ArrayList<Integer> tierList = new ArrayList<Integer>();
    private ArrayList<Creature> creaturePool = new ArrayList<Creature>();
    public ArrayList<String> getNameList() {
        return nameList;
    }

    public void Initialisation (){
        try {
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
        players.add(player1);players.add(player2);
        players.add(player3);players.add(player4);
        players.add(player5);players.add(player6);


    }

    private void creatingTypeOfCreature (String name,int i){
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

    public Player getPlayer1() {
        return player1;
    }
}
