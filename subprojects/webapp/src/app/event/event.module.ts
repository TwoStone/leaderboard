import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { EventService } from './event.service';
import { EditEventComponent } from './edit-event.component';
import { EventListComponent } from './event-list.component';

@NgModule({
    imports: [
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