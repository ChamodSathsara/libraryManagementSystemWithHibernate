package lk.ijse.service;

import lk.ijse.dto.BookDto;
import lk.ijse.dto.BranchDto;

import java.util.List;

public interface BookService {
    boolean saveBook(BookDto bookDto);
    BookDto getBook(int id);
    boolean updateBook(BookDto bookDto);
    boolean deleteBook(BookDto bookDto);

    List<BookDto> getAllBook();
    List<BookDto> getSelectedBooks(int id);
    int getCount(int id);

    int getBookPk(String bookName);
}
