import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CampaignsComponent } from '../app/components/campaigns/campaigns.component';
import { CompaniesComponent } from '../app/components/companies/companies.component';

const routes: Routes = [
  { path: 'campaigns', component: CampaignsComponent },
  { path: 'companies', component: CompaniesComponent },
  { path: '', redirectTo: '/campaigns', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }