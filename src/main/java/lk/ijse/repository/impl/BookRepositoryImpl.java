package lk.ijse.repository.impl;

import lk.ijse.entity.Book;
import lk.ijse.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private static BookRepository bookRepository;

    private Session session;
    private BookRepositoryImpl(){}
    public static BookRepository getInstance(){
        return bookRepository == null ? bookRepository =new  BookRepositoryImpl() : bookRepository;
    }
    @Override
    public List<Book> getAllBook() {
        String sql ="SELECT c FROM Book AS c";
        Query query = session.createQuery(sql);
        List<Book> list = query.getResultList();

        for (Book book : list){
            System.out.println(book);
        }

        return list;
    }

    @Override
    public List<Book> getSelectedBooks(int id) {
        String sql = "SELECT C FROM Book AS C WHERE C.branch = :id";
        Query query = session.createQuery(sql);
        List list = query.list();
        return list;
    }

    @Override
    public Integer save(Book book) {
        book.setBranch(book.getBranch());
        System.out.println(book.getBranch());
        session.save(book);
        return 0;
    }

    @Override
    public void update(Book book) {
        session.update(book);
    }

    @Override
    public Book get(Integer integer) {
        return session.get(Book.class,integer);
    }

    @Override
    public void delete(Book book) {
        session.delete(book);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    public int getCount(int id){
        String sql = "SELECT count(C) FROM Book AS C WHERE C.branch = :id";
        Query query = session.createQuery(sql);
        int count = (int) query.getSingleResult();
        return count;

    }

    @Override
    public int getBookPk(String bookName) {
        String sql = "SELECT c.bookId FROM Book AS c WHERE c.bookName = :bookName";
        Query query = session.createQuery(sql,Integer.class);
        query.setParameter("bookName", bookName);
        List<Integer> list = query.getResultList();
        int pk = list.get(0);

        return pk;
    }

    @Override
    public void saveAll(Book book) {
        session.save(book);
    }
}