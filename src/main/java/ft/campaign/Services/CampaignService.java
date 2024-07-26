package ft.campaign.Services;

import ft.campaign.Entities.Campaign;
import ft.campaign.Entities.Company;
import ft.campaign.Exceptions.WrongDataException;
import ft.campaign.Mappers.ICampaignMapper;
import ft.campaign.Models.CampaignRequest;
import ft.campaign.Models.CampaignResponse;
import ft.campaign.Repositories.ICampaignRepository;
import ft.campaign.Repositories.ICompanyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CampaignService {

    private final ICampaignRepository campaignRepository;
    private final ICampaignMapper campaignMapper;
    private final ICompanyRepository companyRepository;

    public CampaignService(ICampaignRepository campaignRepository, ICampaignMapper campaignMapper, ICompanyRepository companyRepository) {
        this.campaignRepository = campaignRepository;
        this.campaignMapper = campaignMapper;
        this.companyRepository = companyRepository;
    }

    public CampaignResponse create(CampaignRequest request) throws WrongDataException {
        log.info("Creating campaign: {}", request);
        validate(request);

        Campaign campaign = campaignMapper.campaignCreateRequestToCampaign(request);
        campaign.setStatus(false);
        campaign.setCreatedAt(LocalDateTime.now());
        campaign.setUpdatedAt(LocalDateTime.now());
        Campaign savedCampaign = campaignRepository.save(campaign);
        log.info("Saved campaign: {}", savedCampaign);
        CampaignResponse createCampaignResponse = campaignMapper.campaignToCampaignCreateResponse(savedCampaign);
        log.info("Mapped campaign for response: {}", createCampaignResponse);
        return createCampaignResponse;
    }

    public CampaignResponse update(Long id, CampaignRequest request) throws WrongDataException {
        log.info("Requesting to update campaign with id: {}", id);
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        if (campaignOptional.isEmpty()) {
            log.info("Campaign with id {} was not found", id);
            throw new WrongDataException("Campaign was not found.");
        }

        validate(request);

        Campaign existingCampaign = campaignOptional.get();
        campaignMapper.campaignUpdateRequestToCampaign(request, existingCampaign);
        existingCampaign.setStatus(false);
        existingCampaign.setUpdatedAt(LocalDateTime.now());
        Campaign updatedCampaign = campaignRepository.save(existingCampaign);
        log.info("Successfully updated campaign with id: {}", id);
        return campaignMapper.campaignToCampaignUpdateResponse(updatedCampaign);
    }

    public CampaignResponse activate(Long campaignId, Long companyId) throws WrongDataException {
        log.info("Activating campaign with id: {}", campaignId);

        Optional<Campaign> campaignOptional = campaignRepository.findById(campaignId);
        if (campaignOptional.isEmpty()) {
            log.info("Campaign with id {} was not found", campaignId);
            throw new WrongDataException("Campaign was not found.");
        }

        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isEmpty()) {
            log.info("Company with id {} was not found", companyId);
            throw new WrongDataException("Company was not found.");
        }

        Campaign campaign = campaignOptional.get();
        Company company = companyOptional.get();

        if (company.getEmeraldAccountBalance() >= campaign.getCampaignFund()) {
            company.setEmeraldAccountBalance(company.getEmeraldAccountBalance() - campaign.getCampaignFund());
            campaign.setCompany(company);
            campaign.setStatus(true);
            campaign.setUpdatedAt(LocalDateTime.now());

            companyRepository.save(company);
            Campaign updatedCampaign = campaignRepository.save(campaign);

            log.info("Successfully activated campaign with id: {}", campaignId);
            return campaignMapper.campaignToCampaignUpdateResponse(updatedCampaign);
        } else {
            throw new WrongDataException("Insufficient funds in the company's Emerald account.");
        }
    }

    public CampaignResponse deactivate(Long id) throws WrongDataException {
        log.info("Deactivating campaign with id: {}", id);
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        if (campaignOptional.isEmpty()) {
            log.info("Campaign with id {} was not found", id);
            throw new WrongDataException("Campaign was not found.");
        }
        Campaign campaign = campaignOptional.get();
        campaign.setStatus(false);
        campaign.setUpdatedAt(LocalDateTime.now());
        Campaign updatedCampaign = campaignRepository.save(campaign);
        log.info("Successfully deactivated campaign with id: {}", id);
        return campaignMapper.campaignToCampaignUpdateResponse(updatedCampaign);
    }

    public CampaignResponse getById(Long id) throws WrongDataException {
        log.info("Getting campaign by id: {}", id);
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        if (campaignOptional.isEmpty()) {
            log.info("Campaign with id {} was not found", id);
            throw new WrongDataException("Campaign was not found.");
        }
        Campaign campaign = campaignOptional.get();
        log.info("Campaign by id: {}", campaign);
        return campaignMapper.campaignToCampaignUpdateResponse(campaign);
    }

    public List<CampaignResponse> getAll() throws WrongDataException {
        List<Campaign> campaigns = campaignRepository.findAll();
        if (campaigns.isEmpty()) {
            throw new WrongDataException("No campaigns found.");
        }
        return campaigns.stream()
                .map(campaignMapper::campaignToCampaignUpdateResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long campaignId) throws WrongDataException {
        Optional<Campaign> campaign = campaignRepository.findById(campaignId);
        if (campaign.isEmpty()) {
            throw new WrongDataException("Campaign was not found.");
        }
        log.info("Found campaign: {}", campaign);
        log.info("Requesting to delete campaign: {}", campaignId);
        campaignRepository.deleteById(campaignId);
        log.info("Successfully deleted campaign: {}", campaignId);
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
        if (request.getRadius() == null) {
            throw new WrongDataException("Campaign radius is required.");
        }
    }
}