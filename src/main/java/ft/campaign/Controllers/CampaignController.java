package ft.campaign.Controllers;

import ft.campaign.Entities.Campaign;
import ft.campaign.Exceptions.WrongDataException;
import ft.campaign.Models.CampaignRequest;
import ft.campaign.Models.CampaignResponse;
import ft.campaign.Services.CampaignService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/campaign")
public class CampaignController {

    private final CampaignService campaignService;
    public CampaignController (CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public CampaignResponse createCampaign(@RequestBody CampaignRequest request) throws WrongDataException {
        log.info("Create campaign request: {}", request);
        CampaignResponse createCampaignResponse = campaignService.create(request);
        log.info("Campaign response: {}", createCampaignResponse);
        return createCampaignResponse;
    }

    @PutMapping("/{campaignId}")
    public CampaignResponse updateCampaign(@PathVariable Long campaignId, @RequestBody CampaignRequest request) throws WrongDataException {
        log.info("Update campaign request for id {}: {}", campaignId, request);
        CampaignResponse updateCampaignResponse = campaignService.update(campaignId, request);
        log.info("Update campaign response: {}", updateCampaignResponse);
        return updateCampaignResponse;
    }

    @GetMapping("/test-connection")
    public List<Campaign> testConnection() {
        log.debug("Testing database connection");
        return campaignService.getAllCampaigns();
    }
}
