package ft.campaign.Repositories;

import ft.campaign.Entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICampaignRepository extends JpaRepository<Campaign, String> {
    List<Campaign> findCampaignByName(String name);
}
