package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {
	
	@FXML
	private Button lets_go;
	
	
	public void nextpage() throws IOException {
		
		lets_go.getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
		
		
        Stage stage = new Stage();
        		
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
