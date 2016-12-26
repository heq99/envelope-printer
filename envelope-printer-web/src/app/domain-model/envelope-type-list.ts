import {EnvelopeType} from "./envelope-type";
/**
 * Created by Qiang on 26/12/2016.
 */

export class EnvelopeTypeList {
    _embedded: {
        envelopeTypes: EnvelopeType[];
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