import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
<<<<<<< HEAD
<<<<<<< HEAD
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
=======
import { FormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
>>>>>>> main
import { RouterModule } from '@angular/router';
=======
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
>>>>>>> d6ac6270762f6bfdc65512c387a04c92ec2e7e27
import { AppComponent } from './app.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';
<<<<<<< HEAD
=======
import {ActivateAccountComponent} from "./pages/activate-account/activate-account.component";
import {CodeInputModule} from 'angular-code-input';
import {HttpTokenInterceptor} from "./userservices/interceptor/http-token.interceptor";
>>>>>>> d6ac6270762f6bfdc65512c387a04c92ec2e7e27

import { ResourceListComponent } from './pages/resource-list/resource-list.component';
import { ResourceTypeComponent } from './pages/resource-type/resource-type.component';
import { ResourceService } from './pages/resource-list/resource.service';
import { AddResourceComponent } from './pages/add-resource/add-resource.component';
import { UpdateResourceComponent } from './pages/update-resource/update-resource.component';
<<<<<<< HEAD

import {ActivateAccountComponent} from "./pages/activate-account/activate-account.component";
import {CodeInputModule} from 'angular-code-input';
import {HttpTokenInterceptor} from "./userservices/interceptor/http-token.interceptor";


=======
import {RouterModule} from "@angular/router";
>>>>>>> d6ac6270762f6bfdc65512c387a04c92ec2e7e27


@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ComponentsModule,
    NgbModule,
    RouterModule,
<<<<<<< HEAD
<<<<<<< HEAD
    ReactiveFormsModule ,
    AppRoutingModule
=======
    AppRoutingModule,
    CodeInputModule,
>>>>>>> main
=======
    ReactiveFormsModule ,
    AppRoutingModule,
    CodeInputModule,
>>>>>>> d6ac6270762f6bfdc65512c387a04c92ec2e7e27
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    AuthLayoutComponent,
<<<<<<< HEAD
<<<<<<< HEAD
    ResourceListComponent,
    ResourceTypeComponent,
    AddResourceComponent,
    UpdateResourceComponent
  ],
  providers: [ResourceService],
=======
=======
    ResourceTypeComponent,
    AddResourceComponent,
    UpdateResourceComponent,
>>>>>>> d6ac6270762f6bfdc65512c387a04c92ec2e7e27
    ActivateAccountComponent,

  ],
  providers: [
    ResourceService,
    HttpClient,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpTokenInterceptor,
      multi: true
    },
    // Add any other providers you might have here
  ],
<<<<<<< HEAD
>>>>>>> main
=======
>>>>>>> d6ac6270762f6bfdc65512c387a04c92ec2e7e27
  bootstrap: [AppComponent]
})
export class AppModule { }
