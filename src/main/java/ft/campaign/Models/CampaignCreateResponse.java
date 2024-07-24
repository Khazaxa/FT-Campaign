package ft.campaign.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignCreateResponse {
    private Long id;
    private String name;
    private String keywords;
    private double bidAmount;
    private double campaignFund;
    private boolean status;
    private String city;
    private double radius;
    private Date createdAt;
    private Date updatedAt;

}
