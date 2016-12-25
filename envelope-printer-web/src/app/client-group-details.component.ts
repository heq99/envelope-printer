/**
 * Created by Qiang on 08/12/2016.
 */

import {Component, OnInit, Input} from "@angular/core";
import {Location} from "@angular/common";
import {ClientGroup} from "./domain-model/client-group";
import {ClientGroupService} from "./services/client-group-service";
import {ClientService} from "./services/client.service";
import {ActivatedRoute, Params} from "@angular/router";
import {ClientList} from "./domain-model/client-list";
import {Client} from "./domain-model/client";
@Component({
    selector: 'client-group-details',
    templateUrl: './client-group-details.component.html',
    styleUrls: ['./client-group-details.component.css']
})
export class ClientGroupDetailsComponent implements OnInit {

    @Input()
    clientGroup: ClientGroup;
    clients: Client[] = [];

    constructor(
        private clientGroupService: ClientGroupService,
        private clientService: ClientService,
        private route: ActivatedRoute,
        private location: Location
    ) { }

    ngOnInit(): void {
        this.route.params.forEach((params: Params) => {
            if (params['id'] == "new") {
                this.clientGroup = new ClientGroup();
            } else {
                let id = +params['id'];
                this.clientGroupService.getClientGroup(id)
                    .then((clientGroup: ClientGroup) => {
                        this.clientGroup = clientGroup;
                        this.clientService.getClients(clientGroup._links.clients.href)
                            .then((clientList: ClientList) => {
                                this.clients = clientList._embedded.clients;
                                this.clientGroup.numberOfClients = this.clients.length;
                            });
                    });
            }
        });
    }

    save(): void {
        this.clientGroupService.saveClientGroup(this.clientGroup)
            .then(() => this.goBack());
    }

    goBack(): void {
        this.location.back();
    }
}
