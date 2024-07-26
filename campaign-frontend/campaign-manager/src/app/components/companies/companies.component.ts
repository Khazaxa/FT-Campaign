import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../service/api.service';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit {
  companies: any[] = [];
  selectedCompanyId: string | null = null;

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.loadCompanies();
  }

  loadCompanies(): void {
    this.apiService.getCompanies().subscribe(data => this.companies = data);
  }

  loadBalance(id: string): void {
    this.selectedCompanyId = id;
  }

  deleteCompany(id: string): void {
    this.apiService.deleteCompany(id).subscribe(() => this.loadCompanies());
  }
}