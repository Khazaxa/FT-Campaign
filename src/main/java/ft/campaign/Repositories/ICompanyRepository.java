package ft.campaign.Repositories;

import ft.campaign.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, String> {
    void deleteById(Long id);
}
