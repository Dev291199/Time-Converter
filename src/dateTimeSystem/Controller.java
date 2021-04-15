package dateTimeSystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	@FXML
	public Label welcomeLabel;
	@FXML
	public Label dateLabel;
	@FXML
	public TextField dateTextField;
	@FXML
	public Label monthLabel;
	@FXML
	public ChoiceBox monthChoiceBox;
	@FXML
	public Label hourLabel;
	@FXML
	public TextField hourTextField;
	@FXML
	public Label minuteLabel;
	@FXML
	public TextField minuteTextField;
	@FXML
	public ChoiceBox zoneChoiceBox;
	@FXML
	public Button convertButton;


	public static String convertedZone;
	public static int convertedMonth;
	public static String month;
	ZoneId IndiaZone = ZoneId.of("Asia/Kolkata");
	ZoneId CaliforniaZone = ZoneId.of("America/Los_Angeles");

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		zoneChoiceBox.getItems().add("AM");
		zoneChoiceBox.getItems().add("PM");
		zoneChoiceBox.setValue("AM");
		convertedZone = "AM";
		zoneChoiceBox.setOnAction((event) -> {
			 Object zone = zoneChoiceBox.getValue();
			convertedZone = String.valueOf(zone);
			System.out.println("   ChoiceBox.getValue(): " + convertedZone);
	});


		monthChoiceBox.getItems().addAll("January","February","March","April","May","June","July"
											,"August","September","October","November","December");
		monthChoiceBox.setValue("January");
		month = "01";
		monthChoiceBox.setOnAction((event -> {
			convertedMonth = monthChoiceBox.getSelectionModel().getSelectedIndex() + 1;
			System.out.println(convertedMonth);
			month = Integer.toString(convertedMonth);
			if(month.length()==1)
			{
				month = "0"+month;
			}

		}));

		convertButton.setOnAction(event -> {
			convert();
		});
}

	private void convert() {
		String d = dateTextField.getText();
		if(d.length()==1){
			d = "0"+d;
		}
		System.out.println(d);

		String hr = hourTextField.getText();
		if(hr.length()==1){
			hr = "0"+hr;
		}
		System.out.println(hr);

		String m = minuteTextField.getText();
		if(m.length() == 1){
			m = "0"+m;
		}
		System.out.println(m);

		String date;
		String hour;
		String min;

		try{
			date = d;
			hour = hr;
			min = m;
		}catch(Exception exception) {
			warnUser();
			return;
		}
			try {
				String dateTime;
				dateTime = "2021" + "-" + month + "-" + date + " " + hr + ":" + min + ":" + "00" + " " + convertedZone;
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
				LocalDateTime ldt = LocalDateTime.parse(dateTime, dtf);

				ZonedDateTime IndiaTime = ZonedDateTime.of(ldt, IndiaZone);
				DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
				ZonedDateTime CaliforniaDateTime = IndiaTime.withZoneSameInstant(CaliforniaZone);
				DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
				System.out.println("California Date-Time(MM/dd/yyyy)- " + dtf2.format(CaliforniaDateTime));

				display(dtf2.format(CaliforniaDateTime));
			}catch(Exception exception){
				warnUser();
				return;
			}
	}

	private void display(String time) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("\r\n \r\n \r\n Date and Time in PST is       " + time);
		alert.getDialogPane().setMinHeight(300);
		alert.getDialogPane().setMinWidth(500);
		alert.show();
	}

	private void warnUser() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occurred");
		alert.setHeaderText("Invalid data entered");
		alert.setContentText("Please enter the valid data");
		alert.show();
	}
}
