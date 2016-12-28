/**
 * Created by Qiang on 29/10/2016.
 */

export class Client {
    company: string;
    contact1: string;
    contact2: string;
    contact3: string;
    address: string;
    telephone1: string;
    telephone2: string;
    status: string;
    _links: {
        self: {
            href: string;
        },
        client: {
            href: string;
        }
    };
}