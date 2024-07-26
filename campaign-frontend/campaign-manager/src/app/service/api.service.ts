import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }
  
  // Campaign
  createCampaign(campaign: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/campaign`, campaign);
  }

  getCampaigns(): Observable<any> {
    return this.http.get(`${this.baseUrl}/campaigns`);
  }

  getCampaign(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/campaign/${id}`);
  }

  updateCampaign(id: string, campaign: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/campaign/${id}`, campaign);
  }

  deleteCampaign(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/campaign/${id}`);
  }

  activateCampaign(id: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/campaign/${id}/activate`, {});
  }

  deactivateCampaign(id: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/campaign/${id}/deactivate`, {});
  }

  // Company

  createCompany(company: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/company`, company);
  }

  getCompanies(): Observable<any> {
    return this.http.get(`${this.baseUrl}/companies`);
  }

  getCompanyBalance(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/company/${id}/balance`);
  }

  deleteCompany(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/company/${id}`);
  }
}