import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { ApiService } from '../../service/api.service';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.css']
})
export class BalanceComponent implements OnChanges {
  @Input() companyId: string | null = null;
  balance: number | null = null;

  constructor(private apiService: ApiService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['companyId'] && this.companyId) {
      this.loadBalance();
    }
  }

  loadBalance(): void {
    if (this.companyId) {
      this.apiService.getCompanyBalance(this.companyId).subscribe(data => this.balance = data.balance);
    }
  }
}