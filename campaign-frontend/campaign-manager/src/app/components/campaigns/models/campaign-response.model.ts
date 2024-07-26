export interface CampaignResponse {
    id: number;
    name: string;
    keywords: string;
    bidAmount: number;
    campaignFound: boolean;
    status: string;
    city: string;
    radius: number;
  }