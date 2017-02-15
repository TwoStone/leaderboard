import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { DragulaModule } from 'ng2-dragula';

import { HeatPlanModule } from './heat-plan/heat-plan.module';

import { EventService } from './event.service';
import { EditEventComponent } from './edit-event.component';
import { EventListComponent } from './event-list.component';

@NgModule({
    imports: [
        HeatPlanModule,
        DragulaModule,
        RouterModule,
        BrowserModule,
        FormsModule,
        CommonModule
    ],
    declarations: [
        EditEventComponent,
        EventListComponent
    ],
    providers: [
        EventService
    ]
})
export class EventModule {
}