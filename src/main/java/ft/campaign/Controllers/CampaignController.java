package ft.campaign.Controllers;

import ft.campaign.Exceptions.WrongDataException;
import ft.campaign.Models.CampaignRequest;
import ft.campaign.Models.CampaignResponse;
import ft.campaign.Services.CampaignService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class CampaignController {

    private final CampaignService campaignService;
    public CampaignController (CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping("/campaign")
    public CampaignResponse createCampaign(@RequestBody CampaignRequest request) throws WrongDataException {
        log.info("Create campaign request: {}", request);
        CampaignResponse createCampaignResponse = campaignService.create(request);
        log.info("Campaign response: {}", createCampaignResponse);
        return createCampaignResponse;
    }

    @PutMapping("/campaign/{campaignId}")
    public CampaignResponse updateCampaign(@PathVariable Long campaignId, @RequestBody CampaignRequest request) throws WrongDataException {
        log.info("Update campaign request for id {}: {}", campaignId, request);
        CampaignResponse updateCampaignResponse = campaignService.update(campaignId, request);
        log.info("Update campaign response: {}", updateCampaignResponse);
        return updateCampaignResponse;
    }

    @PutMapping("/campaign/{campaignId}/activate")
    public CampaignResponse activateCampaign(@PathVariable Long campaignId, Long companyId) throws WrongDataException {
        log.info("Activating campaign with id: {} for company with id: {}", campaignId, companyId);
        CampaignResponse activateCampaignResponse = campaignService.activate(campaignId, companyId);
        log.info("Activated campaign: {}", activateCampaignResponse);
        return activateCampaignResponse;
    }

    @PutMapping("/campaign/{campaignId}/deactivate")
    public CampaignResponse deactivateCampaign(@PathVariable Long campaignId) throws WrongDataException {
        log.info("Deactivating campaign with id: {}", campaignId);
        CampaignResponse deactivateCampaignResponse = campaignService.deactivate(campaignId);
        log.info("Deactivated campaign: {}", deactivateCampaignResponse);
        return deactivateCampaignResponse;
    }

    @GetMapping("/campaign/{campaignId}")
    public CampaignResponse getCampaignById(@PathVariable Long campaignId) throws WrongDataException{
        log.info("Getting campaign by id: {}", campaignId);
        CampaignResponse campaignResponse = campaignService.getById(campaignId);
        log.info("Campaign by id: {}", campaignResponse);
        return campaignResponse;
    }

    @GetMapping("campaigns")
    public List<CampaignResponse> testConnection() throws WrongDataException{
        log.debug("Attempting to get all campaigns");
        return campaignService.getAll();
    }

    @DeleteMapping("/campaign/{campaignId}")
    public void deleteCampaign(@PathVariable Long campaignId) throws WrongDataException {
        log.info("Deleting campaign by id: {}", campaignId);
        campaignService.delete(campaignId);
    }
}
