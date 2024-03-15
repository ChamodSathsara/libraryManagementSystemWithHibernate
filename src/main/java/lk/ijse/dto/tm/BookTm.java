package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lk.ijse.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookTm {
    private int bookId;
    private String bookName;
    private String author;
    private int week2Price;
    private int week1Price;
    private String genre;
    private String availability;
    private int branchId;
    private Button btn;

    public BookTm(int bookId, String bookName, String author, int week2Price, int week1Price, String genre, String availability, Button btn) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.week2Price = week2Price;
        this.week1Price = week1Price;
        this.genre = genre;
        this.availability = availability;
        this.btn = btn;
    }
}
