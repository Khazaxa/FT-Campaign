package ft.campaign.Repositories;

import ft.campaign.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long> {
    void deleteById(Long id);
    Optional<Company> findById(Long id);
}
