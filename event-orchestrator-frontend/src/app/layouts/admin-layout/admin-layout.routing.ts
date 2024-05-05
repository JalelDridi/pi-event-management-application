import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/maps/maps.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { TablesComponent } from '../../pages/tables/tables.component';

import { ReviewpagetestComponent } from 'src/app/pages/reviewpagetest/reviewpagetest.component';
import { ReclamationpagetestComponent } from 'src/app/pages/reclamationpagetest/reclamationpagetest.component';
import {HomeComponent} from "../../pages/home/home.component";
import {AdminDashboardComponent} from "../../pages/admin-dashboard/admin-dashboard.component";
import {AllEventsComponent} from "../../pages/all-events/all-events.component";
import {MyParticipationsComponent} from "../../pages/my-participations/my-participations.component";
import {MyEventsComponent} from "../../pages/my-events/my-events.component";
import {CreateEventRequestComponent} from "../../pages/create-event-request/create-event-request.component";
import {SubmitAFeedbackComponent} from "../../pages/submit-a-feedback/submit-a-feedback.component";
import {RessourcesComponent} from "../../pages/ressources/ressources.component";
import { AddResourceComponent } from 'src/app/pages/add-resource/add-resource.component';
import { UpdateResourceComponent } from 'src/app/pages/update-resource/update-resource.component';
import { ResourceTypeComponent } from 'src/app/pages/resource-type/resource-type.component';
import { AddResourceTypeComponent } from 'src/app/pages/add-resource-type/add-resource-type.component';
import {UserListComponent} from "../../pages/admin-pages/user-list/user-list.component";
import {UserListFinalComponent} from "../../pages/admin-pages/user-list-final/user-list-final.component";
import {RessourceListComponent} from "../../pages/admin-pages/ressource-list/ressource-list.component";
import { ResourceListByTypeComponent } from 'src/app/pages/resource-list-by-type/resource-list-by-type.component';
import { UpdateResourceTypeComponent } from 'src/app/pages/update-resource-type/update-resource-type.component';
import {ChatComponent} from "../../pages/chat/chat.component";

export const AdminLayoutRoutes: Routes = [
    { path: 'home',      component: HomeComponent },
    { path: 'admindashboard',      component: AdminDashboardComponent },
    { path: 'allevents',      component: AllEventsComponent },
    { path: 'myparticipations',      component: MyParticipationsComponent },
    { path: 'myevents',      component: MyEventsComponent },
    { path: 'requestevent',      component: CreateEventRequestComponent },
    { path: 'submitfeedback',      component: SubmitAFeedbackComponent },
    { path: 'resources',      component: RessourcesComponent },
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'tables',         component: TablesComponent },
    { path: 'icons',          component: IconsComponent },
    { path: 'maps',           component: MapsComponent },
    { path: 'reviewtest', component:ReviewpagetestComponent},
    { path: 'reclamationtest', component:ReclamationpagetestComponent},
    { path: 'maps',           component: MapsComponent },
    {path:'addResource',component:AddResourceComponent},
    {path:'updateResource',component:UpdateResourceComponent},
    {path:'resourceTypesList',component:ResourceTypeComponent},
    {path:"addResourceType", component:AddResourceTypeComponent},
    {path:"userlist", component:UserListComponent},
    {path:"userlistFinal", component:UserListFinalComponent},
    {path:"RessourceList", component:RessourceListComponent},
    {path:"resources-by-type/:resourceTypeID",component: ResourceListByTypeComponent},
    {path:"updateResourceType/:resourceTypeID",component:UpdateResourceTypeComponent},
    {path:"RessourceList", component:RessourceListComponent},
    { path: 'chat/:userId',           component: ChatComponent }

];
