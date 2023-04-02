package lth.com.hrm.HumanManagement.EntityManager;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "blogs", schema = "testauth", catalog = "")
public class BlogsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "title")
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogsEntity that = (BlogsEntity) o;
        return id == that.id && Objects.equals(content, that.content) && Objects.equals(image, that.image) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, image, title);
    }
}
