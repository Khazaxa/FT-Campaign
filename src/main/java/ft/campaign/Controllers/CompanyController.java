package ft.campaign.Controllers;

import ft.campaign.Exceptions.WrongDataException;
import ft.campaign.Models.CompanyRequest;
import ft.campaign.Models.CompanyResponse;
import ft.campaign.Services.CompanyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/company")
    public void createCompany(@RequestBody CompanyRequest request) {
        log.info("Creating company");
        companyService.create(request);
        log.info("Company created");
    }

    @GetMapping("/company/{companyId}/balance")
    public Double getCompanyEmeraldBalance(@PathVariable Long companyId) throws WrongDataException{
        log.info("Getting company emerald balance by id: {}", companyId);
        Double emeraldBalance = companyService.getEmeraldBalance(companyId);
        log.info("Company emerald balance by id: {}", emeraldBalance);
        return emeraldBalance;
    }

    @GetMapping("/companies")
    public List<CompanyResponse> getAllCompanies() {
        log.info("Getting all companies");
        List<CompanyResponse> companies = companyService.getAllCompanies();
        log.info("All companies: {}", companies);
        return companies;
    }

    @DeleteMapping("/company/{companyId}")
    public void deleteCompany(Long companyId) {
        log.info("Deleting company with id: {}", companyId);
        companyService.delete(companyId);
        log.info("Company with id {} deleted", companyId);
    }
}
