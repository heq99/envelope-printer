/**
 * Created by Qiang on 28/11/2016.
 */

import {Injectable} from "@angular/core";
import {baseUrl} from "./configuration";
import {Headers, Http} from "@angular/http";
import {ClientGroupList} from "../domain-model/client-group-list";
import {ClientGroup} from "../domain-model/client-group";
import {ClientService} from "./client.service";

@Injectable()
export class ClientGroupService {

    private clientGroupUrl = baseUrl + 'clientGroups';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(
        private http: Http,
        private clientSerivce: ClientService
    ) { }

    getClientGroups(url: string): Promise<ClientGroupList> {
        if (url == null) {
            url = this.clientGroupUrl.concat('?size=10');
        }
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as ClientGroupList)
            .catch(this.handleError);
    }

    getClientGroup(id: number): Promise<ClientGroup> {
        return this.http.get(this.clientGroupUrl.concat('/', id.toString()))
            .toPromise()
            .then(response => response.json() as ClientGroup)
            .catch(this.handleError);
    }

    saveClientGroup(clientGroup: ClientGroup): Promise<ClientGroup> {
        if (clientGroup._links == null) {
            return this.http.post(this.clientGroupUrl, JSON.stringify(clientGroup), {headers: this.headers})
                .toPromise()
                .then(() => clientGroup)
                .catch(this.handleError);
        } else {
            return this.http.put(clientGroup._links.self.href, JSON.stringify(clientGroup), {headers: this.headers})
                .toPromise()
                .then(() => clientGroup)
                .catch(this.handleError);
        }
    }

    deleteClientGroup(clientGroup: ClientGroup): Promise<ClientGroup> {
        return this.http.delete(clientGroup._links.self.href)
            .toPromise()
            .then(() => clientGroup)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}