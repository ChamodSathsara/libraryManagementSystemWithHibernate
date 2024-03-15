package lk.ijse.repository;

import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.projection.BookProjection;

import java.util.List;

public interface BookRepository extends CrudRepository<Integer , Book>{
    List<Book> getAllBook();
    List<Book> getSelectedBooks(int id);
    int getCount(int id);

    int getBookPk(String bookName);

    void saveAll(Book book);
}
