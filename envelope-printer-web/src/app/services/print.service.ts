/**
 * Created by Qiang on 26/12/2016.
 */
import {Injectable} from "@angular/core";
import {baseUrl} from "./configuration";
import {Headers, Http, Response, ResponseContentType} from "@angular/http";

@Injectable()
export class PrintService {

    private printUrl = baseUrl + 'print';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) { }

    print(clientId: number, envelopeType: string): Promise<Blob> {
        let body: string = '[' + clientId + ']';
        return this.http.post(this.printUrl.concat('/', envelopeType), body, {headers: this.headers, responseType: ResponseContentType.Blob})
            .toPromise()
            .then((response: Response) => response.blob())
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}