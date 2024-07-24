package ft.campaign.Repositories;

import ft.campaign.Entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICampaignRepository extends JpaRepository<Campaign, String> {
    List<Campaign> findCampaignByName(String name);
    Optional<Campaign> findCampaignById(Long id);
    Optional<Campaign> deleteCampaignById(Long id);
}
