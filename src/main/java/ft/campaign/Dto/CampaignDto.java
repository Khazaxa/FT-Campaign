package ft.campaign.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDto {
    private String name;
    private String keywords;
    private double bidAmount;
    private double campaignFund;
    private boolean status;
    private String city;
    private double radius;
}
