package ft.campaign.Services;

import ft.campaign.Entities.Company;
import ft.campaign.Exceptions.WrongDataException;
import ft.campaign.Mappers.ICompanyMapper;
import ft.campaign.Models.CompanyRequest;
import ft.campaign.Models.CompanyResponse;
import ft.campaign.Repositories.ICompanyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Double getEmeraldBalance(Long companyId) throws WrongDataException{
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isEmpty()) {
            throw new WrongDataException("Company not found.");
        }
        Company company = companyOptional.get();
        return company.getEmeraldAccountBalance();
    }

    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(companyMapper::companyToCreateCompanyResponse)
                .collect(Collectors.toList());
    }
}
