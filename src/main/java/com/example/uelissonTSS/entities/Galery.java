package com.example.uelissonTSS.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "Galeria")
public class Galery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Column(nullable = false)
    private String imageUrl;
    @CreationTimestamp
    private Instant  creationTimestamp;

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
