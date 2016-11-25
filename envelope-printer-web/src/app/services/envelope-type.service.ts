/**
 * Created by Qiang on 06/11/2016.
 */
import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { EnvelopeType } from '../domain-model/envelope-type';

@Injectable()
export class EnvelopeTypeService {

    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) { }

    getEnvelopeType(url: string): Promise<EnvelopeType> {
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as EnvelopeType)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

}