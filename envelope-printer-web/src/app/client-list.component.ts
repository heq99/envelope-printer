/**
 * Created by Qiang on 29/10/2016.
 */
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Client } from './domain-model/client';
import { ClientListPage } from "./domain-model/client-list";
import { EnvelopeType } from "./domain-model/envelope-type";
import { ClientService } from './services/client.service';
import { EnvelopeTypeService } from './services/envelope-type.service';
import 'material-design-lite/dist/material.indigo-pink.min.css';


@Component({
    selector: 'client-list',
    templateUrl: 'client-list.component.html',
    styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

    clients: Client[] = [];
    totalClients: number;

    totalPagesNum: number;
    currentPageNum: number;
    pageSize: number;
    firstPageUrl: string;
    prevPageUrl: string;
    nextPageUrl: string;
    lastPageUrl: string;

    constructor(
        private clientService: ClientService,
        private envelopeTypeService: EnvelopeTypeService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.loadClients(null);
    }

    goToDetail(client: Client): void {
        let id = client._links.self.href.substring(client._links.self.href.lastIndexOf('/')+1);
        this.router.navigate(['/client', id]);
    }

    addNewClient(): void {
        this.router.navigate(['/client/new']);
    }

    loadClients(url: string): void {
        this.clientService.getClients(url)
            .then((clientListPage: ClientListPage) => {
                this.clients = clientListPage._embedded.clients;
                this.clients.forEach(
                    client => this.envelopeTypeService.getEnvelopeType(client._links.envelopeType.href)
                        .then((envelopeType: EnvelopeType) => client.envelopeType = envelopeType.type)
                );

                console.info(this.clients);

                this.totalClients = clientListPage.page.totalElements;

                this.totalPagesNum = clientListPage.page.totalPages;
                this.currentPageNum = clientListPage.page.number + 1;
                this.pageSize = clientListPage.page.size;
                this.firstPageUrl = clientListPage._links.first.href;
                if (clientListPage._links.prev == null) {
                    this.prevPageUrl = null;
                } else {
                    this.prevPageUrl = clientListPage._links.prev.href;
                }
                if (clientListPage._links.next == null) {
                    this.nextPageUrl = null;
                } else {
                    this.nextPageUrl = clientListPage._links.next.href;
                }
                this.lastPageUrl = clientListPage._links.last.href;
            });
    }

    isFirstPage(): boolean {
        return this.currentPageNum == 1;
    }

    isLastPage(): boolean {
        return this.currentPageNum == this.totalPagesNum;
    }

}