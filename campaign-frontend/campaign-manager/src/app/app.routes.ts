import { Routes } from '@angular/router';
import { CampaignsComponent } from '../app/components/campaigns/campaigns.component';
//import { CompaniesComponent } from '../app/components/companies/companies.component';

export const routes: Routes = [
  { path: 'campaigns', component: CampaignsComponent },
  // { path: 'companies', component: CompaniesComponent },
  { path: '', redirectTo: '/campaigns', pathMatch: 'full' },
  { path: '**', redirectTo: '/campaigns' }
];