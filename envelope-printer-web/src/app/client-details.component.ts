/**
 * Created by Qiang on 29/10/2016.
 */
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';

import { Client } from './domain-model/client';
import { ClientService } from './services/client.service';
import {EnvelopeService} from "./services/envelope.service";
import {Envelope} from "./domain-model/envelope";
import {EnvelopeList} from "./domain-model/envelope-list";
import {PrintService} from "./services/print.service";

@Component({
    selector: 'client-details',
    templateUrl: './client-details.component.html',
    styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {

    @Input()
    client: Client;

    envelopes: Envelope[] = [];

    constructor(
        private clientService: ClientService,
        private envelopeService: EnvelopeService,
        private printService: PrintService,
        private route: ActivatedRoute,
        private location: Location
    ) { }

    ngOnInit(): void {
        this.envelopeService.getEnvelopes()
            .then((envelopeList: EnvelopeList) => {
                this.envelopes = envelopeList._embedded.envelopes;
            });
        this.route.params.forEach((params: Params) => {
            if (params['id'] == "new") {
                this.client = new Client();
                this.client.status = "ACTIVE";
            } else {
                let id = +params['id'];
                this.clientService.getClient(id)
                    .then((client: Client) => this.client = client);
            }
        })
    }

    save(): void {
        this.clientService.saveClient(this.client)
            .then(() => this.goBack());
    }

    goBack(): void {
        this.location.back();
    }

    print(client: Client, envelope: Envelope): void {
        var reader = new FileReader();
        this.printService.print(this.clientService.getClientId(client), envelope.name)
            .then((blob: Blob) => reader.readAsDataURL(blob));
        reader.onloadend = function(e) {
            window.open(reader.result, 'PDF');
        }
    }
}
