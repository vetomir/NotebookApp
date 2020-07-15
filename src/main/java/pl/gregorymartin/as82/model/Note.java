package pl.gregorymartin.as82.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public
class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title cannot be Blank")
    private String title;
    private Category category;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadline;

    public Note() {
    }

    public Note(final String title, final Category category, final String description, final LocalDateTime deadline) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.deadline = deadline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(final LocalDateTime deadline) {
        this.deadline = deadline;
    }

    //

    public Note toUpdate(Note source){
        Note result = new Note();
        result.id = source.id;
        result.title = source.title;
        result.category = source.category;
        result.description = source.description;
        result.deadline = source.deadline;

        return result;
    }
}
