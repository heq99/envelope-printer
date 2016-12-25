/**
 * Created by Qiang on 25/10/2016.
 */
import {Injectable} from '@angular/core';
import {Headers, Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Client} from '../domain-model/client';
import {ClientList} from '../domain-model/client-list';
import {baseUrl} from "./configuration";

@Injectable()
export class ClientService {

    private clientUrl = baseUrl + 'clients';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) { }

    getClients(url: string): Promise<ClientList> {
        if (url == null) {
            url = this.clientUrl.concat('?size=10');
        }
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as ClientList)
            .catch(this.handleError);
    }

    getClient(id: number): Promise<Client> {
        return this.http.get(this.clientUrl.concat('/', id.toString()))
            .toPromise()
            .then(response => response.json() as Client)
            .catch(this.handleError);
    }

    saveClient(client: Client): Promise<Client> {
        if (client._links == null) {
            return this.http.post(this.clientUrl, JSON.stringify(client), {headers: this.headers})
                .toPromise()
                .then(() => client)
                .catch(this.handleError);
        } else {
            return this.http.put(client._links.self.href, JSON.stringify(client), {headers: this.headers})
                .toPromise()
                .then(() => client)
                .catch(this.handleError);
        }
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}