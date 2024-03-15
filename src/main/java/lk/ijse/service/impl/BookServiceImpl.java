package lk.ijse.service.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.repository.BookRepository;
import lk.ijse.repository.impl.BookRepositoryImpl;
import lk.ijse.service.BookService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookServiceImpl implements BookService {
    private static BookService bookService;
    private Session session;

    private final BookRepository bookRepository;

    private BookServiceImpl(){
        bookRepository = BookRepositoryImpl.getInstance();
    }
    public static BookService getInstance(){
        return bookService == null ? bookService = new BookServiceImpl() : bookService;
    }
    @Override
    public boolean saveBook(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            bookRepository.setSession(session);
            int isSave = bookRepository.save(bookDto.toEntity());
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public BookDto getBook(int id) {
        session = SessionFactoryConfig.getInstance().getSession();
        try {
            bookRepository.setSession(session);
            Book book = bookRepository.get(id);
            return book.toDto();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateBook(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            bookRepository.setSession(session);
            bookRepository.update(bookDto.toEntity());
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteBook(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            bookRepository.setSession(session);
            bookRepository.delete(bookDto.toEntity());
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<BookDto> getAllBook() {
        session = SessionFactoryConfig.getInstance().getSession();
        try{
            bookRepository.setSession(session);
            List<Book> bookList = bookRepository.getAllBook();
            List<BookDto> bookDtoList = null;
            for(Book book : bookList){
                bookDtoList.add(book.toDto());
            }
            return bookDtoList;
        }catch (Exception e ){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<BookDto> getSelectedBooks(int id) {
        session = SessionFactoryConfig.getInstance().getSession();
        try{
            bookRepository.setSession(session);
            List<Book> bookList = bookRepository.getSelectedBooks(id);
            List<BookDto> bookDtoList = null;
            for(Book book : bookList){
                bookDtoList.add(book.toDto());
            }
            return bookDtoList;
        }catch (Exception e ){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }
    public int getCount(int id){
        session = SessionFactoryConfig.getInstance().getSession();
        try {
            bookRepository.setSession(session);
            int count = bookRepository.getCount(id);
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            session.close();
        }

    }

    @Override
    public int getBookPk(String bookName) {
        session = SessionFactoryConfig.getInstance().getSession();
        try {
            bookRepository.setSession(session);
            int pk = bookRepository.getBookPk(bookName);
            return pk;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            session.close();
        }
    }
}
