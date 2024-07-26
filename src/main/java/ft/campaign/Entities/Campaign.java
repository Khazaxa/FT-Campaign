package ft.campaign.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String keywords;
    @Column(nullable = false)
    private Double bidAmount;
    @Column(nullable = false)
    private Double campaignFund;
    @Column(nullable = false)
    private Boolean status = false;
    private String city;
    @Column(nullable = false)
    private Double radius; // in kilometers
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn
    private Company company;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}