import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IRfbLocation } from 'app/shared/model/rfb-location.model';
import { RfbLocationService } from './rfb-location.service';
import { IRfbEvent } from 'app/shared/model/rfb-event.model';
import { RfbEventService } from 'app/entities/rfb-event';

@Component({
    selector: 'jhi-rfb-location-update',
    templateUrl: './rfb-location-update.component.html'
})
export class RfbLocationUpdateComponent implements OnInit {
    private _rfbLocation: IRfbLocation;
    isSaving: boolean;

    rfbevents: IRfbEvent[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private rfbLocationService: RfbLocationService,
        private rfbEventService: RfbEventService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rfbLocation }) => {
            this.rfbLocation = rfbLocation;
        });
        this.rfbEventService.query().subscribe(
            (res: HttpResponse<IRfbEvent[]>) => {
                this.rfbevents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.rfbLocation.id !== undefined) {
            this.subscribeToSaveResponse(this.rfbLocationService.update(this.rfbLocation));
        } else {
            this.subscribeToSaveResponse(this.rfbLocationService.create(this.rfbLocation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRfbLocation>>) {
        result.subscribe((res: HttpResponse<IRfbLocation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get rfbLocation() {
        return this._rfbLocation;
    }

    set rfbLocation(rfbLocation: IRfbLocation) {
        this._rfbLocation = rfbLocation;
    }
}
