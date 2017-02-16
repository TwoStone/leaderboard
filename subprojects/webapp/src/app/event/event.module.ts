import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { DragulaModule } from 'ng2-dragula';

import { HeatPlanModule } from './heat-plan/heat-plan.module';

import { EditEventComponent } from './edit-event.component';
import { EventListComponent } from './event-list.component';
import { EventService } from './event.service';

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