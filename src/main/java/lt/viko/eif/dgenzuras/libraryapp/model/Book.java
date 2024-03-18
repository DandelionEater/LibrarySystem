package lt.viko.eif.dgenzuras.libraryapp.model;

import javax.persistence.*;

/**
 * Book class holds information about the book.
 *
 * @author dainius.genzuras@stud.viko.lt
 * @since 1.0
 * @see Reader
 */

@Entity
@Table(name = "book")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String name;
    private String authorName;
    private int releaseYear;
    private String category;
    private int inStock;

    public Book(int id, String name, String authorName, int releaseYear, String category, int inStock) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.releaseYear = releaseYear;
        this.category = category;
        this.inStock = inStock;
    }

    public Book() {}

    @Override
    public String toString(){
        return String.format(
                "\t\tBook:\n" +
                        "\t\t\tBook id: %d\n" +
                        "\t\t\tBook name: %s\n" +
                        "\t\t\tAuthor name: %s\n" +
                        "\t\t\tRelease year: %d\n" +
                        "\t\t\tBook category: %s\n" +
                        "\t\t\tHave in stock: %d\n",
                this.id,
                this.name,
                this.authorName,
                this.releaseYear,
                this.category,
                this.inStock);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
}
