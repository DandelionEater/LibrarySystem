package lt.viko.eif.dgenzuras.libraryapp.model;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 * Reader class holds information about the readers profile.
 *
 * @author dainius.genzuras@stud.viko.lt
 * @since 1.0
 * @see Account
 * @see Book
 */

@Entity
@Table(name = "reader")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String firstName;
    private String lastName;
    private int code;

    @OneToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();

    public Reader(int id, String firstName, String lastName, int code) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
    }

    public Reader() {}

    @Override
    public String toString() {
        return String.format("Reader:\n" +
                "\tFirst name: %s\n" +
                "\tLast name: %s\n" +
                "\tCode: %s\n" +
                "\tAccount: %s\n" +
                "%s",
                this.firstName,
                this.lastName,
                this.code,
                account,
                constructBookList());
    }

    private String constructBookList() {
        String result = "";
        for (Book book : bookList){
            result += book;
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Book> getBookList() { return bookList; }

    public void setBookList(List<Book> bookList) { this.bookList = bookList; }

    public Account getAccount() { return account; }

    public void setAccount(Account account) { this.account = account; }
}
