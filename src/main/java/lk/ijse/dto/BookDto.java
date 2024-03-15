package lk.ijse.dto;


import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.service.BranchService;
import lk.ijse.service.impl.BranchServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor@Data
public class BookDto {
    private int bookId;
    private String bookName;
    private String author;
    private String genre;
    private String availability;
    private int week2Price;
    private int week1Price;
    private Branch branch;

    public Book toEntity() {
        Book book = new Book();
        book.setBookId(this.bookId);
        book.setBookName(this.bookName);
        book.setAuthor(this.author);
        book.setGenre(this.genre);
        book.setAvailability(this.availability);
        book.setWeek2Price(this.week2Price);
        book.setWeek1Price(this.week1Price);
        book.setBranch(this.branch);

        return book;
    }
}
