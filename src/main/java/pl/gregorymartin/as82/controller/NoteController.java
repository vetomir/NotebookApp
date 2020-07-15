package pl.gregorymartin.as82.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gregorymartin.as82.model.Note;
import pl.gregorymartin.as82.service.NoteService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
class NoteController {
    private NoteService service;

    static int uberId;

    public NoteController(final NoteService service) {
        this.service = service;
    }

    @GetMapping
    String showProjects(Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("notes", service.ShowAllReadModels());
        return "note";
    }

    @PostMapping
    String createNote(
            @ModelAttribute(value = "note") Note current,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()){
            return "note";
        }

        service.saveNote(current);

        model.addAttribute("note", new Note());
        model.addAttribute("notes", service.ShowAllReadModels());
        model.addAttribute("message", "Dodano Notatkę!!");
        return "note";
    }



    //delete
    @GetMapping("/delete/{id}")
    String deleteNote(
            @PathVariable int id,
            Model model
    ) {
        uberId = id;
        service.deleteNote(uberId);
        model.addAttribute("id", uberId);

        return "redirect:/";
    }

    //edit
    @GetMapping("/edit/{id}")
    String showNoteToEdit(
            @PathVariable int id,
            Model model
    ) {
        uberId = id;
        model.addAttribute("id", uberId);
        model.addAttribute("noteById", service.findById(uberId));

        return "edit";
    }


    @PostMapping("/edit")
    String editNote(
            @ModelAttribute("noteById") Note current,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()){
            return "edit";
        }
        service.updateNote( current, uberId);
        model.addAttribute("id", uberId);
        return "redirect:/";
    }
}
