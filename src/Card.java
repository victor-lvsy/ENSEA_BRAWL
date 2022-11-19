import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Card {
    private final Pane pane = new Pane();
    private String CardName;
    private final Text shopLevel = new Text();
    private final Text attack = new Text();
    private final Text health = new Text();
    private final Text name = new Text();
    private final ImageView cardImgView = new ImageView();

    public Card() {
        ImageView fondCarte = new ImageView(new Image("file:ImageJeu/fondCarte.png"));
        pane.getChildren().add(cardImgView);
        pane.getChildren().add(fondCarte);
        pane.getChildren().add(attack);
        pane.getChildren().add(health);
        pane.getChildren().add(name);
        pane.getChildren().add(shopLevel);
    }

    public void update(){
        cardImgView.setImage(new Image("file:ImageJeu/" + CardName + ".png"));
        Font font = new Font("Verdana", 20);
        Font fontName = new Font("Verdana", 14);
        name.setText(CardName);
        cardImgView.setX(8);
        cardImgView.setY(53);
        shopLevel.setX(23);
        shopLevel.setY(35);
        shopLevel.setFont(font);
        shopLevel.setFill(Color.BLACK);
        attack.setX(16-(int)attack.getText().length()*6.2);
        attack.setY(309);
        attack.setFont(font);
        health.setX(184-(int)health.getText().length()*6.2);
        health.setY(309);
        health.setFont(font);
        name.setX(47);
        name.setY(33);
        name.setFont(fontName);
        name.setFill(Color.WHITE);

    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public Text getShopLevel() {
        return shopLevel;
    }

    public Pane getPane() {
        return pane;
    }

    public Text getAttack() {
        return attack;
    }

    public Text getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return CardName;
    }
}
