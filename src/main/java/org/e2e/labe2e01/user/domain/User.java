package org.e2e.labe2e01.user.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.review.domain.Review;

import java.time.Instant;
import java.util.ArrayList;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@RequiredArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = User.class
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "avg_rating", nullable = false)
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be at most 5")
    private Double avgRating = 0.0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = ZonedDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    @Column(nullable = false)
    private Integer trips = 0;

    @ManyToOne
    @JoinColumn(name = "coordinate_id")
    private Coordinate coordinate;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewsAuthored = new ArrayList<>();

    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewsReceived = new ArrayList<>();
}