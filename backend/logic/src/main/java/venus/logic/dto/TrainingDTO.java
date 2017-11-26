package venus.logic.dto;

import venus.dal.model.Training;

public class TrainingDTO {
    private int id;
    private String name;

    public TrainingDTO() {
    }

    public TrainingDTO(int id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TrainingDTO create(Training training) {
        return new TrainingDTO(
                training.getId(),
                training.getName());
    }
}
