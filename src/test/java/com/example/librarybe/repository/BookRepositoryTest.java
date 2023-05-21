package com.example.librarybe.repository;

import com.example.librarybe.models.Book;
import com.example.librarybe.repositories.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void it_should_save_book() {
        Book bookMock = new Book();
        bookMock.setId(1L);

        bookRepository.save(bookMock);

        Book book = bookRepository.findById(1L).get();

        Assert.assertEquals(1L, (long) book.getId());
    }
}