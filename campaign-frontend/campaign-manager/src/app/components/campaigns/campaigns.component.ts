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

  editCampaign(id: string): void {
    this.apiService.getCampaign(id).subscribe(data => {
      const name = prompt('Enter campaign name:', data.name);
      if (name) {
        this.apiService.updateCampaign(id, { name }).subscribe(() => this.loadCampaigns());
      }
    }
    );
  }

  deleteCampaign(id: string): void {
    this.apiService.deleteCampaign(id).subscribe(() => this.loadCampaigns());
  }
}