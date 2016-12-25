/**
 * Created by Qiang on 28/11/2016.
 */
import {PaginatedList} from "./paginated-list";
import {ClientGroup} from "./client-group";

export class ClientGroupList extends PaginatedList {
    _embedded: {
        clientGroups: ClientGroup[];
    }
}