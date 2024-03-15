package lk.ijse.entity;

import lk.ijse.dto.BookDto;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "availability")
    private String availability;

    @Column(name = "week2Price")
    private int week2Price;

    @Column(name = "week1Price")
    private int week1Price;

    @ManyToOne
    @JoinColumn(
            name = "branch_id",
            referencedColumnName = "branch_id",
            updatable = false,
            insertable = false
    ) private Branch branch;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY , mappedBy = "book")
    private List<Barrow> barrowList;

    public Book() {
    }

    public Book(int bookId, String bookName, String author, String genre, String availability, int week2Price, int week1Price, Branch branch) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.week2Price = week2Price;
        this.week1Price = week1Price;
        this.branch = branch;
    }
    public BookDto toDto(){
        BookDto book = new BookDto();
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
