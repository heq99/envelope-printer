import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';

import { MdSidenavModule, MdToolbarModule, MdInputModule, MdCardModule,
         MdButtonModule, MdMenuModule, MdListModule, MdIconModule
       } from '@angular/material';

import { AgGridModule } from "ag-grid-angular/main";


import { AppComponent } from './app.component';
import { ClientService } from './services/client.service';
import { ClientGroupService } from './services/client-group-service';
import { EnvelopeService } from './services/envelope.service';
import { PrintService } from "./services/print.service";
import { ClientListComponent } from './client-list.component';
import { ClientDetailsComponent } from './client-details.component';
import { ClientGroupListComponent } from "./client-group-list.component";
import { ClientGroupDetailsComponent } from "./client-group-details.component";
import { EnvelopeListComponent } from "./envelope-list.component";
import { EnvelopeDetailsComponent } from "./envelope-details.component";

@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        AgGridModule.withComponents([]),
        RouterModule.forRoot([
            {
                path: '',
                redirectTo: '/clients',
                pathMatch: 'full'
            },
            {
                path: 'clients',
                component: ClientListComponent
            },
            {
                path: 'client/:id',
                component: ClientDetailsComponent
            },
            {
                path: 'clientGroups',
                component: ClientGroupListComponent
            },
            {
                path: 'clientGroup/:id',
                component: ClientGroupDetailsComponent
            },
            {
                path: 'envelopes',
                component: EnvelopeListComponent
            },
            {
                path: 'envelope/:id',
                component: EnvelopeDetailsComponent
            }
        ]),
        HttpModule,
        MdSidenavModule,
        MdToolbarModule,
        MdInputModule,
        MdCardModule,
        MdButtonModule,
        MdMenuModule,
        MdListModule,
        MdIconModule
    ],
    declarations: [
        AppComponent,
        ClientListComponent,
        ClientDetailsComponent,
        ClientGroupListComponent,
        ClientGroupDetailsComponent,
        EnvelopeListComponent,
        EnvelopeDetailsComponent
    ],
    // entryComponents: [dialog.component],
    providers: [ ClientService, ClientGroupService, EnvelopeService, PrintService ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
