package lt.viko.eif.dgenzuras.libraryapp.database;

import lt.viko.eif.dgenzuras.libraryapp.model.Account;
import lt.viko.eif.dgenzuras.libraryapp.model.Book;
import lt.viko.eif.dgenzuras.libraryapp.model.Reader;
import lt.viko.eif.dgenzuras.libraryapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


/**
 * DBloader class communicates between the system and the database.
 *
 * @author dainius.genzuras@stud.viko.lt
 * @since 1.0
 * @see lt.viko.eif.dgenzuras.libraryapp.Main
 */

public class DBloader {
    static Server server;

    static {
        try {
            server = Server.createTcpServer("-tcpPort", "9094").start();

            Reader reader1 = new Reader(0, "Dainius", "Genzuras", 1001);
            Account account1 = new Account(0, "Saiyandeffa", "gibbi352");
            Book book1 = new Book(00140, "Java for dummies", "Jack 'o Niel", 2005, "Learning", 6);
            Book book2 = new Book(05542, "World's birds", "Kendrick Nomad", 2015, "Nature", 3);
            Book book3 = new Book(78009, "Carpentry 101", "Jack Adams", 2003, "Building", 1);
            Book book4 = new Book(56801, "Car engine guy", "Nicolas Cage", 2020, "Engineering", 20);

            reader1.setAccount(account1);
            reader1.getBookList().add(book1);
            reader1.getBookList().add(book2);
            reader1.getBookList().add(book3);
            reader1.getBookList().add(book4);

            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.save(reader1);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (server != null) server.shutdown();
        }
    }

    public static void loadReaders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Reader> readers = session.createQuery("from Reader", Reader.class).list();
            readers.forEach(read -> System.out.println(read));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
