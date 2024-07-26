import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';  // Import CommonModule
import { FormsModule } from '@angular/forms';    // Import FormsModule
import { AppService } from '../../../services/app.service';
import { Campaign } from './models/campaign';

@Component({
  selector: 'app-campaigns',
  standalone: true,
  imports: [CommonModule, FormsModule],  // Include the modules here
  templateUrl: './campaigns.component.html',
  styleUrls: ['./campaigns.component.css']
})
export class CampaignsComponent {
  campaign: Campaign = {
    id: 0,
    name: '',
    keywords: '',
    bidAmount: 0,
    campaignFound: false,
    status: '',
    city: '',
    radius: 0
  };

  showForm = false;

  constructor(private appService: AppService) { }

  onSubmit() {
    this.appService.addCampaign(this.campaign).subscribe({
      next: (newCampaign) => {
        console.log('Campaign added successfully', newCampaign);
        this.showForm = false;
        this.resetForm();
      },
      error: (error) => {
        console.error('Error adding campaign', error);
      }
    });
  }

  resetForm() {
    this.campaign = {
      id: 0,
      name: '',
      keywords: '',
      bidAmount: 0,
      campaignFound: false,
      status: '',
      city: '',
      radius: 0
    };
  }
}