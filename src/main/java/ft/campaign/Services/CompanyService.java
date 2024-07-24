package ft.campaign.Services;

import ft.campaign.Entities.Company;
import ft.campaign.Mappers.ICompanyMapper;
import ft.campaign.Models.CompanyRequest;
import ft.campaign.Models.CompanyResponse;
import ft.campaign.Repositories.ICompanyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CompanyService {

    private final ICompanyRepository companyRepository;
    private final ICompanyMapper companyMapper;

    public CompanyService(ICompanyRepository companyRepository, ICompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public CompanyResponse create(CompanyRequest request) {
        log.info("Mapping request to company: {}", request);
        Company company = companyMapper.createCompanyRequestToCompany(request);
        log.info("Mapped company to be created: {}", company);

        Company savedCompany = companyRepository.save(company);
        log.info("Saved company: {}", savedCompany);

        CompanyResponse createCompanyResponse = companyMapper.companyToCreateCompanyResponse(savedCompany);
        log.info("Mapped saved company for response: {}", createCompanyResponse);

        return createCompanyResponse;
    }

    public void delete(Long id) {
        log.info("Requesting to delete company with id: {}", id);
        companyRepository.deleteById(id);
        log.info("Successfully deleted company with id: {}", id);
    }
}
