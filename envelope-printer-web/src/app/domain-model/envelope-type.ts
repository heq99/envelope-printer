/**
 * Created by Qiang on 25/10/2016.
 */
export class EnvelopeType {
    type: string;
    width: number;
    height: number;
    _links: {
        self: {
            href: string;
        },
        envelopeType: {
            href: string;
        },
        envelopeFields: {
            href: string;
        }
    }
}
