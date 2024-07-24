package ft.campaign.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignCreateRequest {
    private String name;
    private String keywords;
    private double bidAmount;
    private double campaignFund;
    private boolean status;
    private String city;
    private double radius;
}
