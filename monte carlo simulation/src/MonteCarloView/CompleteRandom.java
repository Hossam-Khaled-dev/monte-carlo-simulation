package MonteCarloView;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import khaledproject.Interval;

public class CompleteRandom extends Application {
    
    private final GridPane gridPane = new GridPane();
    private final ArrayList<Label> labels = new ArrayList<>();
    private final ArrayList<TextField> textFields = new ArrayList<>();
    private final Button useFrequencyButton = new Button("use frequency");
    private final Button useProbabilityButton = new Button("use probability");
    private final ArrayList <Double> probabilities = new ArrayList<>();
    private final ArrayList <Double> cumulativeProbabilities = new ArrayList<>();
    private final ArrayList <Interval> intervals = new ArrayList<>();
    private int demandSize = 0;
    private int frequenciesTotal = 0;
    
    public void setDemandData(int size) {
        
        demandSize = size;
    
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        Insets insets = new Insets(100);
        
        gridPane.setPadding(insets);
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        
        for(int i = 0; i < demandSize; i++) {
            
            labels.add(new Label("" + i));
            textFields.add(new TextField());
            
            gridPane.add(labels.get(i), 0, i);
            gridPane.add(textFields.get(i), 1, i);
        
        }
        
        gridPane.add(useFrequencyButton, 1, demandSize + 1);
        gridPane.add(useProbabilityButton, 1, demandSize + 2);
        
        Scene scene = new Scene(gridPane);
        
        useFrequencyButton.setOnAction(e -> {
            
            boolean emptyTextField = true;
            
            for(int i = 0; i < demandSize; i++) {
                
                if(!textFields.get(i).getText().equals("")) {
                    
                    frequenciesTotal += Integer.parseInt(textFields.get(i).getText());
                    
                }
                
                else {
                    
                    emptyTextField = false;
                    
                }
                
            }
            
            if(emptyTextField) {
                
                for(int i = 0; i < demandSize; i++) {
                    
                    probabilities.add(Double.parseDouble(textFields.get(i).getText()) / frequenciesTotal);
                    
                    double sum = 0;
                    
                    for(int j = 0; j <= i; j++) {
                        
                        sum += probabilities.get(j);
                    
                    }
                    
                    cumulativeProbabilities.add(sum);
                    
                    if(i == 0) {
                        
                        intervals.add(new Interval(1, (int) (cumulativeProbabilities.get(i) * 100)));
                        
                    }
                    
                    else {
                        
                        intervals.add(new Interval((cumulativeProbabilities.get(i - 1) * 100) + 1, cumulativeProbabilities.get(i) * 100));
                        
                    }
                    
                }
                
                ResultPage resultPage = new ResultPage();
                
                primaryStage.close();
                
                resultPage.setTableData(probabilities, cumulativeProbabilities, intervals);
                resultPage.start(primaryStage);
                
            }
            
            else {
                
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Lack in inputs");
                errorAlert.setContentText("Complete the empty fields before continue");
                errorAlert.showAndWait();
                
            }
            
        });
        
        useProbabilityButton.setOnAction(e -> {
            
            boolean emptyTextField = true;
            
            for(int i = 0; i < demandSize; i++) {

                if(!textFields.get(i).getText().equals("")) {
                    probabilities.add(Double.parseDouble(textFields.get(i).getText()));

                    double sum = 0;

                    for(int j = 0; j <= i; j++) {

                        sum += probabilities.get(j);

                    }

                    cumulativeProbabilities.add(sum);

                    if(i == 0) {

                        intervals.add(new Interval(1, (int) (cumulativeProbabilities.get(i) * 100)));

                    }

                    else {

                        intervals.add(new Interval((cumulativeProbabilities.get(i - 1) * 100) + 1, cumulativeProbabilities.get(i) * 100));

                    }
                    
                }
                
                else {
                    
                    emptyTextField = false;
                    
                }

            }

            if(emptyTextField) {
                
                ResultPage resultPage = new ResultPage();

                primaryStage.close();
                
                resultPage.setTableData(probabilities, cumulativeProbabilities, intervals);
                resultPage.start(primaryStage);
                
            }
            
            else {
                
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Lack in inputs");
                errorAlert.setContentText("Complete the empty fields before continue");
                errorAlert.showAndWait();
                
            }
            
        });
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Complete Random Sampling");
        primaryStage.show();
    
    }
    
}
