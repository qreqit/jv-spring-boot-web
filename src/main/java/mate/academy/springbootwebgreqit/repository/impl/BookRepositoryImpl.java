package mate.academy.springbootwebgreqit.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import mate.academy.springbootwebgreqit.exception.EntityNotFoundException;
import mate.academy.springbootwebgreqit.model.Book;
import mate.academy.springbootwebgreqit.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext
    private  EntityManager entityManager;

    @Override
    @Transactional
    public Book save(Book book) {
        try {
            entityManager.persist(book);
            return book;
        } catch (Exception e) {
            throw new EntityNotFoundException("Can not save book");
        }
    }

    @Override
    public List<Book> findAll() {
        try {
            TypedQuery<Book> query = entityManager.createQuery("FROM Book ", Book.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new EntityNotFoundException("Can not find all from DB");
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try {
            Book book = entityManager.find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (Exception e) {
            throw new EntityNotFoundException("Can not find book by id:" + id);
        }
    }
}
