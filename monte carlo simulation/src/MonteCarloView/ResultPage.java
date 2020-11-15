package MonteCarloView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import khaledproject.Interval;

public class ResultPage extends Application {
    
    private final GridPane gridPane = new GridPane();
    private final ObservableList <ResultData> data = FXCollections.observableArrayList();
    private final TableView <ResultData> tableView = new TableView();
    private final TableColumn <ResultData, String> probabilities = new TableColumn("Probabilities");
    private final TableColumn <ResultData, String> cumulativeProbabilities = new TableColumn("CumulativeProbabilities");
    private final TableColumn <ResultData, String> intervals = new TableColumn("Intervals");
    private final TableColumn <ResultData, String> x = new TableColumn("X");
    private final TableColumn <ResultData, String> y = new TableColumn("Y");
    private final Label expectedDemandLabel = new Label("Expected Demand");
    private final TextField expectedDemandTextField = new TextField();
    private final Button returnButton = new Button("Return");
    
    public void setTableData(ArrayList <Double> probabilities, ArrayList <Double> cumulativeProbabilities, ArrayList <Interval> intervals) {
        
        NumberFormat formatter = new DecimalFormat("#0.00");
        int size = probabilities.size();
        double ex = 0;
        
        for(int i = 0; i < size; i++) {
            
            ResultData resultData = new ResultData("" + formatter.format(probabilities.get(i)), "" + formatter.format(cumulativeProbabilities.get(i)), "" + formatter.format(intervals.get(i).getX()), "" + formatter.format(intervals.get(i).getY()));

            data.add(resultData);
            
            ex += i * probabilities.get(i);
            
        }
        
        expectedDemandTextField.setText("" + ex);
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        expectedDemandTextField.setEditable(false);
        
        probabilities.impl_setReorderable(false);
        probabilities.setSortable(false);
        probabilities.setEditable(false);
        probabilities.setCellValueFactory(new PropertyValueFactory("Probabilities"));
        
        cumulativeProbabilities.impl_setReorderable(false);
        cumulativeProbabilities.setSortable(false);
        cumulativeProbabilities.setEditable(false);
        cumulativeProbabilities.setCellValueFactory(new PropertyValueFactory("CumulativeProbabilities"));
        
        x.impl_setReorderable(false);
        x.setSortable(false);
        x.setEditable(false);
        x.setCellValueFactory(new PropertyValueFactory("X"));
        
        y.impl_setReorderable(false);
        y.setSortable(false);
        y.setEditable(false);
        y.setCellValueFactory(new PropertyValueFactory("Y"));
        
        intervals.impl_setReorderable(false);
        intervals.setSortable(false);
        intervals.setEditable(false);
        intervals.getColumns().addAll(x, y);
        
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(300);
        tableView.getColumns().addAll(probabilities, cumulativeProbabilities, intervals);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPlaceholder(new Label("No Data"));
        tableView.setItems(data);
        
        Insets insets = new Insets(100);
        
        gridPane.setPadding(insets);
        gridPane.setVgap(20);
        gridPane.add(tableView, 0, 0);
        gridPane.add(expectedDemandLabel, 0, 1);
        gridPane.add(expectedDemandTextField, 0, 2);
        gridPane.add(returnButton, 0, 3);
        
        Scene scene = new Scene(gridPane);
        
        returnButton.setOnAction(e -> {
            
            StartPage startPage = new StartPage();
            
            primaryStage.close();
            
            startPage.start(primaryStage);
            
        });
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Result Page");
        primaryStage.show();
        
    }
    
    public class ResultData {
        
        private final SimpleStringProperty Probabilities;
        private final SimpleStringProperty CumulativeProbabilities;
        private final SimpleStringProperty X;
        private final SimpleStringProperty Y;
        
        public ResultData(String probabilities, String cumulativeProbabilities, String x, String y) {
            
            this.Probabilities = new SimpleStringProperty(probabilities);
            this.CumulativeProbabilities = new SimpleStringProperty(cumulativeProbabilities);
            this.X = new SimpleStringProperty(x);
            this.Y = new SimpleStringProperty(y);
            
        }

        public void setProbabilities(String probabilities) {
            
            Probabilities.set(probabilities);
        
        }
        
        public String getProbabilities() {
            
            return Probabilities.get();
        
        }

        public void setCumulativeProbabilities(String cumulativeProbabilities) {
            
            CumulativeProbabilities.set(cumulativeProbabilities);
        
        }
        
        public String getCumulativeProbabilities() {
            
            return CumulativeProbabilities.get();
        
        }
        
        public void setX(String x) {
            
            X.set(x);
        
        }
        
        public String getX() {
            
            return X.get();
        
        }
        
        public void setY(String y) {
            
            Y.set(y);
        
        }
        
        public String getY() {
            
            return Y.get();
        
        }
        
    }
    
}
