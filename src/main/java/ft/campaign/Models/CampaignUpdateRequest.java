package ft.campaign.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignUpdateRequest {
    private String name;
    private String keywords;
    private Double bidAmount;
    private Double campaignFund;
    private Boolean status;
    private String city;
    private Double radius;
}
