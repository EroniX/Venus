package venus.logic.dto;

import venus.dal.model.Training;
import venus.dal.model.User;

public class TrainingDTO {
    private int id;
    private String name;
    private Boolean registered;

    public TrainingDTO() {
    }

    public TrainingDTO(int id, String name, Boolean registerded) {
        this.setId(id);
        this.setName(name);
        this.setRegistered(registerded);
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

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public static TrainingDTO create(Training training, User user) {
        return new TrainingDTO(
            training.getId(),
            training.getName(),
            user.hasTraining(training.getId())
        );
    }
}
