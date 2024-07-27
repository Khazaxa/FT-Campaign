import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Campaign } from '../components/campaigns/models/campaign';
import { Company } from '../components/companies/models/company';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  private readonly API_URL = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  // Campaign methods
  addCampaign(campaign: Campaign): Observable<Campaign> {
    return this.http.post<Campaign>(`${this.API_URL}/campaign`, campaign);
  }

  getCampaigns(): Observable<Campaign[]> {
    return this.http.get<Campaign[]>(`${this.API_URL}/campaigns`);
  }

  // Company methods
  addCompany(company: Company): Observable<Company> {
    return this.http.post<Company>(`${this.API_URL}/company`, company);
  }

  getCompanies(): Observable<Company[]> {
    return this.http.get<Company[]>(`${this.API_URL}/companies`);
  }

  deleteCompany(companyId: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/company/${companyId}`);
  }

  getCompanyBalance(companyId: number): Observable<number> {
    return this.http.get<number>(`${this.API_URL}/company/${companyId}/balance`);
  }
}
