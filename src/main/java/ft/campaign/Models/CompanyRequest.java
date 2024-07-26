package ft.campaign.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {
    private String name;
    private Double emeraldAccountBalance;
    private String address;
    private String city;
    private String country;
    private String email;
    private String phoneNumber;
}
