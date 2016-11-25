/**
 * Created by Qiang on 05/11/2016.
 */
import {Client} from "./client";
export class ClientListPage {
    _embedded: {
        clients: Client[];
    };
    _links: {
        first: {
            href: string;
        },
        prev: {
            href: string;
        }
        self: {
            href: string;
        },
        next: {
            href: string;
        },
        last: {
            href: string;
        }
    };
    page: {
        size: number,
        totalElements: number,
        totalPages: number,
        number: number
    }
}