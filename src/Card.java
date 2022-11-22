import javafx.scene.control.Button;
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
    private boolean bool = false ;
    private final Button button = new Button("Buy");
    private int action = 0;
    private boolean newBool = false;
    private final ImageView pvatt = new ImageView(new Image("file:ImageJeu/HP_ATT.png"));
    private final ImageView fondCarte = new ImageView(new Image("file:ImageJeu/fondCarte.png"));
    private Font font = new Font("Verdana", 24);
    private Font font2 = new Font("Verdana", 20);


    public Card() {
        pane.getChildren().add(cardImgView);
        pane.getChildren().add(fondCarte);
        pane.getChildren().add(pvatt);
        pane.getChildren().add(attack);
        pane.getChildren().add(health);
        pane.getChildren().add(name);
        pane.getChildren().add(shopLevel);
        pane.getChildren().add(button);
        pvatt.setX(15);
        pvatt.setY(60);
        shopLevel.setFont(font);
        health.setFont(font);
        attack.setFont(font);
        Font fontName = new Font("Verdana", 12);
        name.setFont(fontName);
        name.setFill(Color.WHITE);
        button.setVisible(false);
        cardImgView.setOnMouseClicked(mouseEvent -> {
            newBool = true;
            bool = !bool;
        });

        button.setOnMouseClicked(mouseEvent -> {
            action += 1;
            this.reduit();
        });
        this.reduit();
    }

    public void reduit(){
        attack.setFont(font2);
        health.setFont(font2);
        attack.setY(154);
        health.setY(154);
        attack.setX(28);
        health.setX(169);
        pvatt.setVisible(true);
        button.setVisible(false);
        fondCarte.setVisible(false);
        name.setVisible(false);
        shopLevel.setVisible(false);
    }

    public void entiere(){
        attack.setFont(font);
        health.setFont(font);
        attack.setY(300);
        health.setY(300);
        attack.setX(25);
        health.setX(172);
        pvatt.setVisible(false);
        button.setVisible(true);
        fondCarte.setVisible(true);
        name.setVisible(true);
        shopLevel.setVisible(true);
    }

    public void update(){
        name.setText(CardName);
        cardImgView.setImage(new Image("file:ImageJeu/" + CardName + ".png"));
        cardImgView.setX(17);
        cardImgView.setY(55);
        shopLevel.setX(21);
        shopLevel.setY(35);
        name.setX(58);
        name.setY(33);
        name.setFill(Color.BLACK);
        button.setTranslateX(85);
        button.setTranslateY(285);
    }

    public ImageView getCardView() {
        return cardImgView;
    }

    public boolean isBool() {
        return bool;
    }

    public void clear() {
        this.bool = false;
        this.cardImgView.setEffect(null);
        button.setVisible(false);
    }

    public Button getButton() {
        return button;
    }
    public int getAction() {
        return action;
    }
    public String getCardName() {
        return CardName;
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

    public boolean isNewBool() {
        return newBool;
    }
    public void setNewBool(boolean newBool) {
        this.newBool = newBool;
    }
    public void setBool(boolean bool) {
        this.bool = bool;
    }
    public void setCardName(String cardName) {
        CardName = cardName;
    }


    @Override
    public String toString() {
        return CardName;
    }
}
