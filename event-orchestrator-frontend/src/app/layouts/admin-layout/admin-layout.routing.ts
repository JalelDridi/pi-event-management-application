import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/maps/maps.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { TablesComponent } from '../../pages/tables/tables.component';
<<<<<<< HEAD
import { ResourceListComponent } from 'src/app/pages/resource-list/resource-list.component';
import { AddResourceComponent } from 'src/app/pages/add-resource/add-resource.component';
import { UpdateResourceComponent } from 'src/app/pages/update-resource/update-resource.component';
=======
import { ReviewpagetestComponent } from 'src/app/pages/reviewpagetest/reviewpagetest.component';
>>>>>>> main

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'tables',         component: TablesComponent },
    { path: 'icons',          component: IconsComponent },
    { path: 'maps',           component: MapsComponent },
<<<<<<< HEAD
    {path:'/resource-list',component:ResourceListComponent},
    {path:'/addResource',component:AddResourceComponent},
    {path:'/updateResource',component:UpdateResourceComponent},
=======
    { path: 'reviewtest', component:ReviewpagetestComponent}
>>>>>>> main
];
