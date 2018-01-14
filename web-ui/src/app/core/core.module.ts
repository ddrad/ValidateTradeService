import { NgModule } from '@angular/core';
import { HeaderComponent } from './header/header.component';
import { GreetingComponent } from './greeting/greeting.component';
import { AppCommonModule } from '../common/app-common.module';
import { AppRoutingModule } from '../app-routing.module';
import { AuthService } from '../auth/auth.service';
import { HttpService } from '../common/services/http.service';
import { ValidatorService} from "../validator/validator.service";
import { StorageService } from '../common/services/storage.service';

@NgModule({
  declarations: [
    HeaderComponent,
    GreetingComponent
  ],
  imports: [
    AppCommonModule,
    AppRoutingModule
  ],
  exports: [
    AppRoutingModule,
    HeaderComponent
  ],
  providers: [
    ValidatorService,
    StorageService,
    AuthService,
    HttpService,
  ]
})
export class CoreModule {
}
