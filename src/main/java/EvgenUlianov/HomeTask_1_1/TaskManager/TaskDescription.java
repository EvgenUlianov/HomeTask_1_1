package EvgenUlianov.HomeTask_1_1.TaskManager;

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

    public TaskDescription(String name) {
        this.name = name;
        this.completed = false;
    }

    public TaskDescription(){};
//    public String getName() {
//        return name;
//    }

    @Transactional
    public void setName(String name) {
        this.name = name;
    }

    @Transactional
    public void toggle() {
        completed = !(completed);
    }

    @Override
    public int compareTo(Object o) {
        return (int) (id - ((TaskDescription) o).id);
    }

//    public boolean isCompleted() {
//        return completed;
//    }



}
