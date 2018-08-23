import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRfbEvAtt } from 'app/shared/model/rfb-ev-att.model';

@Component({
    selector: 'jhi-rfb-ev-att-detail',
    templateUrl: './rfb-ev-att-detail.component.html'
})
export class RfbEvAttDetailComponent implements OnInit {
    rfbEvAtt: IRfbEvAtt;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rfbEvAtt }) => {
            this.rfbEvAtt = rfbEvAtt;
        });
    }

    previousState() {
        window.history.back();
    }
}
