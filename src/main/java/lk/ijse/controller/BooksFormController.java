package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.tm.BookTm;
import lk.ijse.service.BookService;
import lk.ijse.service.impl.BookServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class BooksFormController implements Initializable {
    @FXML
    private AnchorPane bookPane;

    @FXML
    private Label txtBookCatagories;

    @FXML
    private TableView<BookTm> tblBooks;

    @FXML
    private TableColumn<?, ?> collBookId;

    @FXML
    private TableColumn<?, ?> collBookTopic;

    @FXML
    private TableColumn<?, ?> collBookAuthor;

    @FXML
    private TableColumn<?, ?> collBarrowPrice2Week;

    @FXML
    private TableColumn<?, ?> collBarrowPrice1Week;

    @FXML
    private TableColumn<?, ?> collGenre;

    @FXML
    private TableColumn<?, ?> collAvailability;

    @FXML
    private TableColumn<?, ?> collBarrow;

    public static int selectedBook;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {
        BookService bookService = BookServiceImpl.getInstance();

        try{
            List<BookDto> bookDtoList = bookService.getSelectedBooks(UserDashboardFormController.cetegoryId);
            ObservableList<BookTm> obList = FXCollections.observableArrayList();

            for (BookDto bookDto : bookDtoList){
                Button btn = new Button("Barrow");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) ->{
                    ButtonType yes = new ButtonType("yes",ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no" , ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,"Are you sure Barrow Book ?",yes,no).showAndWait();

                    if(type.orElse(no)==yes){
                        int selectedIndex = tblBooks.getSelectionModel().getSelectedIndex();
                        int id = (int) collBookId.getCellData(selectedIndex);

                        getBarrowBook(id);
                    }
                });
                BookTm bookTm = new BookTm(
                        bookDto.getBookId()
                        ,bookDto.getBookName()
                        ,bookDto.getAuthor()
                        ,bookDto.getWeek2Price()
                        ,bookDto.getWeek1Price()
                        ,bookDto.getGenre()
                        ,bookDto.getAvailability(),btn);

                obList.add(bookTm);
            }
            tblBooks.setItems(obList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getBarrowBook(int id) {
        selectedBook = id;
        Parent rootNode= null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/barrowBook_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        stage.setTitle("Barrow Books");
        stage.centerOnScreen();
        stage.setScene(scene);

    }

    private void setCellValueFactory() {
        collBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        collBookTopic.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        collBookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        collBarrowPrice2Week.setCellValueFactory(new PropertyValueFactory<>("week2Price"));
        collBarrowPrice1Week.setCellValueFactory(new PropertyValueFactory<>("week1Price"));
        collGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        collAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        collBarrow.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }
}
