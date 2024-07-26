package ft.campaign.Mappers;

import ft.campaign.Entities.Campaign;
import ft.campaign.Models.CampaignRequest;
import ft.campaign.Models.CampaignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(injectionStrategy = org.mapstruct.InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ICampaignMapper {
    @Mapping(target = "companyId", source = "company.id")
    CampaignResponse campaignToCampaignCreateResponse(Campaign campaign);

    Campaign campaignCreateRequestToCampaign(CampaignRequest campaignRequest);

    void campaignUpdateRequestToCampaign(CampaignRequest campaignUpdateRequest, @MappingTarget Campaign existingCampaign);

    @Mapping(target = "companyId", source = "company.id")
    CampaignResponse campaignToCampaignUpdateResponse(Campaign campaign);
}