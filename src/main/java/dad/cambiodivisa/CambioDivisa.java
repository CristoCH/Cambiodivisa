package dad.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

    private TextField cantidadOrigen;
    private TextField cantidadDestino;
    private ComboBox<Divisa> comboOrigen;
    private ComboBox<Divisa> comboDestino;
    private Button botonCambiar;

    private Divisa euro = new Divisa("Euro", 1.0);
    private Divisa libra = new Divisa("Libra", 0.8873);
    private Divisa dolar = new Divisa("Dolar", 1.2007);
    private Divisa yen = new Divisa("Yen", 133.59);

    private Divisa [] divisas = {euro, libra, dolar, yen};


    @Override
    public void start(Stage stage) throws Exception {

        cantidadOrigen = new TextField("0");
        cantidadOrigen.setPrefColumnCount(3);
        cantidadDestino = new TextField("0");
        cantidadDestino.setPrefColumnCount(3);
        cantidadDestino.setEditable(false);

        comboOrigen = new ComboBox<>();
        comboOrigen.getItems().addAll(divisas);
        comboOrigen.getSelectionModel().select(euro);
        comboDestino = new ComboBox<>();
        comboDestino.getItems().addAll(divisas);
        comboDestino.getSelectionModel().select(yen);

        botonCambiar = new Button("Cambiar");
        botonCambiar.setOnAction(e-> onCambiarButton(e));

        HBox origenBox = new HBox();
        origenBox.setAlignment(Pos.CENTER);
        origenBox.setSpacing(5);
        origenBox.getChildren().addAll(cantidadOrigen, comboOrigen);

        HBox destinoBox = new HBox();
        destinoBox.setAlignment(Pos.CENTER);
        destinoBox.setSpacing(5);
        destinoBox.getChildren().addAll(cantidadDestino, comboDestino);

        VBox root = new VBox();
        root.setSpacing(5);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(origenBox,destinoBox, botonCambiar);

        Scene scene = new Scene(root, 320, 200);

        stage.setTitle("Cambio de divisa");
        stage.setScene(scene);
        stage.show();

    }

    private void onCambiarButton(ActionEvent e) {
        //System.out.println(destino.fromEuro(origen.toEuro(cantidad))); // convierte 2000 yenes en dólares
        try {
            Double cantidadPrevia = Double.parseDouble(cantidadOrigen.getText());
            Divisa origen = comboOrigen.getSelectionModel().getSelectedItem();
            Divisa destino = comboDestino.getSelectionModel().getSelectedItem();

            Double aEuros = origen.toEuro(cantidadPrevia);
            Double convertido = destino.fromEuro(aEuros);
            cantidadDestino.setText(convertido+"");

        } catch (NumberFormatException er){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Cambio de divisa");
            error.setHeaderText("Error");
            error.setContentText("Introduzca un número valido");
            error.showAndWait();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
