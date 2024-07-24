package ft.campaign.Services;

import ft.campaign.Entities.Campaign;
import ft.campaign.Exceptions.WrongDataException;
import ft.campaign.Mappers.ICampaignMapper;
import ft.campaign.Models.CampaignRequest;
import ft.campaign.Models.CampaignResponse;
import ft.campaign.Repositories.ICampaignRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CampaignService {

    private final ICampaignRepository campaignRepository;
    private final ICampaignMapper campaignMapper;

    public CampaignService(ICampaignRepository campaignRepository, ICampaignMapper campaignMapper) {
        this.campaignRepository = campaignRepository;
        this.campaignMapper = campaignMapper;
    }

    public CampaignResponse create(CampaignRequest request) throws WrongDataException {
        log.info("Validating create campaign request");
        validate(request);

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
        CampaignResponse createCampaignResponse = campaignMapper.campaignToCampaignCreateResponse(savedCampaign);
        log.info("Mapped campaign for response: {}", createCampaignResponse);
        return createCampaignResponse;
    }

    public CampaignResponse update(Long id, CampaignRequest request) throws WrongDataException {
        log.info("Requesting to update campaign with id: {}", id);
        Optional<Campaign> campaignOptional = campaignRepository.findCampaignById(id);
        if (campaignOptional.isEmpty()) {
            log.info("Campaign with id {} was not found", id);
            throw new WrongDataException("Campaign was not found.");
        }

        validate(request);

        Campaign existingCampaign = campaignOptional.get();
        campaignMapper.campaignUpdateRequestToCampaign(request, existingCampaign);
        Campaign updatedCampaign = campaignRepository.save(existingCampaign);
        log.info("Successfully updated campaign with id: {}", id);
        return campaignMapper.campaignToCampaignUpdateResponse(updatedCampaign);
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    private void validate(CampaignRequest request) throws WrongDataException {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new WrongDataException("Campaign name is required.");
        }
        if (request.getKeywords() == null || request.getKeywords().isEmpty()) {
            throw new WrongDataException("Campaign keywords are required.");
        }
        if (request.getBidAmount() == null) {
            throw new WrongDataException("Bid amount is required.");
        }
        if (Double.compare(request.getBidAmount(), 1.0) < 0) {
            throw new WrongDataException("Bid amount must be at least 1.");
        }
        if (request.getCampaignFund() == null) {
            throw new WrongDataException("Campaign fund is required.");
        }
        if (request.getStatus() == null) {
            throw new WrongDataException("Campaign status is required.");
        }
        if (request.getRadius() == null) {
            throw new WrongDataException("Campaign radius is required.");
        }
    }
}