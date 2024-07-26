package ft.campaign.Mappers;

import ft.campaign.Entities.Company;
import ft.campaign.Models.CompanyRequest;
import ft.campaign.Models.CompanyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(injectionStrategy = org.mapstruct.InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ICompanyMapper {
    Company createCompanyRequestToCompany(CompanyRequest request);
    CompanyResponse companyToCreateCompanyResponse(Company company);
}
