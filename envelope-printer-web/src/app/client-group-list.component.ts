/**
 * Created by Qiang on 30/11/2016.
 */

import {Component, OnInit} from "@angular/core";
import {ClientGroup} from "./domain-model/client-group";
import {ClientGroupService} from "./services/client-group-service";
import {ClientService} from "./services/client.service";
import {Router} from "@angular/router";
import {ClientGroupList} from "./domain-model/client-group-list";
import {ClientList} from "./domain-model/client-list";
import {EnvelopeService} from "./services/envelope.service";
import {Envelope} from "./domain-model/envelope";
@Component({
    selector: 'client-group-list',
    templateUrl: 'client-group-list.component.html',
    styleUrls: ['./client-group-list.component.css']
})
export class ClientGroupListComponent implements OnInit {

    clientGroups: ClientGroup[] = [];
    totalClientGroups: number;

    totalPagesNum: number;
    currentPageNum: number;
    pageSize: number;
    firstPageUrl: string;
    prevPageUrl: string;
    nextPageUrl: string;
    lastPageUrl: string;

    constructor(
        private clientGroupService: ClientGroupService,
        private clientService: ClientService,
        private envelopeService: EnvelopeService,
        private router: Router,
    ) { }

    ngOnInit(): void {
        this.loadClientGroups(null);
    }

    goToDetail(clientGroup: ClientGroup): void {
        let id = clientGroup._links.self.href.substring(clientGroup._links.self.href.lastIndexOf('/')+1);
        this.router.navigate(['/clientGroup', id]);
    }

    addNewClientGroup(): void {
        this.router.navigate(['/clientGroup/new']);
    }

    loadClientGroups(url: string): void {
        this.clientGroupService.getClientGroups(url)
            .then((clientGroupList: ClientGroupList) => {
                this.clientGroups = clientGroupList._embedded.clientGroups;
                this.clientGroups.forEach(
                    clientGroup => {
                        this.clientService.getClients(clientGroup._links.clients.href)
                            .then((clientList: ClientList) => clientGroup.numberOfClients = clientList._embedded.clients.length);
                        this.envelopeService.getEnvelope(clientGroup._links.envelope.href)
                            .then((envelope: Envelope) => clientGroup.envelope = envelope.name);
                    }
                );

                this.totalClientGroups = clientGroupList.page.totalElements;

                this.totalPagesNum = clientGroupList.page.totalPages;
                this.currentPageNum = clientGroupList.page.number + 1;
                this.pageSize = clientGroupList.page.size;
                if (clientGroupList._links.first == null) {
                    this.firstPageUrl = null;
                } else {
                    this.firstPageUrl = clientGroupList._links.first.href;
                }
                if (clientGroupList._links.prev == null) {
                    this.prevPageUrl = null;
                } else {
                    this.prevPageUrl = clientGroupList._links.prev.href;
                }
                if (clientGroupList._links.next == null) {
                    this.nextPageUrl = null;
                } else {
                    this.nextPageUrl = clientGroupList._links.next.href;
                }
                if (clientGroupList._links.last == null) {
                    this.lastPageUrl = null;
                } else {
                    this.lastPageUrl = clientGroupList._links.last.href;
                }
            });
    }

    isFirstPage(): boolean {
        return this.currentPageNum == 1;
    }

    isLastPage(): boolean {
        return this.currentPageNum == this.totalPagesNum;
    }

}
