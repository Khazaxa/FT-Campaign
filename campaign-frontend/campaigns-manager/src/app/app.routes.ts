import { Routes } from '@angular/router';
import { CampaignsComponent } from './components/campaigns/campaigns.component';
import { CompaniesComponent } from './components/companies/companies.component';

export const routes: Routes = [
  { path: 'campaigns', component: CampaignsComponent },
   { path: 'companies', component: CompaniesComponent },
  { path: '', redirectTo: '/campaigns', pathMatch: 'full' },
  { path: '**', redirectTo: '/campaigns' }
];
