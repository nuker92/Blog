package com.example.blog.entity.tags;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "tagValue")
    private String tagValue;

    @OneToMany(mappedBy = "tag")
    private List<TagUsage> usedTags = new ArrayList<>();

    public Tag() {
    }

    public Tag(String tagValue) {
        this.tagValue = tagValue;
    }

    public long getId() {
        return id;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public List<TagUsage> getUsedTags() {
        return usedTags;
    }

    public void setUsedTags(List<TagUsage> usedTags) {
        this.usedTags = usedTags;
    }
}
