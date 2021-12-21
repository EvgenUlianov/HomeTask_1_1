package EvgenUlianov.HomeTask_1_1.TaskManager;

import EvgenUlianov.HomeTask_1_1.UserManager.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "taskDescription")
public class TaskDescription implements  Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    @Transactional
    public void setName(String name) {
        this.name = name;
    }

    @Transactional
    public void setOwner(User user) {
        this.owner = user;
    }

    @Transactional
    public void toggle() {
        completed = !(completed);
    }

    @Override
    public int compareTo(Object o) {
        return (int) (id - ((TaskDescription) o).id);
    }

}
