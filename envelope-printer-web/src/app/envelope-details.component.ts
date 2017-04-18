/**
 * Created by Qiang on 30/12/2016.
 */

import {Component, OnInit, Input} from "@angular/core";
import {Envelope} from "./domain-model/envelope";
import {EnvelopeService} from "./services/envelope.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';
@Component({
    selector: 'envelope-details',
    templateUrl: './envelope-details.component.html',
    styleUrls: ['./envelope-details.component.css']
})
export class EnvelopeDetailsComponent implements OnInit {

    @Input()
    envelope: Envelope;

    constructor(
        private envelopeService: EnvelopeService,
        private route: ActivatedRoute,
        private location: Location
    ) { }

    ngOnInit(): void {
        this.route.params.forEach((params: Params) => {
            if (params['id'] == "new") {
                this.envelope = new Envelope();
            } else {
                let id = +params['id'];
                this.envelopeService.getEnvelopeById(id)
                    .then((envelope: Envelope) => this.envelope = envelope);
            }
        })
    }

    save(): void {

    }

    goBack(): void {
        this.location.back();
    }

    getEnvelopeImageUrl(envelope: Envelope): string {
        return 'url("/public/images/envelopes/' + envelope.imageFileName + '")';
    }
}