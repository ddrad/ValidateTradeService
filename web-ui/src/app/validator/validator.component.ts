import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import {ValidatorService} from "./validator.service";

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
              private router: Router) {
  }

  ngOnInit() {
    this.initForm();
  }

  onSubmit() {
     console.log('Form Submited');
      this.validatorService.validate(this.adForm.value)
        .subscribe(
          (ad: any) => {
            // this.adService.adsInfo[ad.index] = ad;
            // this.adsChangedSbj.next(this.adsInfo.slice());
            // this.adSelectedSbj.next(ad);
            this.router.navigate(['/ads', ad.id, ad.index], {relativeTo: this.route});
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
