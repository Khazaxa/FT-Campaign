import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../service/api.service';
import { CampaignResponse } from './models/campaign-response.model';

@Component({
  selector: 'app-campaigns',
  templateUrl: './campaigns.component.html',
  styleUrls: ['./campaigns.component.css']
})
export class CampaignsComponent implements OnInit {
  campaigns: CampaignResponse[] = [];
  loading = false;
  errorMessage: string | null = null;

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.loadCampaigns();
  }

  loadCampaigns(): void {
    this.loading = true;
    this.apiService.getCampaigns().subscribe(
      data => {
        this.campaigns = data;
        this.loading = false;
      },
      error => {
        this.errorMessage = error;
        this.loading = false;
      }
    );
  }
}