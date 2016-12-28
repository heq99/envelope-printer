import {ClientList} from "./client-list";
/**
 * Created by Qiang on 28/11/2016.
 */

export class ClientGroup {
    groupName: string;
    numberOfClients: number;
    envelope: string;
    _links: {
        self: {
            href: string;
        },
        clientGroup: {
            href: string;
        },
        clients: {
            href: string;
        },
        envelope: {
            href: string;
        }
    }
}