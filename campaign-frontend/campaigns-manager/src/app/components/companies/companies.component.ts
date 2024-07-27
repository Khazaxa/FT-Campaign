import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Company } from './models/company';
import { AppService } from '../../services/app.service';

@Component({
  selector: 'app-companies',
  standalone: true,
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css'],
  imports: [CommonModule, FormsModule]
})
export class CompaniesComponent {
  company: Company = {
    id: 0,
    name: '',
    emeraldAccountBalance: 0,
    address: '',
    city: '',
    country: '',
    email: '',
    phoneNumber: ''
  };

  companies: Company[] = [];
  showForm = false;
  showCompanies = false;

  constructor(private appService: AppService) { }

  onSubmit() {
    this.appService.addCompany(this.company).subscribe({
      next: (newCompany) => {
        console.log('Company added successfully', newCompany);
        this.showForm = false;
        this.resetForm();
        this.loadCompanies();
      },
      error: (error) => {
        console.error('Error adding company', error);
      }
    });
  }

  toggleForm() {
    this.showForm = !this.showForm;
  }

  toggleCompanies() {
    this.showCompanies = !this.showCompanies;
    if (this.showCompanies) {
      this.loadCompanies();
    }
  }

  loadCompanies() {
    this.appService.getCompanies().subscribe({
      next: (companies) => {
        this.companies = companies;
      },
      error: (error) => {
        console.error('Error fetching companies', error);
      }
    });
  }

  deleteCompany(companyId: number) {
    this.appService.deleteCompany(companyId).subscribe({
      next: () => {
        console.log('Company deleted successfully');
        this.loadCompanies();
      },
      error: (error) => {
        console.error('Error deleting company', error);
      }
    });
  }

  fetchBalance(company: Company) {
    this.appService.getCompanyBalance(company.id).subscribe({
      next: (balance) => {
        console.log('Company balance fetched successfully', balance);
        company.emeraldAccountBalance = balance;
      },
      error: (error) => {
        console.error('Error fetching company balance', error);
      }
    });
  }

  resetForm() {
    this.company = {
      id: 0,
      name: '',
      emeraldAccountBalance: 0,
      address: '',
      city: '',
      country: '',
      email: '',
      phoneNumber: ''
    };
  }
}
