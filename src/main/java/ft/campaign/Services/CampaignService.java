package ft.campaign.Services;

import ft.campaign.Entities.Campaign;
import ft.campaign.Exceptions.WrongDataException;
import ft.campaign.Mappers.ICampaignMapper;
import ft.campaign.Models.CampaignCreateRequest;
import ft.campaign.Models.CampaignCreateResponse;
import ft.campaign.Repositories.ICampaignRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
public class CampaignService {

    private final ICampaignRepository campaignRepository;
    private final ICampaignMapper campaignMapper;

    public CampaignService(ICampaignRepository campaignRepository, ICampaignMapper campaignMapper) {
        this.campaignRepository = campaignRepository;
        this.campaignMapper = campaignMapper;
    }

    public CampaignCreateResponse create(CampaignCreateRequest request) throws WrongDataException {
        log.info("Mapping request to campaign: {}", request);
        Campaign campaign = campaignMapper.campaignCreateRequestToCampaign(request);
        log.info("Mapped campaign to be created: {}", campaign);

        List<Campaign> existingCampaigns = campaignRepository.findCampaignByName(campaign.getName());
        if (!existingCampaigns.isEmpty()) {
            throw new WrongDataException("A campaign with the same name already exists.");
        }

        campaign.setCreatedAt(LocalDateTime.now());

        Campaign savedCampaign;
        try {
            savedCampaign = campaignRepository.save(campaign);
        } catch (DataIntegrityViolationException exception) {
            throw new WrongDataException(exception.getMessage());
        }
        log.info("Saved campaign: {}", savedCampaign);
        CampaignCreateResponse createCarResponse = campaignMapper.campaignToCampaignCreateResponse(savedCampaign);
        log.info("Mapped car for response: {}", createCarResponse);
        return createCarResponse;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

}