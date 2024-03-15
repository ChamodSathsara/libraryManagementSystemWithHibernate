package lk.ijse.entity;

import lk.ijse.embadded.BarrowPrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "barrow")
public class Barrow {
    @EmbeddedId
    private BarrowPrimaryKey barrowPrimaryKey;
    @Column(name = "barrow_date")
    private String barrowDate;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "full_amount")
    private int fullAmount;

    @ManyToOne
    @JoinColumn(
            name = "user_name",
            referencedColumnName = "user_name",
            updatable = false,
            insertable = false
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "book_id",
            referencedColumnName = "book_id",
            insertable = false,
            updatable = false
    )
    private Book book;

}
