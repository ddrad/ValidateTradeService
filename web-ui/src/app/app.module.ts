import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { AuthModule } from './auth/auth.module';
import {CoreModule} from './core/core.module';
import {AppRoutingModule} from './app-routing.module';
import { ValidatorComponent } from './validator/validator.component';
import {AppCommonModule} from "./common/app-common.module";
import { ResultComponent } from './result/result.component';
import { ReplyItemComponent } from './result/reply-item/reply-item.component';


@NgModule({
  declarations: [
    AppComponent,
    ErrorPageComponent,
    ValidatorComponent,
    ResultComponent,
    ReplyItemComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AuthModule,
    AppRoutingModule,
    AppCommonModule,
    CoreModule
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
