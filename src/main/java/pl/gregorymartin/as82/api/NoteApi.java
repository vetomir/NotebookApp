package pl.gregorymartin.as82.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.as82.model.Note;
import pl.gregorymartin.as82.service.NoteService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteApi {
    private NoteService service;
    private Logger logger = LoggerFactory.getLogger(NoteApi.class);

    public NoteApi(final NoteService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<Note>> readAllTasks() {
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(service.showAll());
    }

    @PostMapping
    ResponseEntity<Note> createTask(@RequestBody @Valid Note toCreate) {
        Note result = service.saveNote(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @Transactional
    @PostMapping("/{id}")
    ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody @Valid Note toUpdate) {
        service.updateNote(toUpdate, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteNote(@PathVariable int id) {
        service.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
