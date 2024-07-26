package ft.campaign.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
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
    private Long companyId;
    private Date createdAt;
    private Date updatedAt;
}