import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';import { RouterModule } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClipboardModule } from 'ngx-clipboard';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/maps/maps.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { TablesComponent } from '../../pages/tables/tables.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AddReviewComponent } from 'src/app/pages/add-review/add-review.component';
import { ReviewpagetestComponent } from 'src/app/pages/reviewpagetest/reviewpagetest.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { NgxStarRatingModule } from 'ngx-star-rating';
import { ReviewService } from 'src/app/reviewservices/review.service';
import { BadWordsFilterService } from 'src/app/reviewservices/badwordsfilter.service';
import { ReviewlistComponent } from 'src/app/pages/reviewlist/reviewlist.component';
import { CustomDatePipe } from 'src/app/custom-date.pipe';
import { AddReclamationsComponent } from 'src/app/pages/add-reclamation/add-reclamation.component';
import { ReclamationpagetestComponent } from 'src/app/pages/reclamationpagetest/reclamationpagetest.component';
import {ResourceListComponent} from "../../pages/resource-list/resource-list.component";
import {ResourceService} from "../../pages/resource-list/resource.service";
import { ReclamationStatisticsService } from 'src/app/reviewservices/reclamationstatistics.service';
import { ReclamationsStatisticsComponent } from '../../pages/reclamations-statistics/reclamations-statistics.component';
import { NgxEchartsModule } from 'ngx-echarts';
import { ReviewStatisticsComponent } from 'src/app/pages/reviews-statistics/reviews-statistics.component';




@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(AdminLayoutRoutes),
        FormsModule,
        HttpClientModule,
        NgbModule,
        ClipboardModule,
        MatButtonModule,
        NgxEchartsModule.forRoot({
            echarts: () => import('echarts'),
        }),
        ReactiveFormsModule,
        MatCardModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule,
        MatListModule,
        MatSelectModule,
        NgxStarRatingModule,

    ],
    declarations: [
        DashboardComponent,
        UserProfileComponent,
        AddReviewComponent,
        ReviewpagetestComponent,
        ReviewlistComponent,
        TablesComponent,
        IconsComponent,
        MapsComponent,
        CustomDatePipe,
        AddReclamationsComponent,
        ReclamationpagetestComponent,
        ReclamationsStatisticsComponent,
        ReviewStatisticsComponent,
        CustomDatePipe
    ],
    exports: [
        ReviewlistComponent
    ],
    providers: [
        ReviewService,
        ResourceService,
        BadWordsFilterService,
        ReclamationStatisticsService,
        DatePipe
    ]
})

export class AdminLayoutModule {}
