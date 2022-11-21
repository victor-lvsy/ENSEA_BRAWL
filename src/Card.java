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
    private final ImageView fondCarte = new ImageView(new Image("file:ImageJeu/fondCarte.png"));

    public Card() {
        pane.getChildren().add(cardImgView);
        pane.getChildren().add(fondCarte);
        pane.getChildren().add(attack);
        pane.getChildren().add(health);
        pane.getChildren().add(name);
        pane.getChildren().add(shopLevel);
        pane.getChildren().add(button);
        Font font = new Font("Verdana", 20);
        shopLevel.setFont(font);
        health.setFont(font);
        attack.setFont(font);
        Font fontName = new Font("Verdana", 14);
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
        button.setVisible(false);
        fondCarte.setVisible(false);
        attack.setVisible(false);
        health.setVisible(false);
        name.setVisible(false);
        shopLevel.setVisible(false);
    }

    public void entiere(){
        button.setVisible(true);
        fondCarte.setVisible(true);
        attack.setVisible(true);
        health.setVisible(true);
        name.setVisible(true);
        shopLevel.setVisible(true);
    }

    public void update(){
        name.setText(CardName);
        cardImgView.setImage(new Image("file:ImageJeu/" + CardName + ".png"));
        cardImgView.setX(13);
        cardImgView.setY(54);
        shopLevel.setX(23);
        shopLevel.setY(35);
        attack.setX(16-(int)attack.getText().length()*6.2);
        attack.setY(309);
        health.setX(184-(int)health.getText().length()*6.2);
        health.setY(309);
        name.setX(47);
        name.setY(33);
        button.setTranslateX(50);
        button.setTranslateY(150);
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
