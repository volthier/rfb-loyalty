import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IRfbEvAtt } from 'app/shared/model/rfb-ev-att.model';
import { RfbEvAttService } from './rfb-ev-att.service';
import { IRfbEvent } from 'app/shared/model/rfb-event.model';
import { RfbEventService } from 'app/entities/rfb-event';
import { IRfbUser } from 'app/shared/model/rfb-user.model';
import { RfbUserService } from 'app/entities/rfb-user';

@Component({
    selector: 'jhi-rfb-ev-att-update',
    templateUrl: './rfb-ev-att-update.component.html'
})
export class RfbEvAttUpdateComponent implements OnInit {
    private _rfbEvAtt: IRfbEvAtt;
    isSaving: boolean;

    rfbevents: IRfbEvent[];

    rfbusers: IRfbUser[];
    attendanceDayDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private rfbEvAttService: RfbEvAttService,
        private rfbEventService: RfbEventService,
        private rfbUserService: RfbUserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rfbEvAtt }) => {
            this.rfbEvAtt = rfbEvAtt;
        });
        this.rfbEventService.query().subscribe(
            (res: HttpResponse<IRfbEvent[]>) => {
                this.rfbevents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.rfbUserService.query({ filter: 'rfbevatt-is-null' }).subscribe(
            (res: HttpResponse<IRfbUser[]>) => {
                if (!this.rfbEvAtt.rfbUserId) {
                    this.rfbusers = res.body;
                } else {
                    this.rfbUserService.find(this.rfbEvAtt.rfbUserId).subscribe(
                        (subRes: HttpResponse<IRfbUser>) => {
                            this.rfbusers = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.rfbEvAtt.id !== undefined) {
            this.subscribeToSaveResponse(this.rfbEvAttService.update(this.rfbEvAtt));
        } else {
            this.subscribeToSaveResponse(this.rfbEvAttService.create(this.rfbEvAtt));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRfbEvAtt>>) {
        result.subscribe((res: HttpResponse<IRfbEvAtt>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackRfbEventById(index: number, item: IRfbEvent) {
        return item.id;
    }

    trackRfbUserById(index: number, item: IRfbUser) {
        return item.id;
    }
    get rfbEvAtt() {
        return this._rfbEvAtt;
    }

    set rfbEvAtt(rfbEvAtt: IRfbEvAtt) {
        this._rfbEvAtt = rfbEvAtt;
    }
}
