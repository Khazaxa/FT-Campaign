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
    private String name;
    private String keywords;
    private double bidAmount;
    private double campaignFund;
    private boolean status;
    private String city;
    private double radius; // in kilometers
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