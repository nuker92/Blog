package com.example.blog.entity;

import com.example.blog.entity.tags.TagUsage;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post implements Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(min = 3, max = 10000)
    @Column(length = Integer.MAX_VALUE, name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "dateOfAddition")
    private LocalDateTime dateOfAddition;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private Set<Vote> rating = new HashSet<>();

    @OneToMany(mappedBy = "post")
    private List<TagUsage> usedTags = new ArrayList<>();

    public Post() {
    }

    public Post(@Size(min = 3, max = 10000) String description, User user, LocalDateTime dateOfAddition) {
        this.description = description;
        this.user = user;
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

    public LocalDateTime getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(LocalDateTime dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Vote> getRating() {
        return rating;
    }

    public void setRating(Set<Vote> rating) {
        this.rating = rating;
    }

    public List<TagUsage> getUsedTags() {
        return usedTags;
    }

    public void setUsedTags(List<TagUsage> usedTags) {
        this.usedTags = usedTags;
    }
}
