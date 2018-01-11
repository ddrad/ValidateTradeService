import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {HttpService} from '../common/services/http.service';
import {Subject} from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/throw';

@Injectable()
export class ValidatorService {

  constructor(private http: Http, private httpService: HttpService) {
  }

  validate(data: any) {
    const options = this.httpService.getRequestOptions();

    return this.http.post('http://localhost:4200/app-validator/validate', data.description, options)
      .map(
        (response: Response) => {
          const reply: any = response.json();
          console.log('in http response ', reply);
          return reply;
        }
      )
      .catch(
        (error: Response) => {
          return Observable.throw('Can not update ad ' + error);
        }
      );
  }
}
