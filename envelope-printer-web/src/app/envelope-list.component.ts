import {Component, OnInit} from "@angular/core";
import {Envelope} from "./domain-model/envelope";
import {EnvelopeService} from "./services/envelope.service";
import {Router} from "@angular/router";
import {EnvelopeList} from "./domain-model/envelope-list";
/**
 * Created by Qiang on 28/12/2016.
 */

@Component({
    selector: 'envelope-list',
    templateUrl: 'envelope-list.component.html',
    styleUrls: ['./envelope-list.component.css']
})
export class EnvelopeListComponent implements OnInit {

    envelopes: Envelope[] = [];

    constructor(
        private envelopeService: EnvelopeService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.loadEnvelopes(null);
    }

    loadEnvelopes(url: string): void {
        this.envelopeService.getEnvelopes()
            .then((envelopeList: EnvelopeList) => {
                this.envelopes = envelopeList._embedded.envelopes;
            });
    }
}