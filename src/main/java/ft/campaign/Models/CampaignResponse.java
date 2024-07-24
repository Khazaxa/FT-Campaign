package ft.campaign.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponse {
    private Long id;
    private String name;
    private String keywords;
    private Double bidAmount;
    private Double campaignFund;
    private Boolean status;
    private String city;
    private Double radius;
    private Date createdAt;
    private Date updatedAt;
}
