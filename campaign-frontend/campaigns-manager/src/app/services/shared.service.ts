import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  private selectedCompanyId: number | null = null;

  setSelectedCompanyId(companyId: number): void {
    this.selectedCompanyId = companyId;
  }

  getSelectedCompanyId(): number | null {
    return this.selectedCompanyId;
  }
}
