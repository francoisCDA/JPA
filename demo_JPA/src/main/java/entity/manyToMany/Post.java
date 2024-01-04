package entity.manyToMany;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="post")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title ;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="post_tag",joinColumns = @JoinColumn(name="post_id"),inverseJoinColumns = @JoinColumn(name="tag_id"))
    private List<Tag> tagList;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tagList=" + tagList +
                '}';
    }
}
