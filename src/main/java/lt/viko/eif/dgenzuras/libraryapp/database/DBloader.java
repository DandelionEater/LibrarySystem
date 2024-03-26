package lt.viko.eif.dgenzuras.libraryapp.database;

import lt.viko.eif.dgenzuras.libraryapp.Main;
import lt.viko.eif.dgenzuras.libraryapp.model.Account;
import lt.viko.eif.dgenzuras.libraryapp.model.Book;
import lt.viko.eif.dgenzuras.libraryapp.model.Reader;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Objects;


/**
 * DBloader class communicates between the system and the database.
 *
 * @author dainius.genzuras@stud.viko.lt
 * @since 1.0
 * @see Main
 */


public class DBloader {

    public void SendPacket() {
        ServerSocket server = null;

        try {
            System.out.println();
            System.out.println("Starting TCP server on port 9094...");

            try {
                server = new ServerSocket(9094);

                System.out.println("Server successfully started, awaiting connection...");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            Socket socket = server.accept();

            System.out.println("Client connected, creating data...");

            Reader reader1 = new Reader(0, "Dainius", "Genzuras", 1001);
            Account account1 = new Account(0, "Saiyandeffa", "gibbi352");
            Book book1 = new Book(10140, "Java for dummies", "Jack 'o Niel", 2005, "Learning", 6);
            Book book2 = new Book(25542, "World's birds", "Kendrick Nomad", 2015, "Nature", 3);
            Book book3 = new Book(78009, "Carpentry 101", "Jack Adams", 2003, "Building", 1);
            Book book4 = new Book(56801, "Car engine guy", "Nicolas Cage", 2020, "Engineering", 20);
            Book book5 = new Book(90101, "Going Bovine", "Libba Bray", 2009, "Fantasy", 5);
            Book book6 = new Book(45675, "Revenge", "Yoko Ogawa", 1998, "Fiction", 2);
            Book book7 = new Book(23457, "Zeplin", "Karin Tidbeck", 2011, "Fiction", 10);
            Book book8 = new Book(11111, "Railsea", "China Mieville", 2012, "Fiction", 8);
            Book book9 = new Book(78654, "To kill a mockingbird", "Harper Lee", 1960, "Novel", 54);
            Book book10 = new Book(98765, "Invisible man", "Ralph Ellison", 1952, "Biography", 20);

            reader1.setAccount(account1);
            reader1.getBookList().add(book1);
            reader1.getBookList().add(book2);
            reader1.getBookList().add(book3);
            reader1.getBookList().add(book4);
            reader1.getBookList().add(book5);
            reader1.getBookList().add(book6);
            reader1.getBookList().add(book7);
            reader1.getBookList().add(book8);
            reader1.getBookList().add(book9);
            reader1.getBookList().add(book10);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            StringWriter data = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(Reader.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(reader1, data);

            System.out.println("Data created, validating...");

            if(!validate(data.toString())) {
                System.exit(1);
            }

            System.out.println("Data validated, sending packet...");

            out.println(data.toString());

            System.out.println("Packet sent successfully!");

            socket.close();

            server.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validate(String xmlFile) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(getResource("schema.xsd")));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(xmlFile)));
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();

            System.out.println("XML validation failed... Transfer terminating");
            return false;
        }
    }

    private String getResource(String filename) throws FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(filename);
        Objects.requireNonNull(resource);

        return resource.getFile();
    }
}
