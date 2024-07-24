package ft.campaign.Controllers;

import ft.campaign.Entities.Campaign;
import ft.campaign.Exceptions.WrongDataException;
import ft.campaign.Models.CampaignCreateRequest;
import ft.campaign.Models.CampaignCreateResponse;
import ft.campaign.Services.CampaignService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public CampaignCreateResponse createCampaign(@RequestBody CampaignCreateRequest request) throws WrongDataException {
        log.info("Create campaign request: {}", request);
        CampaignCreateResponse createCampaignResponse = campaignService.create(request);
        log.info("Campaign response: {}", createCampaignResponse);
        return createCampaignResponse;
    }

    @GetMapping("/test-connection")
    public List<Campaign> testConnection() {
        log.debug("Testing database connection");
        return campaignService.getAllCampaigns();
    }
}
