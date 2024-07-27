import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Campaign } from './models/campaign';
import { AppService } from '../../services/app.service';
import { Cities } from './models/Cities';

@Component({
  selector: 'app-campaigns',
  standalone: true,
  templateUrl: './campaigns.component.html',
  styleUrls: ['./campaigns.component.css'],
  imports: [CommonModule, FormsModule, ReactiveFormsModule]
})
export class CampaignsComponent {
  campaign: Campaign = {
    id: 0,
    name: '',
    keywords: '',
    bidAmount: 0,
    campaignFund: false,
    status: '',
    city: '',
    radius: 0
  };

  campaigns: Campaign[] = [];
  showForm = false;
  showCampaigns = false;
  cities = Object.values(Cities);
  keywordOptions: string[] = ['Marketing', 'Sales', 'Technology', 'Finance'];

  constructor(private appService: AppService) { }

  onSubmit() {
    this.appService.addCampaign(this.campaign).subscribe({
      next: (newCampaign) => {
        console.log('Campaign added successfully', newCampaign);
        this.showForm = false;
        this.resetForm();
        this.loadCampaigns();
      },
      error: (error) => {
        console.error('Error adding campaign', error);
      }
    });
  }

  toggleForm() {
    this.showForm = !this.showForm;
  }

  toggleCampaigns() {
    this.showCampaigns = !this.showCampaigns;
    if (this.showCampaigns) {
      this.loadCampaigns();
    }
  }

  loadCampaigns() {
    this.appService.getCampaigns().subscribe({
      next: (campaigns) => {
        this.campaigns = campaigns;
      },
      error: (error) => {
        console.error('Error fetching campaigns', error);
      }
    });
  }

  resetForm() {
    this.campaign = {
      id: 0,
      name: '',
      keywords: '',
      bidAmount: 0,
      campaignFund: false,
      status: '',
      city: '',
      radius: 0
    };
  }
}
