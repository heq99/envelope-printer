import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';

import { MaterialModule } from '@angular/material';

import { AppComponent } from './app.component';
import { ClientService } from './services/client.service';
import { EnvelopeTypeService } from './services/envelope-type.service';
import { ClientListComponent } from './client-list.component';
import { ClientDetailsComponent } from './client-details.component';

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
            }
        ]),
        HttpModule,
        MaterialModule.forRoot()
    ],
    declarations: [
        AppComponent,
        ClientListComponent,
        ClientDetailsComponent
    ],
    providers: [ ClientService, EnvelopeTypeService ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
