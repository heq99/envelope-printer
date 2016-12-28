/**
 * Created by Qiang on 06/11/2016.
 */
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { Envelope } from '../domain-model/envelope';
import {baseUrl} from "./configuration";
import {EnvelopeList} from "../domain-model/envelope-list";

@Injectable()
export class EnvelopeService {

    private envelopeUrl = baseUrl + 'envelopes';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) { }

    getEnvelopes(): Promise<EnvelopeList> {
        return this.http.get(this.envelopeUrl)
            .toPromise()
            .then(response => response.json() as EnvelopeList)
            .catch(this.handleError);
    }

    getEnvelope(url: string): Promise<Envelope> {
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Envelope)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

}