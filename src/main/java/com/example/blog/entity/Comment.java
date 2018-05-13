package com.example.blog.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comment")
public class Comment implements Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(min = 3, max = 1000)
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "datteOfAddition")
    private LocalDateTime dateOfAddition;

    @OneToMany(mappedBy = "comment")
    private Set<Vote> rating = new HashSet<>();

    public Comment() {
    }

    public Comment(@Size(min = 3, max = 1000) String description, User user, Post post, LocalDateTime dateOfAddition) {
        this.description = description;
        this.user = user;
        this.post = post;
        this.dateOfAddition = dateOfAddition;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(LocalDateTime dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }

    public Set<Vote> getRating() {
        return rating;
    }

    public void setRating(Set<Vote> rating) {
        this.rating = rating;
    }
}
