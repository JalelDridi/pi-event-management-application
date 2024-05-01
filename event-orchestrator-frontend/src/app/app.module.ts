import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
<<<<<<< HEAD
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
=======
import { FormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
>>>>>>> main
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { ResourceListComponent } from './pages/resource-list/resource-list.component';
import { ResourceTypeComponent } from './pages/resource-type/resource-type.component';
import { ResourceService } from './pages/resource-list/resource.service';
import { AddResourceComponent } from './pages/add-resource/add-resource.component';
import { UpdateResourceComponent } from './pages/update-resource/update-resource.component';

import {ActivateAccountComponent} from "./pages/activate-account/activate-account.component";
import {CodeInputModule} from 'angular-code-input';
import {HttpTokenInterceptor} from "./userservices/interceptor/http-token.interceptor";




@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ComponentsModule,
    NgbModule,
    RouterModule,
<<<<<<< HEAD
    ReactiveFormsModule ,
    AppRoutingModule
=======
    AppRoutingModule,
    CodeInputModule,
>>>>>>> main
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    AuthLayoutComponent,
<<<<<<< HEAD
    ResourceListComponent,
    ResourceTypeComponent,
    AddResourceComponent,
    UpdateResourceComponent
  ],
  providers: [ResourceService],
=======
    ActivateAccountComponent,
  
  ],
  providers: [
    HttpClient,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpTokenInterceptor,
      multi: true
    },

  ],
>>>>>>> main
  bootstrap: [AppComponent]
})
export class AppModule { }
