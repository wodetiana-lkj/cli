package top.tsview.cli.dao;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import top.tsview.cli.dao.entity.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person save(@Nonnull Person person);

    Optional<Person> findById(long id);
}
