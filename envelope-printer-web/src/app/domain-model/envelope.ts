/**
 * Created by Qiang on 25/10/2016.
 */
export class Envelope {
    name: string;
    width: number;
    height: number;
    _links: {
        self: {
            href: string;
        },
        envelope: {
            href: string;
        },
        envelopeFields: {
            href: string;
        }
    }
}
