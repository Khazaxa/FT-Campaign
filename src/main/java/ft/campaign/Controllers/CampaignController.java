package ft.campaign.Controllers;

import ft.campaign.Entities.Campaign;
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
    public Campaign createCampaign(@RequestBody Campaign request) throws Exception {
        log.debug("Create campaign request: {}", request);
        Campaign createCampaignResponse = campaignService.createCampaign(request);
        log.debug("Campaign response: {}", createCampaignResponse);
        return createCampaignResponse;
    }

    @GetMapping("/test-connection")
    public List<Campaign> testConnection() {
        log.debug("Testing database connection");
        return campaignService.getAllCampaigns();
    }
}
