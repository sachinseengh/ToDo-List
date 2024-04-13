package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashBoardController implements Initializable {

	@FXML
	private TextField txt_field;

	@FXML
	private Label error;

	@FXML
	private Button add_btn;

	@FXML
	private Button delete_btn;
	
	@FXML
	private TableView<GetList> tableview;
	
	@FXML
	private TableColumn<GetList, String> col_sn;
	@FXML
	private TableColumn<GetList, String> col_task;
	@FXML
	private TableColumn<GetList, String> col_date;

	@FXML
	private Label Selectedsn;
	

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

	public void add() throws SQLException {
		if (txt_field.getText().equals(" ") || txt_field.getText().trim().isEmpty() || txt_field.getText().isEmpty()) {
			error.setText("Task field Can't be Empty");
			return;
		}
		if (!hasAlphabet(txt_field.getText())) {
			error.setText("Task Must have alphabets");
			return;
		}

		Conn c = new Conn();
		

		String sql2 = "Select count(*) as count_all from list";

		ResultSet rs = null;
		try {
			rs = c.s.executeQuery(sql2);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		int sno = 0;
		try {
			if (rs.next()) {
				sno = rs.getInt("count_all") + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Date date = new Date();
		java.sql.Date workdate = new java.sql.Date(date.getTime());

		String sql = "INSERT INTO [list] ([s-No], [work], [work_date]) VALUES ('" + sno + "', '" + txt_field.getText()
				+ "', '" + workdate + "')";

		int check = c.s.executeUpdate(sql);

		if (check > 0) {
			System.out.println("Done");
		} else {
			System.out.println("Not Done");
		}
		txt_field.setText("");
		showUnverified();
		c.connection.close();
	}

	// Table list
	ObservableList<GetList> listdata;

	public ObservableList<GetList> dataList() {
	    Conn c = new Conn();
	    listdata = FXCollections.observableArrayList();
	    String sql = "select * from list";

	    try {
	        ResultSet result = c.s.executeQuery(sql);
	        int i = 1; // Initialize i to 1
	        GetList data;
	        while (result.next()) {
	            String num = result.getString("S-No"); // Assuming "S-No" is the serial number in the database
	            String work = result.getString("work");
	            String workDate = result.getString("work_date");
	            data = new GetList(String.valueOf(i), num, work, workDate); // Use i as the serial number
	            i++; // Increment i for the next iteration
	            listdata.add(data);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return listdata;
	}


	// To show data
	public void showUnverified(){
		ObservableList<GetList> showList = dataList();
		

		col_sn.setCellValueFactory(new PropertyValueFactory<>("nums"));
		col_task.setCellValueFactory(new PropertyValueFactory<>("Work"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("Work_Date"));
	

		tableview.setItems(showList);

	}

	public void selectUnverified() {

		error.setText("");
		GetList data = tableview.getSelectionModel().getSelectedItem();

		int num = tableview.getSelectionModel().getSelectedIndex();

		if ((num - 1) < -1) {
			Selectedsn.setText("");
			return;
		}else {
		Selectedsn.setText(data.getSNo());
		}
	}

	public void delete() {
		if(Selectedsn.getText().equals("")) {
			error.setText("Task Not Selected");
			return;
		}
		
		Conn c= new Conn();
	
//		String sql = "delete from list where S-No ='"+Selectedsn.getText()+"'";

		String sql = "DELETE FROM list WHERE [s-No] = '"+Selectedsn.getText()+"'";
		try {
			
			int d=c.s.executeUpdate(sql);
			showUnverified();
			Selectedsn.setText("");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showUnverified();
	}

}
