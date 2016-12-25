/**
 * Created by Qiang on 05/11/2016.
 */
import {Client} from "./client";
import {PaginatedList} from "./paginated-list";
export class ClientList extends PaginatedList {
    _embedded: {
        clients: Client[];
    };
}