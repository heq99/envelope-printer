import {Envelope} from "./envelope";
/**
 * Created by Qiang on 26/12/2016.
 */

export class EnvelopeList {
    _embedded: {
        envelopes: Envelope[];
    };
    _links: {
        self: {
            href: string;
        },
        profile: {
            href: string;
        },
        search: {
            href: string;
        }
    }
}