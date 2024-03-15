package lk.ijse.embadded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class BarrowPrimaryKey implements Serializable {
    @Column(name = "user_name")
    private String userName;

    @Column(name = "book_id")
    private int bookId;



}
