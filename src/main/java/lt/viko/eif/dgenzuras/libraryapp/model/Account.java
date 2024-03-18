package lt.viko.eif.dgenzuras.libraryapp.model;

import javax.persistence.*;

/**
 * Account class holds information about the account.
 *
 * @author dainius.genzuras@stud.viko.lt
 * @since 1.0
 * @see Reader
 */

@Entity
@Table(name = "account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String userName;
    private String password;

    public Account(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Account() {}

    @Override
    public String toString() {
        return String.format(
                "\t\tUser name: %s \n" + "\t\tPassword: %s \n",
                this.userName,
                this.password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
