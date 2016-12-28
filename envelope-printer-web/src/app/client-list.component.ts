/**
 * Created by Qiang on 29/10/2016.
 */
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Client } from './domain-model/client';
import { ClientList } from "./domain-model/client-list";
import { ClientService } from './services/client.service';
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
            .then((clientList: ClientList) => {
                this.clients = clientList._embedded.clients;

                this.totalClients = clientList.page.totalElements;

                this.totalPagesNum = clientList.page.totalPages;
                this.currentPageNum = clientList.page.number + 1;
                this.pageSize = clientList.page.size;
                if (clientList._links.first == null) {
                    this.firstPageUrl = null;
                } else {
                    this.firstPageUrl = clientList._links.first.href;
                }
                if (clientList._links.prev == null) {
                    this.prevPageUrl = null;
                } else {
                    this.prevPageUrl = clientList._links.prev.href;
                }
                if (clientList._links.next == null) {
                    this.nextPageUrl = null;
                } else {
                    this.nextPageUrl = clientList._links.next.href;
                }
                if (clientList._links.last == null) {
                    this.lastPageUrl = null;
                } else {
                    this.lastPageUrl = clientList._links.last.href;
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