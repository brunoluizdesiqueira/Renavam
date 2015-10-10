package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
   String checkRenavam;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Validador de renavam");
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Adicionando HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20,20,20,30));

        //Adicionando GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //Implementando Nodes para GridPane
        Label lblRenavam = new Label("Renavam");
        final TextField txtRenavam = new TextField();

        Button btnValidar = new Button("Validar");
        Button btnCancelar = new Button("Cancelar");

        final Label lblMessage = new Label();

        //Adicionando Nodes do GridPane layout
        gridPane.add(lblRenavam, 0, 0);
        gridPane.add(txtRenavam, 1, 0);
        gridPane.add(btnValidar, 2, 1);
        gridPane.add(btnCancelar, 3, 1);
        gridPane.add(lblMessage, 1, 2);

        //Reflection para gridPane
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gridPane.setEffect(r);

        //DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        //Adicionar o text e o efeito DropShadow a ele.
        Text text = new Text("Validador de renavam");
        text.setFont(Font.font("Consolas", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);

        //Adicionar texto a HBox.
        hb.getChildren().add(text);

        //Adicionar ID's para os Nodes
        bp.setId("bp");
        gridPane.setId("root");
        btnValidar.setId("btnValidar");
        btnCancelar.setId("btnCancelar");
        text.setId("text");

        //Ação para o  btnValidar
        btnValidar.setOnAction(event -> {
            checkRenavam = txtRenavam.getText();

            ValidadorRenavam validador = new ValidadorRenavam();

            if (validador.validarRenavam(checkRenavam)){
                lblMessage.setText("Renavam valido.");
                lblMessage.setTextFill(Color.GREEN);
            } else {
                lblMessage.setText("Renavam incorreto.");
                lblMessage.setTextFill(Color.RED);
            }
            txtRenavam.setText("");
        });

        //Ação para btnCancelar.
        btnCancelar.setOnAction(event -> primaryStage.close());

        //Adicionar o layout HBox e GridPane para BorderPane layout.
         bp.setTop(hb);
         bp.setCenter(gridPane);

        //Adicionando BorderPane à cena e carregamento CSS.
        Scene scene = new Scene(bp);

        scene.getStylesheets().add("validador.css");

        primaryStage.setScene(scene);
        primaryStage.titleProperty().bind(
                scene.widthProperty().asString().
                        concat(" : ").concat(scene.heightProperty().asString()));

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
