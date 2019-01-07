import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONException;

import javax.swing.plaf.synth.SynthInternalFrameUI;
import java.io.IOException;


public class GUI extends Application {
    XYChart.Series series;


    public Server server;

    {
        try {
            server = new Server(808);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Client client;

    {
        try {
            client = new Client("192.168.43.147", 808);
            server.Connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    LineChart<Number,Number> lineChartDepth;

    LineChart<Number,Number> lineChartCountObject;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GSON");
        primaryStage.setFullScreen(true);
        final NumberAxis xAxisDepth=new NumberAxis();
        final NumberAxis yAxisDepth=new NumberAxis();

        xAxisDepth.setLabel("Cтепень вложенности");
        yAxisDepth.setLabel("Время выполнения, нc");

        final NumberAxis xAxisCountObject=new NumberAxis();
        final NumberAxis yAxisCountObject=new NumberAxis();

        xAxisCountObject.setLabel("Колличетво объектов");
        yAxisCountObject.setLabel("Время выполнения, нc");

        lineChartDepth=new LineChart<>(xAxisDepth,yAxisDepth);
        lineChartCountObject=new LineChart<>(xAxisCountObject,yAxisCountObject);

        lineChartDepth.setTitle("Dependence Time on Depth");
        lineChartCountObject.setTitle("Dependence Time on CountObjects");


        lineChartDepth.setVisible(false);
        lineChartDepth.setPrefSize(660,720);
        lineChartDepth.setLayoutX(0);
        lineChartDepth.setLayoutY(0);

        lineChartCountObject.setVisible(false);
        lineChartCountObject.setPrefSize(660,720);
        lineChartCountObject.setLayoutX(650);
        lineChartCountObject.setLayoutY(0);

        Label CreateGraphik = new Label("Сформировать график:");
        CreateGraphik.setLayoutX(650);
        CreateGraphik.setLayoutY(730);

        Button buttonCount=new Button("По колличеству объектов");
        buttonCount.setLayoutX(1000);
        buttonCount.setLayoutY(730);

        Button buttonDepth=new Button("По колличеству узлов");
        buttonDepth.setLayoutX(800);
        buttonDepth.setLayoutY(730);


        TextField Depth = new TextField("10");
        Depth.setLayoutX(100);
        Depth.setLayoutY(730);
        Depth.setPrefSize(50,20);

        Label DepthInfo = new Label("Depth:");
        DepthInfo.setLayoutX(50);
        DepthInfo.setLayoutY(730);


        TextField CountObject = new TextField("40");
        CountObject.setLayoutX(250);
        CountObject.setLayoutY(730);
        CountObject.setPrefSize(50,20);

        Label CountObjectInfo = new Label("CountObject");
        CountObjectInfo.setLayoutX(170);
        CountObjectInfo.setLayoutY(730);


        TextField CountJson = new TextField("40");
        CountJson.setLayoutX(400);
        CountJson.setLayoutY(730);
        CountJson.setPrefSize(50,20);

        Label CountJsonInfo = new Label("CountJson");
        CountJsonInfo.setLayoutX(330);
        CountJsonInfo.setLayoutY(730);




        Group group=new Group();
        group.getChildren().add(lineChartDepth);
        group.getChildren().add(lineChartCountObject);
        group.getChildren().add(buttonDepth);
        group.getChildren().add(buttonCount);
        group.getChildren().add(Depth);
        group.getChildren().add(DepthInfo);
        group.getChildren().add(CountObject);
        group.getChildren().add(CountObjectInfo);
        group.getChildren().add(CountJson);
        group.getChildren().add(CountJsonInfo);
        group.getChildren().addAll(CreateGraphik);

        buttonCount.setOnAction(event -> {
            try {
                lineChartCountObject.getData().clear();
                testCountObject(Integer.parseInt(Depth.getCharacters().toString()),Integer.parseInt(CountObject.getCharacters().toString()),
                        Integer.parseInt(CountJson.getCharacters().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        buttonDepth.setOnAction(event -> {try {
            lineChartDepth.getData().clear();

            testDepth(Integer.parseInt(Depth.getCharacters().toString()),Integer.parseInt(CountObject.getCharacters().toString()),
                    Integer.parseInt(CountJson.getCharacters().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        });

        Scene scene=new Scene(group,1366,728);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
    void testDepth(int depth,int countObject,int countJson) throws IOException, JSONException {

        for (int j=1;j!=countJson+1;j++)
        {
            XYChart.Series series =new XYChart.Series();
            for (int i = 1; i != depth; i++) {
                client.WriteJson(countObject, i);
                server.ReadJson();
                series.getData().add(new XYChart.Data(i, Server.time));
            }
           series.setName("Count Json files: "+j);
            lineChartDepth.getData().addAll(series);

        }
        lineChartDepth.setVisible(true);

    };

    void testCountObject(int depth,int countObject,int countJson) throws IOException, JSONException {


        for (int j=1;j!=countJson+1;j++)
        {
            XYChart.Series series =new XYChart.Series();
            for (int i = 1; i != countObject; i++) {
                client.WriteJson(i, depth);
                server.ReadJson();
                series.getData().add(new XYChart.Data(i, Server.time));
            }
            series.setName("Count obj: "+j);
            lineChartCountObject.getData().addAll(series);

        }
        lineChartCountObject.setVisible(true);

    };

    public static void main(String[] args) throws IOException, JSONException {

        launch(args);
    }
}
