package view;

import controller.ArtController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Line;
import model.Oval;
import model.Point;

public class ArtView extends Application {
    public static final int WIN_WIDTH = 1000;
    public static final int WIN_HEIGHT = 600;
    public static final int SHAPE_ICON_SIZE = 20;
    public static final int MAX_STROKE = 20;
    public static final int MIN_STROKE = 1;

    //drawing on the canvas
//    private Canvas canvas = new Canvas(300, 250);
    private Canvas canvas = new Canvas(WIN_WIDTH, WIN_HEIGHT);

    //selecting shapes
    private ToggleGroup shapeGroup;

    //shape settings
    private Oval oval;
    private Line line;
    //private RectangleModel rect;

    private ColorPicker fillColorPicker = new ColorPicker();
    private ColorPicker strokeColorPicker = new ColorPicker();
    private Slider strokeSlider;
    private CheckBox filledCheckbox;
    private double xbegin = 0;
    private double ybegin = 0;
    private double xend = 0;
    private double yend = 0;
    ArtController controller = new ArtController();

    private GraphicsContext graphics;
    //= canvas.getGraphicsContext2D();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Doodle Program");
        stage.setScene(getPrimaryScene());
        stage.show();

        //drawShapes(graphics);
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

    private void drawShapes(GraphicsContext graphics) {
        graphics.setFill(Color.GREEN);
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
        shapeGroup = new ToggleGroup();

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

        for (int i = 0; i < edits.length; i++) {
            buttons[i] = getImageButton(edits[i]);
        }
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
            //System.out.println(text + " pressed");
            switch (text) {
                case "Line":
                    canvas.setOnMousePressed(c -> {
                        xbegin = c.getX();
                        ybegin = c.getY();
                        //line
                        //graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        graphics.beginPath();
                        graphics.lineTo(xbegin, ybegin);
                        graphics.stroke();
                    });

                    canvas.setOnMouseDragged(c -> {
                        //Line
                        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        graphics.lineTo(c.getX(), c.getY());
                        graphics.strokeLine(10, 10, c.getX(), c.getY());
                    });

                    canvas.setOnMouseReleased(c -> {
//                        Point point1 = new Point(xbegin, ybegin);
//                        Point point2 = new Point(xend, yend);
//
//                        controller.handleAddShape(Color.BLUE, point1, point2);
//                        controller.viewShapes(graphics);
//
//                        graphics.closePath();
                    });

                    System.out.println(text + " pressed");
                    break;
                case "Oval":
                    canvas.setOnMousePressed(c -> {
                        xbegin = c.getX();
                        ybegin = c.getY();
                        //oval
                        graphics.beginPath();
                    });

                    canvas.setOnMouseDragged(c -> {
                        //oval
                        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                        xend = c.getX();
                        yend = c.getY();
                        Point point1 = new Point(xbegin, ybegin);
                        Point point2 = new Point(xend, yend);

                        controller.handleAddShape(Color.BLUE, point1, point2);
                        controller.viewShapes(graphics);
                        controller.removeLastShape();
                    });

                    canvas.setOnMouseReleased(c -> {
                        Point point1 = new Point(xbegin, ybegin);
                        Point point2 = new Point(xend, yend);

                        controller.handleAddShape(Color.BLUE, point1, point2);
                        controller.viewShapes(graphics);

                        graphics.closePath();
                    });
                    System.out.println(text + " pressed");
                    break;
                case "Rectangle":
                    canvas.setOnMousePressed(c -> {
                        xbegin = c.getX();
                        ybegin = c.getY();

                        //rectangle
                        graphics.beginPath();
                    });

                    canvas.setOnMouseDragged(c -> {

                        //rectangle
                        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        graphics.strokeRect(0, 0, c.getX(), c.getY());

                    });

                    canvas.setOnMouseReleased(c -> {
//                        Point point1 = new Point(xbegin, ybegin);
//                        Point point2 = new Point(xend, yend);
//
//                        controller.handleAddShape(Color.BLUE, point1, point2);
//                        controller.viewShapes(graphics);
//
//                        graphics.closePath();
                    });

                    System.out.println(text + " pressed");
                    break;
                case "Squiggle":
                    canvas.setOnMousePressed(c -> {
                        xbegin = c.getX();
                        ybegin = c.getY();

                        // squiggle
                        graphics.beginPath();
                        graphics.lineTo(c.getX(), c.getY());
                        graphics.stroke();
                    });

                    canvas.setOnMouseDragged(c -> {

                        //squiggle
                        graphics.lineTo(c.getX(), c.getY());
                        graphics.stroke();
                    });

                    canvas.setOnMouseReleased(c -> {
//                        Point point1 = new Point(xbegin, ybegin);
//                        Point point2 = new Point(xend, yend);
//
//                        controller.handleAddShape(Color.BLUE, point1, point2);
//                        controller.viewShapes(graphics);
//
//                        graphics.closePath();
                    });

                    System.out.println(text + " pressed");
                    break;
                default:
                    System.out.println("not a line");
                    break;
            }
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
    }

    private void drawMenu(Menu draw) {
        Menu shapesMenu = new Menu("Shape Tools");
        MenuItem[] shapes = {new MenuItem("Line"), new MenuItem("Oval"),
                new MenuItem("Rectangle"), new MenuItem("Squiggle")};
        shapesMenu.getItems().addAll(shapes);
        draw.getItems().add(shapesMenu);

        MenuItem clear = new MenuItem("Clear Shapes");
        draw.getItems().add(clear);
    }


    private void help(Menu about) {
        MenuItem[] items = {new MenuItem("About")};
        about.getItems().addAll(items);
    }
}
