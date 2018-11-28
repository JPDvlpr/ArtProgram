package view;

import controller.ArtController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Point;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * Displays the GUI doodle application for
 * the user to interact with.
 */
public class ArtView extends Application {
    private static final int WIN_WIDTH = 1000;
    private static final int WIN_HEIGHT = 600;
    private static final int SHAPE_ICON_SIZE = 20;
    private static final int MAX_STROKE = 20;
    private static final int MIN_STROKE = 1;
    private static final int UNDO = 0;
    private static final int REDO = 1;

    //drawing on the canvas
    private Canvas canvas = new Canvas(WIN_WIDTH, WIN_HEIGHT);

    //shape settings
    private ColorPicker fillColorPicker = new ColorPicker();
    private ColorPicker strokeColorPicker = new ColorPicker();
    private Slider strokeSlider;
    private CheckBox filledCheckbox;
    private ArtController controller = new ArtController();
    private GraphicsContext graphics;
    private ArrayList<Point> point = new ArrayList<>();

    /**
     * starts the scene and gives default value
     * to the shape
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Doodle Program");
        stage.setScene(getPrimaryScene());
        stage.show();

        graphics = canvas.getGraphicsContext2D();
        graphics.setStroke(Color.BLUE);
        graphics.setLineWidth(5);
    }

    private Scene getPrimaryScene() {
        BorderPane mainPanel = new BorderPane();

        VBox top = new VBox();
        top.getChildren().addAll(buildMenu(), getToolbar());

        //set the primary regions
        mainPanel.setTop(top);
        mainPanel.setCenter(getCanvas());

        Scene scene = new Scene(mainPanel, WIN_WIDTH, WIN_HEIGHT);

        scene.getStylesheets().add("styles.css");

        return scene;
    }

    private Parent getToolbar() {
        HBox panel = new HBox();
        panel.setId("toolbar-main");
        panel.getChildren().addAll(buildShapeSection(), buildSettings(), buildEdit());

        return panel;
    }

    private HBox buildShapeSection() {
        HBox shapesPanel = new HBox();
        shapesPanel.setId("toolbar-shapes");

        String[] shapes = {"Line", "Oval", "Rectangle", "Squiggle"};
        ToggleButton[] buttons = new ToggleButton[shapes.length];

        //selecting shapes
        ToggleGroup shapeGroup = new ToggleGroup();

        for (int i = 0; i < shapes.length; i++) {
            buttons[i] = getImageToggleButton(shapes[i]);
        }

        buttons[0].setSelected(true);
        shapeGroup.getToggles().addAll(buttons);
        shapesPanel.getChildren().addAll(buttons);

        return shapesPanel;
    }

    private HBox buildSettings() {
        HBox settingsPanel = new HBox();
        settingsPanel.setId("toolbar-settings");

        styleColorPicker(fillColorPicker);
        styleColorPicker(strokeColorPicker);

        VBox strokeBox = new VBox();
        Label strokeLabel = new Label("Stroke: 1");
        strokeSlider = new Slider();
        strokeBox.getChildren().addAll(strokeSlider, strokeLabel);

        strokeSlider.setMin(MIN_STROKE);
        strokeSlider.setMax(MAX_STROKE);
        strokeSlider.valueProperty().addListener((observable, oldV, newV) ->
                strokeLabel.setText("Stroke: " + numToInt(newV)));

        filledCheckbox = new CheckBox("Filled");

        settingsPanel.getChildren().addAll(new Label("Fill:"), fillColorPicker,
                new Label("Stroke:"), strokeColorPicker, strokeBox, filledCheckbox);

        return settingsPanel;
    }

    private void styleColorPicker(ColorPicker picker) {
        picker.getStyleClass().add(ColorPicker.STYLE_CLASS_BUTTON);
        picker.setValue(Color.BLACK);
    }

    private int numToInt(Number value) {
        return (int) Math.floor(value.doubleValue());
    }

    private HBox buildEdit() {
        HBox editPanel = new HBox();
        editPanel.setId("toolbar-edits");

        String[] edits = {"undo", "redo"};
        Button[] buttons = new Button[edits.length];

        buttons[UNDO] = getImageButton(edits[UNDO]);
        buttons[REDO] = getImageButton(edits[REDO]);

        buttons[UNDO].setOnAction(x -> {
            controller.undo(graphics);
            graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            controller.viewShapes(graphics);
        });

        buttons[REDO].setOnAction(x -> controller.redoLastShape(graphics));

        editPanel.getChildren().addAll(buttons);
        return editPanel;
    }

    private ImageView getButtonIcon(String text) {
        ImageView image = new ImageView(text + ".png");
        image.setFitHeight(SHAPE_ICON_SIZE);
        image.setFitWidth(SHAPE_ICON_SIZE);
        return image;
    }

    private ToggleButton getImageToggleButton(String text) {
        ToggleButton result = new ToggleButton();
        result.setGraphic(getButtonIcon(text));

        result.setOnAction(e -> {

            canvas.setOnMousePressed(c -> {
                point.clear();
                point.add(new Point(c.getX(), c.getY()));
            });

            canvas.setOnMouseDragged(c -> {
                graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                point.add(new Point(c.getX(), c.getY()));
                controller.handleAddShape(text, point, fillColorPicker.getValue(), strokeColorPicker.getValue(), strokeSlider.getValue(), filledCheckbox.isSelected());
                controller.viewShapes(graphics);
                controller.removeLastShape();
            });

            canvas.setOnMouseReleased(c -> {
                point.add(new Point(c.getX(), c.getY()));
                controller.handleAddShape(text, point, fillColorPicker.getValue(), strokeColorPicker.getValue(), strokeSlider.getValue(), filledCheckbox.isSelected());
                controller.viewShapes(graphics);
            });
        });
        return result;
    }

    private Button getImageButton(String text) {
        Button result = new Button();
        result.setGraphic(getButtonIcon(text));
        return result;
    }

    private Parent getCanvas() {
        VBox box = new VBox();
        canvas = new Canvas();
        canvas.setStyle("-fx-background-color: black");
        canvas.widthProperty().bind(box.widthProperty());
        canvas.heightProperty().bind(box.heightProperty());
        box.getChildren().add(canvas);
        return box;
    }

    private MenuBar buildMenu() {
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu draw = new Menu("Draw");
        Menu help = new Menu("Help");

        fileMenu(file);
        editMenu(edit);
        drawMenu(draw);
        help(help);

        menuBar.getMenus().addAll(file, edit, draw, help);
        return menuBar;
    }

    private void fileMenu(Menu file) {
        MenuItem[] items = {new MenuItem("Quit")};
        file.getItems().addAll(items);

        // Set Accelerator for Exit MenuItem.
        items[0].setAccelerator(KeyCombination.keyCombination("Ctrl+X"));

        // When user click on the Exit item.
        items[0].setOnAction(event -> System.exit(0));
    }

    private void editMenu(Menu edit) {
        MenuItem[] items = {new MenuItem("Undo"), new MenuItem("Redo")};
        edit.getItems().addAll(items);

        items[UNDO].setOnAction(x -> {
            controller.undo(graphics);
            graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            controller.viewShapes(graphics);
        });

        items[REDO].setOnAction(x -> controller.redoLastShape(graphics));
    }

    private void drawMenu(Menu draw) {
        Menu shapesMenu = new Menu("Shape Tools");
        MenuItem[] shapes = {new MenuItem("Line"), new MenuItem("Oval"),
                new MenuItem("Rectangle"), new MenuItem("Squiggle")};
        shapesMenu.getItems().addAll(shapes);
        draw.getItems().add(shapesMenu);

        for (int i = 0; i < shapes.length; i++) {

            int finalI = i;
            shapes[i].setOnAction(event -> getImageToggleButton(shapes[finalI].getText()).fire());
        }

        MenuItem clear = new MenuItem("Clear Shapes");
        draw.getItems().add(clear);

        clear.setOnAction(event -> {
            controller.clearShapeList();
            graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            controller.viewShapes(graphics);
        });
    }

    private void help(Menu about) {
        MenuItem[] items = {new MenuItem("About")};
        about.getItems().addAll(items);

        about.setOnAction(event -> {
            Desktop desktop = Desktop.getDesktop();
            Hyperlink message = new Hyperlink();
            message.setText("mailto:jpappoe@mail.greenriver.edu?subject=Nice%20App");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Doodle App Using Design Patterns");
            alert.setHeaderText("Doodle App Version 1.0");
            alert.setContentText("Created solely by: Jhakon Pappoe " + "<" +
                    message.getText().substring(0, 34) + ">");
            alert.showAndWait();

            message.setOnAction(click -> {
                URI uri = URI.create(message.getText());
                try {
                    desktop.mail(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}