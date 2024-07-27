import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Campaign } from './models/campaign';
import { AppService } from '../../services/app.service';
import { Cities } from './models/Cities';
import { SharedService } from '../../services/shared.service';

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
    campaignFund: 0,
    status: false,
    city: '',
    radius: 0
  };

  campaigns: Campaign[] = [];
  showForm = false;
  showCampaigns = false;
  cities = Object.values(Cities);
  keywordOptions: string[] = ['Marketing', 'Sales', 'Technology', 'Finance'];
  isEditing = false;

  constructor(private appService: AppService, private sharedService: SharedService) { }

  onSubmit() {
    if (this.isEditing) {
      this.appService.updateCampaign(this.campaign).subscribe({
        next: (updatedCampaign) => {
          console.log('Campaign updated successfully', updatedCampaign);
          this.showForm = false;
          this.resetForm();
          this.loadCampaigns();
        },
        error: (error) => {
          console.error('Error updating campaign', error);
        }
      });
    } else {
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

  editCampaign(campaign: Campaign) {
    this.campaign = { ...campaign };
    this.isEditing = true;
    this.showForm = true;
  }

  deleteCampaign(campaignId: number) {
    this.appService.deleteCampaign(campaignId).subscribe({
      next: () => {
        console.log('Campaign deleted successfully');
        this.campaigns = this.campaigns.filter(c => c.id !== campaignId);
      },
      error: (error) => {
        console.error('Error deleting campaign', error);
      }
    });
  }

  activateCampaign(campaignId: number) {
    const companyId = this.sharedService.getSelectedCompanyId();
    if (companyId === null) {
      console.error('No company selected');
      return;
    }
    this.appService.activateCampaign(campaignId, companyId).subscribe({
      next: () => {
        console.log('Campaign activated successfully');
        this.loadCampaigns();
      },
      error: (error) => {
        console.error('Error activating campaign', error);
        alert(`Error activating campaign: ${error.message}`);
      }
    });
  }

  deactivateCampaign(campaignId: number) {
    this.appService.deactivateCampaign(campaignId).subscribe({
      next: () => {
        console.log('Campaign deactivated successfully');
        this.loadCampaigns();
      },
      error: (error) => {
        console.error('Error deactivating campaign', error);
      }
    });
  }

  resetForm() {
    this.campaign = {
      id: 0,
      name: '',
      keywords: '',
      bidAmount: 0,
      campaignFund: 0,
      status: false,
      city: '',
      radius: 0
    };
    this.isEditing = false;
  }
}
