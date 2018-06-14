package ua.nure.veretelnyk;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.xml.sax.SAXException;
import ua.nure.veretelnyk.constants.Constants;
import ua.nure.veretelnyk.entity.Project;
import ua.nure.veretelnyk.entity.Projects;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GUI extends Application {
    private Projects projects;
    private Project currentProject;

    private Image img;
    private Circle circle = new Circle(250,250,80);
    private Text customerName = new Text();
    private Text projectName = new Text();
    private Text price = new Text();
    private Text description = new Text();

    private static final String PRICE = "Price: ";
    private static final String DESCRIPTION = "Description: ";
    private static final String CURRENCY = "$";
    private static final int INITIAL_DELAY = 1;
    private static final int PERIOD = 50;
    private static final int WIDTH = 450;
    private static final int HEIGHT = 600;
    private static final double BOXES_PADDING = 20;
    private static final double ELEMENT_PADDING = 20;
    private static final String ROUND_BTN_STYLE = "-fx-background-radius: 10em; " +
                                                "-fx-min-width: 30px; " +
                                                "-fx-min-height: 30px; " +
                                                "-fx-max-width: 30px; " +
                                                "-fx-max-height: 30px;";
    private static final String PANE_STYLE =    "-fx-background-image: url(" + Constants.IMG_FOLDER +"bg" +Constants.IMG_EXTINSION + ");" +
                                                "-fx-background-repeat: stretch;" +
                                                "-fx-background-size: " + WIDTH +" " +HEIGHT +";" +
                                                "fx-background-position: center center;" +
                                                "-fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        launch(args);
    }
    private void projectsInit() throws Exception{
        DOMController dom = new DOMController(Constants.PROJECTS_XML);
        dom.parse(true);
        projects = dom.getProjects();
        currentProject = projects.next();
    }

    private void dataUpdate(){
        img = new Image(Constants.IMG_FOLDER + currentProject.getCustomer().getId() + Constants.IMG_EXTINSION);

        //circle.setStroke(Color.VIOLET);
        circle.setFill(new ImagePattern(img));

        customerName.setText(currentProject.getCustomer().getName());
        projectName.setText(currentProject.getName());
        price.setText(PRICE + String.valueOf(currentProject.getPrice()) + CURRENCY);
        description.setText(DESCRIPTION + currentProject.getDescription());
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        projectsInit();
        dataUpdate();
        primaryStage.initStyle(StageStyle.UTILITY);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(BOXES_PADDING));
        vBox.setAlignment(Pos.TOP_CENTER);

        Button leftBtn = new Button("<");
        Button rightBtn = new Button(">");
        leftBtn.setStyle(ROUND_BTN_STYLE);
        rightBtn.setStyle(ROUND_BTN_STYLE);
        leftBtn.setOnAction(event -> currentProject = projects.previous());
        rightBtn.setOnAction(event -> currentProject = projects.next());

        HBox imgBox = new HBox();
        imgBox.setAlignment(Pos.CENTER);
        imgBox.setSpacing(ELEMENT_PADDING);
        imgBox.getChildren().addAll(leftBtn, circle, rightBtn);
        vBox.getChildren().addAll(imgBox);

        HBox customerBox = new HBox();
        customerName.setFont(new Font(20));
        customerBox.setPadding(new Insets(BOXES_PADDING));
        customerBox.setAlignment(Pos.CENTER);
        customerBox.getChildren().addAll(customerName);
        vBox.getChildren().addAll(customerBox);

        VBox infoBox = new VBox();
        projectName.setFont(new Font(30));
        price.setFont(new Font(35));
        description.setFont(new Font(20));
        description.setWrappingWidth(WIDTH);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.getChildren().addAll(projectName, price, description);
        vBox.getChildren().addAll(infoBox);

        StackPane root = new StackPane();
        root.getChildren().addAll(vBox);
        primaryStage.setTitle("ProjectManager");
        root.setStyle(PANE_STYLE);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        setUpdateOfMap();
    }
    private void setUpdateOfMap(){
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
                () -> Platform.runLater(() -> {
                    dataUpdate();
                }),
                INITIAL_DELAY,
                PERIOD,
                TimeUnit.MILLISECONDS);
    }

}
