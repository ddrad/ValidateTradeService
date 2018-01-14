import { Injectable } from "@angular/core";
import { Reply } from "../model/reply.model";

@Injectable()
export class StorageService {

    replies: Reply[]=[];

    setFailedTrades(replies: Reply[]) {
        this.replies = replies;
    }

    getFailedTrades() {
        return this.replies.slice();
    }

}