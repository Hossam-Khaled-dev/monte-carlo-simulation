package MonteCarloView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartPage extends Application {
    
    private final GridPane gridPane = new GridPane();
    private final Label demandLabel = new Label("Demand");
    private final TextField demandTextField = new TextField();
    private final Button proceedButton = new Button("Proceed");
    
    @Override
    public void start(Stage primaryStage) {
        
        Insets insets = new Insets(100);
        
        gridPane.setPadding(insets);
        gridPane.setVgap(20);
        gridPane.add(demandLabel, 0, 0);
        gridPane.add(demandTextField, 0, 1);
        gridPane.add(proceedButton, 0, 2);
        
        Scene scene = new Scene(gridPane);
        
        proceedButton.setOnAction(e -> {
            
            CompleteRandom completeRandom = new CompleteRandom();
            
            if(!demandTextField.getText().equals("")) {
                
                primaryStage.close();
                
                completeRandom.setDemandData(Integer.parseInt(demandTextField.getText()));
                completeRandom.start(primaryStage);
                
            }
            
            else {
                
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Empty number");
                errorAlert.setContentText("Enter number of demand");
                errorAlert.showAndWait();
                
            }
            
        });
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Start Page");
        primaryStage.show();
    
    }
    
}
