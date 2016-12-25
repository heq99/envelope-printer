/**
 * Created by Qiang on 28/11/2016.
 */

export class PaginatedList {
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