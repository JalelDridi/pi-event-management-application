import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {APP_INITIALIZER, NgModule} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
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
import {HttpTokenInterceptor} from "./userservices/interceptor/http-token.interceptor";
import {CodeInputModule} from "angular-code-input";
import { TestComponent } from './test/test.component';
import { HomeComponent } from './pages/home/home.component';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { AllEventsComponent } from './pages/all-events/all-events.component';
import { MyParticipationsComponent } from './pages/my-participations/my-participations.component';
import { MyEventsComponent } from './pages/my-events/my-events.component';
import { CreateEventRequestComponent } from './pages/create-event-request/create-event-request.component';
import { SubmitAFeedbackComponent } from './pages/submit-a-feedback/submit-a-feedback.component';
import { RessourcesComponent } from './pages/ressources/ressources.component';
import { AddResourceTypeComponent } from './pages/add-resource-type/add-resource-type.component';
import { RessourceListComponent } from './pages/admin-pages/ressource-list/ressource-list.component';
import { UserListFinalComponent } from './pages/admin-pages/user-list-final/user-list-final.component';
import { UserListComponent } from './pages/admin-pages/user-list/user-list.component';
import { ResourceListByTypeComponent } from './pages/resource-list-by-type/resource-list-by-type.component';
import { UpdateResourceTypeComponent } from './pages/update-resource-type/update-resource-type.component';
import { EditEventComponent } from './pages/edit-event/edit-event.component';
import { EventDetailsComponent } from './pages/event-details/event-details.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {EventService} from "./services/eventservices/eventservice/event.service";
import { UserFeedbacksComponent } from './pages/user-feedbacks/user-feedbacks.component';
import { AdminEventListComponent } from './pages/admin-pages/admin-event-list/admin-event-list.component';
import {MatInputModule} from "@angular/material/input";
import {AdminLayoutModule} from "./layouts/admin-layout/admin-layout.module";


@NgModule({
    imports: [
        MatDialogModule,
        MatSelectModule,
        MatFormFieldModule,
        MatButtonModule,
        CommonModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        ComponentsModule,
        NgbModule,
        RouterModule,
        ReactiveFormsModule,
        AppRoutingModule,
        CodeInputModule,
        ReactiveFormsModule,
        AppRoutingModule,
        NgOptimizedImage,
        MatInputModule,
        AdminLayoutModule,
    ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    AuthLayoutComponent,
    ActivateAccountComponent,
    ResourceListComponent,
    ResourceTypeComponent,
    AddResourceComponent,
    UpdateResourceComponent,
    TestComponent,
    HomeComponent,
    AdminDashboardComponent,
    AllEventsComponent,
    MyParticipationsComponent,
    MyEventsComponent,
    CreateEventRequestComponent,
    SubmitAFeedbackComponent,
    RessourcesComponent,
    AddResourceTypeComponent,
    RessourceListComponent,
    UserListFinalComponent,
    UserListComponent,
    UserFeedbacksComponent,
    ResourceListByTypeComponent,
    UpdateResourceTypeComponent,
    EditEventComponent,
    EventDetailsComponent,
    AddResourceTypeComponent,
    AdminEventListComponent
  ],
  providers: [
    ResourceService,
    EventService,
    HttpClient,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpTokenInterceptor,
      multi: true
    },
    // Add other core services here

    // Feature-specific components (consider grouping if necessary)
    ResourceTypeComponent,
    AddResourceComponent,
    UpdateResourceComponent,
    ActivateAccountComponent,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
