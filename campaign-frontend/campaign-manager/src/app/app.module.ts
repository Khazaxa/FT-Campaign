import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import { CampaignsComponent } from './components/campaigns/campaigns.component';
import { CompaniesComponent } from './components/companies/companies.component';

@NgModule({
  declarations: [
    AppComponent,
    CampaignsComponent,
    CompaniesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }