package pl.gregorymartin.as82.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.gregorymartin.as82.model.Category;
import pl.gregorymartin.as82.model.Note;
import pl.gregorymartin.as82.model.NoteReadModel;
import pl.gregorymartin.as82.model.NoteRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public
class NoteService {
    private NoteRepository repository;
    Logger logger = LoggerFactory.getLogger(NoteService.class);

    public NoteService(final NoteRepository repository) {
        this.repository = repository;
        repository.save(new Note("Przykład 1", Category.HOME, "Przykładowy opis1", LocalDateTime.now()));
        repository.save(new Note("Przykład 2", Category.WORK, "Przykładowy opis2", LocalDateTime.now()));
        repository.save(new Note("Przykład 3", Category.LEISURE, "Przykładowy opis3", LocalDateTime.now()));

    }
    public Note findById(Integer id){
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Note> showAll(){
        return repository.findAll();
    }
    public Note saveNote(Note source){
        return repository.save(source);
    }
    public Note updateNote(Note source, Integer id){
        Note oldOne = repository.getById(id);
        Note newOne = oldOne.toUpdate(source);

        repository.save(newOne);
        repository.deleteById(id);

        return newOne;
    }

    public boolean deleteNote(Integer id){
        try {
            Optional<Note> findNote = Optional.of(repository.getById(id));
            repository.deleteById(id);
            return true;
        }catch (IllegalArgumentException e){
            logger.warn("Cannot find Note to delete // " + e.getMessage());
            return false;
        }
    }

    //reload every time when is called
    public List<NoteReadModel> ShowAllReadModels(){
        List<NoteReadModel> readModels = new ArrayList<>();
        showAll().stream().forEach(x -> readModels.add(new NoteReadModel(x)));
        return readModels;
    }

}
