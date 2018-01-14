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
    serverErrorMessage: string;

    ngOnInit() {
      this.replies = this.storageService.getFailedTrades();
    }

    isHasIssues() {
      return this.replies.length > 0;
    }

    isServerError(){
      this.serverErrorMessage = this.storageService.getErrorMessage();
      if(this.serverErrorMessage != null && this.serverErrorMessage.length > 0){
        return true;
      }
      return false;
    }

    
}