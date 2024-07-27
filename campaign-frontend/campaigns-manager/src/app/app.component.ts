import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CampaignsComponent } from './components/campaigns/campaigns.component';
import { CompaniesComponent } from './components/companies/companies.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CampaignsComponent, CompaniesComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'campaign-manager';
}
