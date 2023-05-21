package com.example.librarybe.repositories;

import com.example.librarybe.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenreId(Long genreId);
    List<Book> findByPublisherId(Long publisherId);


}
