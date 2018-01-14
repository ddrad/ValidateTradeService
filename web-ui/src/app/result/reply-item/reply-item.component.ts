import { Trade } from "../../common/model/trade.model";
import { Input, Component, OnInit } from "@angular/core";
import { Reply } from "../../common/model/reply.model";

@Component({
    selector: 'app-reply-item',
    templateUrl: './reply-item.component.html',
    styleUrls: ['./reply-item.component.css']
  })
  export class ReplyItemComponent implements OnInit {
  
    @Input() reply: Reply;
  
    constructor() { }
  
    ngOnInit() {
    }
  
    disply(item: string) {
      return JSON.stringify(item);
    }
  }