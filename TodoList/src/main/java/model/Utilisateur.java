package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_utilisateur")
    private Long id;

    @Column(unique = true)
    private String pseudo;

    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
    List<Task> todoList;

    public Utilisateur(String pseudo){
        this.pseudo = pseudo;
    }

    public Utilisateur() {

    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public List<Task> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Task> todoList) {
        this.todoList = todoList;
    }

    @Override
    public String toString() {
        return  "id " + id +
                ", pseudo : " + pseudo +
                ", todoList : \n" + todoList +
                '}';
    }
}
