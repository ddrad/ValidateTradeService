import { StorageService } from "../common/services/storage.service";
import { Component, OnInit } from "@angular/core";
import { Trade } from "../common/model/trade.model";
import { Reply } from "../common/model/reply.model";

@Component({
    selector: 'app-result',
    templateUrl: './result.component.html',
    styleUrls: ['./result.component.css']
  })
  export class ResultComponent implements OnInit {
  
    constructor(private storageService: StorageService) {
    }
  
    replies:Reply[];

    ngOnInit() {
      this.replies = this.storageService.getFailedTrades();
    }

    isHasIssues() {
      return this.replies.length > 0;
    }

    
}