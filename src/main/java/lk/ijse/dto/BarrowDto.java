package lk.ijse.dto;

import lk.ijse.embadded.BarrowPrimaryKey;
import lk.ijse.entity.Book;
import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor@AllArgsConstructor
@Data
public class BarrowDto {
    private BarrowPrimaryKey barrowPrimaryKey;
    private String barrowDate;
    private String returnDate;
    private int fullAmount;
    private User user;
    private Book book;
}
