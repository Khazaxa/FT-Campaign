package ft.campaign.Mappers;

import ft.campaign.Dto.CampaignDto;
import ft.campaign.Entities.Campaign;
import ft.campaign.Models.CampaignCreateRequest;
import ft.campaign.Models.CampaignCreateResponse;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-24T20:12:16+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ICampaignMapperImpl implements ICampaignMapper {

    @Override
    public CampaignDto campaignToCampaignDto(Campaign campaign) {
        if ( campaign == null ) {
            return null;
        }

        CampaignDto.CampaignDtoBuilder campaignDto = CampaignDto.builder();

        campaignDto.name( campaign.getName() );
        campaignDto.keywords( campaign.getKeywords() );
        campaignDto.bidAmount( campaign.getBidAmount() );
        campaignDto.campaignFund( campaign.getCampaignFund() );
        campaignDto.status( campaign.isStatus() );
        campaignDto.city( campaign.getCity() );
        campaignDto.radius( campaign.getRadius() );

        return campaignDto.build();
    }

    @Override
    public Campaign campaignDtoToCampaign(CampaignDto campaignDto) {
        if ( campaignDto == null ) {
            return null;
        }

        Campaign.CampaignBuilder campaign = Campaign.builder();

        campaign.name( campaignDto.getName() );
        campaign.keywords( campaignDto.getKeywords() );
        campaign.bidAmount( campaignDto.getBidAmount() );
        campaign.campaignFund( campaignDto.getCampaignFund() );
        campaign.status( campaignDto.isStatus() );
        campaign.city( campaignDto.getCity() );
        campaign.radius( campaignDto.getRadius() );

        return campaign.build();
    }

    @Override
    public CampaignCreateResponse campaignToCampaignCreateResponse(Campaign campaign) {
        if ( campaign == null ) {
            return null;
        }

        CampaignCreateResponse.CampaignCreateResponseBuilder campaignCreateResponse = CampaignCreateResponse.builder();

        campaignCreateResponse.id( campaign.getId() );
        campaignCreateResponse.name( campaign.getName() );
        campaignCreateResponse.keywords( campaign.getKeywords() );
        campaignCreateResponse.bidAmount( campaign.getBidAmount() );
        campaignCreateResponse.campaignFund( campaign.getCampaignFund() );
        campaignCreateResponse.status( campaign.isStatus() );
        campaignCreateResponse.city( campaign.getCity() );
        campaignCreateResponse.radius( campaign.getRadius() );
        if ( campaign.getCreatedAt() != null ) {
            campaignCreateResponse.createdAt( Date.from( campaign.getCreatedAt().toInstant( ZoneOffset.UTC ) ) );
        }
        if ( campaign.getUpdatedAt() != null ) {
            campaignCreateResponse.updatedAt( Date.from( campaign.getUpdatedAt().toInstant( ZoneOffset.UTC ) ) );
        }

        return campaignCreateResponse.build();
    }

    @Override
    public Campaign campaignCreateRequestToCampaign(CampaignCreateRequest campaignCreateRequest) {
        if ( campaignCreateRequest == null ) {
            return null;
        }

        Campaign.CampaignBuilder campaign = Campaign.builder();

        campaign.name( campaignCreateRequest.getName() );
        campaign.keywords( campaignCreateRequest.getKeywords() );
        campaign.bidAmount( campaignCreateRequest.getBidAmount() );
        campaign.campaignFund( campaignCreateRequest.getCampaignFund() );
        campaign.status( campaignCreateRequest.isStatus() );
        campaign.city( campaignCreateRequest.getCity() );
        campaign.radius( campaignCreateRequest.getRadius() );

        return campaign.build();
    }
}
