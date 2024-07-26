import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../service/api.service';

@Component({
  selector: 'app-campaigns',
  templateUrl: './campaigns.component.html',
  styleUrls: ['./campaigns.component.css']
})
export class CampaignsComponent implements OnInit {
  campaigns: any[] = [];

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.loadCampaigns();
  }

  loadCampaigns(): void {
    this.apiService.getCampaigns().subscribe(data => this.campaigns = data);
  }

  createCampaign(): void {
    // Implement logic to create a new campaign
    const newCampaign = { name: 'New Campaign' }; // Example data
    this.apiService.createCampaign(newCampaign).subscribe(() => this.loadCampaigns());
  }

  editCampaign(id: string): void {
    // Implement logic to edit a campaign
  }

  deleteCampaign(id: string): void {
    this.apiService.deleteCampaign(id).subscribe(() => this.loadCampaigns());
  }
}