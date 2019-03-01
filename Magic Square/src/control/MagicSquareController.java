package control;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import model.IllegalOrderException;
import model.MagicSquare;

public class MagicSquareController {

    private String startingSide;

    private final String NORTH = "North";
    private final String EAST = "East";
    private final String SOUTH = "South";
    private final String WEST = "West";

    @FXML
    public VBox vBox;

    @FXML
    public StackPane mainPane;

    @FXML
    public TextField orderTextField;

    @FXML
    public ChoiceBox<String> startingPosChoBox;

    @FXML
    public ChoiceBox orientationChoBox;

    @FXML
    private GridPane settingsGrid;

    @FXML
    public Label oriLabel;

    @FXML
    public Button button;


    @FXML
    void mouseReleased() {

        startingPosChoBox.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                    switch (newValue) {
                        case "North":
                            orientationChoBox.setItems(FXCollections.observableArrayList(
                                    "NW", "NE")
                            );
                            startingSide = NORTH;
                            break;
                        case "East":
                            orientationChoBox.setItems(FXCollections.observableArrayList(
                                    "NE", "SE")
                            );
                            startingSide = EAST;
                            break;
                        case "South":
                            orientationChoBox.setItems(FXCollections.observableArrayList(
                                    "SE", "SW")
                            );
                            startingSide = SOUTH;
                            break;
                        case "West":
                            orientationChoBox.setItems(FXCollections.observableArrayList(
                                    "SW", "NW")
                            );
                            startingSide = WEST;
                            break;
                    }
                });
    }

    @FXML
    void checkMouseClicks() {
        if (startingPosChoBox.getValue() != null) {
            orientationChoBox.setOpacity(1.0);
            orientationChoBox.setDisable(false);
            oriLabel.setOpacity(1.0);
        }
    }

    private GridPane squareGrid = new GridPane();

    @FXML
    void generate() {
        switch (button.getText()) {

            case "Generate":

                MagicSquare mS = new MagicSquare();

                int order = Integer.parseInt(orderTextField.getText());

                try {
                    mS.generateSquare(order, startingSide, String.valueOf(orientationChoBox.getValue()));
                } catch (IllegalOrderException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    alert.showAndWait();
                    break;
                }

                mainPane.getChildren().clear();
                mainPane.getChildren().addAll(squareGrid);

                squareGrid.getRowConstraints().clear();
                squareGrid.getColumnConstraints().clear();

                for (int row = 0; row < order; row++) {
                    RowConstraints rc = new RowConstraints();
                    rc.setFillHeight(true);
                    rc.setVgrow(Priority.ALWAYS);
                    squareGrid.getRowConstraints().add(rc);
                }

                for (int col = 0; col < order; col++) {
                    ColumnConstraints cc = new ColumnConstraints();
                    cc.setFillWidth(true);
                    cc.setHgrow(Priority.ALWAYS);
                    cc.setMaxWidth(30.0);
                    cc.setPrefWidth(30.0);
                    cc.setMinWidth(30.0);
                    squareGrid.getColumnConstraints().add(cc);
                }

                for (int i = 0; i < order * order; i++) {
                    Label label = new Label(Integer.toString(mS.getNumber(i % order, i / order)));
                    label.setTextAlignment(TextAlignment.CENTER);
                    squareGrid.add(label, i % order, i / order);
                }
                StackPane.setAlignment(squareGrid, Pos.CENTER_LEFT);
                button.setText("Back to Settings");
                break;

            case "Back to Settings":
                mainPane.getChildren().clear();
                mainPane.getChildren().addAll(settingsGrid);
                squareGrid.getChildren().clear();
                button.setText("Generate");
                break;
        }
    }
}