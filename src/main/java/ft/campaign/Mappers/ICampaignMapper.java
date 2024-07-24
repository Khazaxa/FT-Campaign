package ft.campaign.Mappers;

import ft.campaign.Dto.CampaignDto;
import ft.campaign.Entities.Campaign;
import ft.campaign.Models.CampaignCreateRequest;
import ft.campaign.Models.CampaignCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(injectionStrategy = org.mapstruct.InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ICampaignMapper {
    CampaignDto campaignToCampaignDto(Campaign campaign);
    Campaign campaignDtoToCampaign(CampaignDto campaignDto);
    CampaignCreateResponse campaignToCampaignCreateResponse(Campaign campaign);
    Campaign campaignCreateRequestToCampaign(CampaignCreateRequest campaignCreateRequest);
//    void campaignUpdateRequestToCampaign(CampaignUpdateRequest campaignUpdateRequest, @MappingTarget Campaign existingCampaign);
//    CampaignUpdateResponse campaignToCampaignUpdateResponse(Campaign campaign);
}