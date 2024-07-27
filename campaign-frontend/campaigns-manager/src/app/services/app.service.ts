import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Campaign } from '../components/campaigns/models/campaign';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  private readonly API_URL = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  addCampaign(campaign: Campaign): Observable<Campaign> {
    return this.http.post<Campaign>(`${this.API_URL}/campaign`, campaign);
  }

  getCampaign(campaignId: number): Observable<Campaign> {
    return this.http.get<Campaign>(`${this.API_URL}/campaign/${campaignId}`);
  }

  updateCampaign(campaign: Campaign): Observable<Campaign> {
    return this.http.put<Campaign>(`${this.API_URL}/campaign/${campaign.id}`, campaign);
  }

  deleteCampaign(campaignId: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/campaign/${campaignId}`);
  }

  deactivateCampaign(campaignId: number): Observable<Campaign> {
    return this.http.put<Campaign>(`${this.API_URL}/campaign/${campaignId}/deactivate`, {});
  }

  activateCampaign(campaignId: number): Observable<Campaign> {
    return this.http.put<Campaign>(`${this.API_URL}/campaign/${campaignId}/activate`, {});
  }

  getCampaigns(): Observable<Campaign[]> {
    return this.http.get<Campaign[]>(`${this.API_URL}/campaigns`);
  }
}
