/**
 * Created by Qiang on 29/10/2016.
 */
import {EnvelopeType} from "./envelope-type";

export class Client {
    company: string;
    contact1: string;
    contact2: string;
    contact3: string;
    address: string;
    telephone1: string;
    telephone2: string;
    status: string;
    envelopeType: string;
    _links: {
        self: {
            href: string;
        },
        client: {
            href: string;
        },
        envelopeType: {
            href: string;
        }
    };
}