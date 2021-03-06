import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { ValidatorService } from "./validator.service";
import { StorageService } from '../common/services/storage.service';
import { Trade } from '../common/model/trade.model';
import { Reply } from '../common/model/reply.model';

@Component({
  selector: 'app-validator',
  templateUrl: './validator.component.html',
  styleUrls: ['./validator.component.css']
})
export class ValidatorComponent implements OnInit, OnDestroy {

  adForm: FormGroup;
  subscription: Subscription;
  
  constructor(private route: ActivatedRoute,
    private validatorService: ValidatorService,
    private storageService: StorageService,
    private router: Router) {
  }

  ngOnInit() {
    this.initForm();
  }

  onSubmit() {
    this.storageService.setErrorMessage(null);
    this.validatorService.validate(this.adForm.value)
      .subscribe(
      (replies: Reply[]) => {
        this.storageService.setFailedTrades(replies);
        this.router.navigate(['/result'], { relativeTo: this.route });
      },
      err => {
        this.storageService.setErrorMessage(err);
        this.router.navigate(['/result'], { relativeTo: this.route });
      }
      );


    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

  onDeleteProduct(index: number) {
    (<FormArray>this.adForm.get('products')).removeAt(index);
  }

  initForm() {
    let description = '';
    this.adForm = new FormGroup({
      'description': new FormControl(description, Validators.required),
    });
  }

  ngOnDestroy(): void {
    // this.subscription.unsubscribe();
  }
}
