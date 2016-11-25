/**
 * Created by Qiang on 29/10/2016.
 */
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';

import { Client } from './domain-model/client';
import { ClientService } from './services/client.service';

@Component({
    selector: 'client-details',
    templateUrl: './client-details.component.html',
    styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {

    @Input()
    client: Client;
    action: string;

    constructor(
        private clientService: ClientService,
        private route: ActivatedRoute,
        private location: Location
    ) { }

    ngOnInit(): void {
        this.route.params.forEach((params: Params) => {
            if (params['id'] == "new") {
                this.client = new Client();
                this.client.status = "ACTIVE";
                this.action = "new";
            } else {
                let id = +params['id'];
                this.clientService.getClient(id)
                    .then((client: Client) => this.client = client);
                this.action = "edit";
            }
        })

    }

    save(): void {
        this.clientService.saveClient(this.client)
            .then(() => this.goBack())
    }

    goBack(): void {
        this.location.back();
    }
}
