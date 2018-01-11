import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GreetingComponent} from './core/greeting/greeting.component';
import {ErrorPageComponent} from './error-page/error-page.component';
import {ValidatorComponent} from "./validator/validator.component";

const appRouts: Routes = [
  {path: '', component: GreetingComponent, pathMatch: 'full'},
  {path: 'validator', component: ValidatorComponent},
  {path: 'page-not-found', component: ErrorPageComponent, data: {message: 'Page not found!'}},
  {path: '**', redirectTo: '/page-not-found'}
];

@NgModule({
  imports : [
    RouterModule.forRoot(appRouts)
  ],
  exports : [
    RouterModule
  ]
})
export class AppRoutingModule {}
