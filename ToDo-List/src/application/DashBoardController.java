package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DashBoardController implements Initializable {

	@FXML
	private TextField txt_field;
	
	@FXML
	private Label error;

	@FXML
	private Button add_btn;

	@FXML
	private Button delete_btn;
	
	
	
//	Validation
	public Boolean hasAlphabet(String txt) {
		String regex = ".*[a-zA-Z].*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}
	public void clearError() {
		error.setText("");
	}

	public void add() {
		if(txt_field.getText().equals(" ")|| txt_field.getText().trim().isEmpty()
				|| txt_field.getText().isEmpty()) {
			error.setText("Task field Can't be Empty");
			return;
		}
		if(!hasAlphabet(txt_field.getText())) {
			error.setText("Task Must have alphabets");
			return;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
