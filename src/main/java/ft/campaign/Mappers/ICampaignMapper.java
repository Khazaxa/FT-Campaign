package ft.campaign.Mappers;

import ft.campaign.Entities.Campaign;
import ft.campaign.Models.CampaignRequest;
import ft.campaign.Models.CampaignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(injectionStrategy = org.mapstruct.InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ICampaignMapper {
    CampaignResponse campaignToCampaignCreateResponse(Campaign campaign);
    Campaign campaignCreateRequestToCampaign(CampaignRequest campaignRequest);
    void campaignUpdateRequestToCampaign(CampaignRequest campaignUpdateRequest, @MappingTarget Campaign existingCampaign);
    CampaignResponse campaignToCampaignUpdateResponse(Campaign campaign);
}