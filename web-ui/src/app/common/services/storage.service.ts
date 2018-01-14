import { Injectable } from "@angular/core";
import { Reply } from "../model/reply.model";

@Injectable()
export class StorageService {

    replies: Reply[]=[];
    serverErrorMessage: string = null;


    setFailedTrades(replies: Reply[]) {
        this.replies = replies;
    }

    getFailedTrades() {
        return this.replies.slice();
    }

    setErrorMessage(errMsg: string) {
        this.serverErrorMessage = errMsg;
    }

    getErrorMessage(){
        return this.serverErrorMessage;
    }

}