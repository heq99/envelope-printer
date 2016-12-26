/**
 * Created by Qiang on 29/10/2016.
 */
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';

import { Client } from './domain-model/client';
import { ClientService } from './services/client.service';
import {EnvelopeTypeService} from "./services/envelope-type.service";
import {EnvelopeType} from "./domain-model/envelope-type";
import {EnvelopeTypeList} from "./domain-model/envelope-type-list";
import {PrintService} from "./services/print.service";

@Component({
    selector: 'client-details',
    templateUrl: './client-details.component.html',
    styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {

    @Input()
    client: Client;

    envelopeTypes: EnvelopeType[] = [];

    constructor(
        private clientService: ClientService,
        private envelopeTypeService: EnvelopeTypeService,
        private printService: PrintService,
        private route: ActivatedRoute,
        private location: Location
    ) { }

    ngOnInit(): void {
        this.envelopeTypeService.getEnvelopeTypes()
            .then((envelopeList: EnvelopeTypeList) => {
                this.envelopeTypes = envelopeList._embedded.envelopeTypes;
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

    print(client: Client, envelopeType: EnvelopeType): void {
        var reader = new FileReader();
        this.printService.print(this.clientService.getClientId(client), envelopeType.type)
            .then((blob: Blob) => reader.readAsDataURL(blob));
        reader.onloadend = function(e) {
            window.open(reader.result, 'PDF');
        }
    }
}
