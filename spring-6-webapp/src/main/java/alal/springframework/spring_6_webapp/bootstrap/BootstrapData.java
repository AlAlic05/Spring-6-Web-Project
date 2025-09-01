package alal.springframework.spring_6_webapp.bootstrap;

import alal.springframework.spring_6_webapp.domain.Author;
import alal.springframework.spring_6_webapp.domain.Book;
import alal.springframework.spring_6_webapp.domain.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import alal.springframework.spring_6_webapp.repositories.AuthorRepository;
import alal.springframework.spring_6_webapp.repositories.BookRepository;
import alal.springframework.spring_6_webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher1 = new Publisher();
        publisher1.setPublisherName("Maulwurf Verlag");
        publisher1.setCity("München");
        publisher1.setState("BY");
        publisher1.setZip("80331");
        publisher1.setAddress("Marienplatz 1");

        Author author1 = new Author();
        author1.setFirstName("John");
        author1.setLastName("Doe");

        Book book1 = new Book();
        book1.setTitle("Java ist nicht nur eine Insel");
        book1.setIsbn("123456789");

        Author authorSaved1 = authorRepository.save(author1);
        Book bookSaved1 = bookRepository.save(book1);

        Author author2 = new Author();
        author2.setFirstName("Jane");
        author2.setLastName("Doe");

        Book book2 = new Book();
        book2.setTitle("Spring Boot für Dummies");
        book2.setIsbn("987654321");

        Author authorSaved2 = authorRepository.save(author2);
        Book bookSaved2 = bookRepository.save(book2);
        Publisher publisherSaved1 = publisherRepository.save(publisher1);

        bookSaved1.setPublisher(publisherSaved1);
        bookSaved2.setPublisher(publisherSaved1);

        authorSaved1.getBooks().add(bookSaved1);
        authorSaved2.getBooks().add(bookSaved2);

        bookSaved1.getAuthors().add(authorSaved1);
        bookSaved2.getAuthors().add(authorSaved2);

        authorRepository.save(authorSaved1);
        authorRepository.save(authorSaved2);

        bookRepository.save(bookSaved1);
        bookRepository.save(bookSaved2);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
        System.out.flush();

    }
}
