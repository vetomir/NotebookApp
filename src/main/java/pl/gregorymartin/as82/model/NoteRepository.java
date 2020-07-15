package pl.gregorymartin.as82.model;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {
    List<Note> findAll();

    Note getById(Integer id);
    Optional<Note> findById(Integer id);

    boolean existsById(Integer id);

    Note save(Note source);

    void deleteById(Integer id);
}
