package lk.ijse.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookProjection {
    private int bookId;
    private String bookName;
    private String author;
    private int week2Price;
    private int week1Price;
    private String genre;
    private String availability;
}
