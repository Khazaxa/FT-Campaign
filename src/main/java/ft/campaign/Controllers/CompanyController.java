package ft.campaign.Controllers;

import ft.campaign.Exceptions.WrongDataException;
import ft.campaign.Models.CompanyRequest;
import ft.campaign.Services.CompanyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("/company/{companyId}")
    public void deleteCompany(Long companyId) {
        log.info("Deleting company with id: {}", companyId);
        companyService.delete(companyId);
        log.info("Company with id {} deleted", companyId);
    }
}
