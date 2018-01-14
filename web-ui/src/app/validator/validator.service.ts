import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { HttpService } from '../common/services/http.service';
import { Subject } from 'rxjs/Subject';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import { StorageService } from '../common/services/storage.service';
import { Reply } from '../common/model/reply.model';
import { Trade } from '../common/model/trade.model';

@Injectable()
export class ValidatorService {

  constructor(private http: Http, private httpService: HttpService, private storageService: StorageService) {
  }

  validate(data: any) {
    const options = this.httpService.getRequestOptions();
    return this.http.post('http://localhost:4200/app-validator/validate', data.description, options)
      .map(
      (response: Response) => {
        return response.json();
      }
      )
      .map(
      (data: any) => {
        let replies: Reply[] = [];
        data.forEach(element => {
          for (let key in element) {
            let trades: Trade[] = [];
            if (element[key].length > 0) {
              element[key].forEach(item => {
                let trade:Trade = item;
                trades.push(trade);
              });
              let reply: Reply = new Reply(key, trades);
              replies.push(reply);
            }
          }
        });
        return replies;
      })
      .catch(
      (error: Response) => {
        let err = error.json();
        return Observable.throw(err.error+' '+err.message);
      }
      );
  }
}
