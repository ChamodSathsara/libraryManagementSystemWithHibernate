package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.tm.BookTm;
import lk.ijse.service.BookService;
import lk.ijse.service.BranchService;
import lk.ijse.service.impl.BookServiceImpl;
import lk.ijse.service.impl.BranchServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminbookManageFormController implements Initializable {
    @FXML
    private TextField txtavailability;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtAuther;

    @FXML
    private TextField txt2weekPrice;

    @FXML
    private TextField txt1weekPrice;

    @FXML
    private TextField txtGenre;

    @FXML
    private TableView<BookTm> tblBooks;

    @FXML
    private TableColumn<?, ?> collNo;

    @FXML
    private TableColumn<?, ?> collBookName;

    @FXML
    private TableColumn<?, ?> collAuthor;

    @FXML
    private TableColumn<?, ?> coll2WeekPrice;

    @FXML
    private TableColumn<?, ?> coll1SWeekPrice;

    @FXML
    private TableColumn<?, ?> collGenre;

    @FXML
    private TableColumn<?, ?> collAvailability;

    @FXML
    private TableColumn<?, ?> collCatagory;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TextField txtCatogary;

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String bookName = txtBookName.getText();
        String author = txtAuther.getText();
        int catogory = Integer.valueOf(txtCatogary.getText());
        int week2Price = Integer.valueOf(txt2weekPrice.getText());
        int week1Price = Integer.valueOf(txt1weekPrice.getText());
        String genre = txtGenre.getText();
        String availability = txtavailability.getText();

        BranchService branchService = BranchServiceImpl.getInstance();
        BranchDto branchDto = branchService.getBranch(catogory);

        System.out.println(branchDto);

        BookDto bookDto = new BookDto(0,bookName,author,genre,availability,week2Price,week1Price, branchDto.toEntity());
        BookService bookService = BookServiceImpl.getInstance();
        boolean isSave = bookService.saveBook(bookDto);

        if (isSave){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully save").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something wrong").show();
        }
        tblBooks.refresh();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String bookName = txtBookName.getText();
        BookService bookService = BookServiceImpl.getInstance();
        int pk = bookService.getBookPk(bookName);

        BookDto bookDto = bookService.getBook(pk);
        boolean isDelete = bookService.deleteBook(bookDto);

        if (isDelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updating").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something wrong").show();
        }

//        String bookName = txtBookName.getText();
//
//        BookService bookService = BookServiceImpl.getInstance();
//        int pk = bookService.getBookPk(bookName);
//
//        BookDto bookDto = bookService.getBook(pk);
//        System.out.println(bookDto);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String bookName = txtBookName.getText();

        BookService bookService = BookServiceImpl.getInstance();
        int pk = bookService.getBookPk(bookName);

        String author = txtAuther.getText();
        String catogory = txtCatogary.getText();
        int week2Price = Integer.valueOf(txt2weekPrice.getText());
        int week1Price = Integer.valueOf(txt1weekPrice.getText());
        String genre = txtGenre.getText();
        String availability = txtavailability.getText();

        BranchService branchService = BranchServiceImpl.getInstance();
        int branchPk =  branchService.getPk(catogory);
        BranchDto branchDto = branchService.getBranch(branchPk);

        BookDto bookDto = new BookDto(pk,bookName,author,genre,availability,week2Price,week1Price, branchDto.toEntity());
        boolean isUpdate = bookService.updateBook(bookDto);

        if (isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updating").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something wrong").show();
        }
        tblBooks.refresh();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValue();
        loadAllData();
    }

    private void loadAllData() {
        BookService bookService = BookServiceImpl.getInstance();

        try{
            List<BookDto> bookDtoList = bookService.getAllBook();
            ObservableList<BookTm> obList = FXCollections.observableArrayList();

            for (BookDto bookDto : bookDtoList){
                Button btn = new Button("Delete");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) ->{
                    ButtonType yes = new ButtonType("yes",ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no" , ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,"Are you sure Delete Book ?",yes,no).showAndWait();

                    if(type.orElse(no)==yes){
                        int selectedIndex = tblBooks.getSelectionModel().getSelectedIndex();
                        int id = (int) collNo.getCellData(selectedIndex);

                        deleteBook(id);
                        tblBooks.refresh();
                    }
                });
                BookTm bookTm = new BookTm(
                        bookDto.getBookId()
                        ,bookDto.getBookName()
                        ,bookDto.getAuthor()
                        ,bookDto.getWeek2Price()
                        ,bookDto.getWeek1Price()
                        ,bookDto.getGenre()
                        ,bookDto.getAvailability()
                        ,bookDto.getBranch().getBranchId()
                        ,btn
                );

                obList.add(bookTm);
            }
            tblBooks.setItems(obList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void deleteBook(int id) {
        BookService bookService = BookServiceImpl.getInstance();
        BookDto bookDto = bookService.getBook(id);

        boolean isDelete = bookService.deleteBook(bookDto);
        if (isDelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Delete").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something wrong").show();
        }
        tblBooks.refresh();
    }

    private void setCellValue() {
        collNo.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        collBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        collAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        coll2WeekPrice.setCellValueFactory(new PropertyValueFactory<>("week2Price"));
        coll1SWeekPrice.setCellValueFactory(new PropertyValueFactory<>("week1Price"));
        collGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        collAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        collCatagory.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }
}
