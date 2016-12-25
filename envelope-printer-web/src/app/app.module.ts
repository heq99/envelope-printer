import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';

import { MaterialModule } from '@angular/material';

import { AppComponent } from './app.component';
import { ClientService } from './services/client.service';
import { ClientGroupService } from './services/client-group-service';
import { EnvelopeTypeService } from './services/envelope-type.service';
import { ClientListComponent } from './client-list.component';
import { ClientDetailsComponent } from './client-details.component';
import { ClientGroupListComponent } from "./client-group-list.component";
import { ClientGroupDetailsComponent } from "./client-group-details.component";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
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
            }
        ]),
        HttpModule,
        MaterialModule.forRoot()
    ],
    declarations: [
        AppComponent,
        ClientListComponent,
        ClientDetailsComponent,
        ClientGroupListComponent,
        ClientGroupDetailsComponent
    ],
    // entryComponents: [dialog.component],
    providers: [ ClientService, ClientGroupService, EnvelopeTypeService ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
