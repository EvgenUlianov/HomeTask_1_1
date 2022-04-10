package EvgenUlianov.HomeTask_1_1.TaskManager;

import EvgenUlianov.HomeTask_1_1.UserManager.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "task_description")
public class TaskDescription implements  Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private boolean completed;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User owner;

    public TaskDescription(String name) {
        this.name = name;
        this.completed = false;
    }

    public TaskDescription(){};

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    public void toggle() {
        completed = !(completed);
    }

    @Override
    public int compareTo(Object o) {
        return (int) (id - ((TaskDescription) o).id);
    }

}
