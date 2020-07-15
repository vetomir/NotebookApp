package pl.gregorymartin.as82.model;

import java.time.LocalDateTime;

public class NoteReadModel {
    private Integer id;
    private String title;
    private String category;
    private Integer daysToDeadline;
    private String description;


    public NoteReadModel(Note source) {
        this.id = source.getId();
        this.title = source.getTitle();
        this.category = source.getCategory().getDisplayValue();
        this.daysToDeadline = getDaysToDeadline(source.getDeadline());
        this.description = source.getDescription();
    }

    Integer getDaysToDeadline(LocalDateTime source){
        Integer result = source.compareTo(LocalDateTime.now());
        return result;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public Integer getDaysToDeadline() {
        return daysToDeadline;
    }

    public void setDaysToDeadline(final Integer daysToDeadline) {
        this.daysToDeadline = daysToDeadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
