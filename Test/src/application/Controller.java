package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class Controller implements Initializable{
	@FXML
	private TableView<Product> table;
	@FXML
	private TableColumn<Product, String> col1;
	@FXML
	private TableColumn<Product, String> col2;
	@FXML
	private TableColumn<Product, String> col3;
	@FXML
	private TableColumn<Product, String> col4;
	@FXML
	private TableColumn<Product, String> col5;
	@FXML
	private TableColumn<Product, String> col6;
	@FXML
	private TableColumn<Product, String> col7;
	@FXML
	private Button button;
	@FXML
	private CheckBox chck;
	
	private ObservableList<Product> data;
	
	public void initialize(){
		col1.setCellValueFactory(new PropertyValueFactory<>("name"));
		col2.setCellValueFactory(new PropertyValueFactory<>("item"));
		col3.setCellValueFactory(new PropertyValueFactory<>("pin"));
		col4.setCellValueFactory(new PropertyValueFactory<>("print"));
		col5.setCellValueFactory(new PropertyValueFactory<>("media"));
		col6.setCellValueFactory(new PropertyValueFactory<>("liner"));
		col7.setCellValueFactory(new PropertyValueFactory<>("rate"));
	}
	
	public void loadTable(){
		data = FXCollections.observableArrayList();
		data.add(new Product("Abcd","xyz","2.33","3.44","1.3","2.3","1.5"));
		data.add(new Product("Abcd","xyz","2.33","3.44","1.3","2.3","1.5"));
		data.add(new Product("Abcd","xyz","2.33","3.44","1.3","2.3","1.5"));
		data.add(new Product("Abdadcd","xyz","2.33","3.44","1.3","2.3","1.5"));
		table.setItems(data);
	}
	
	public void buttonClick(){
		System.out.println("dadadada");
		initialize();
		loadTable();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initialize();
		loadTable();
		table.setEditable(true);
		//col1.setEditable(true);
		col1.setCellFactory(TextFieldTableCell.forTableColumn());
		col1.setOnEditCommit(
	            new EventHandler<CellEditEvent<Product, String>>() {
	                @Override
	                public void handle(CellEditEvent<Product, String> t) {
	                    ((Product) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setName(t.getNewValue());
	                    System.out.println(t.getTableView().getSelectionModel().getSelectedItem().getRate());
	                }
	            }
	        );
		chck.setOnAction((event) -> {
			System.out.println("CheckBox");
		});
	}
}
