import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IRfbEventAttendance } from 'app/shared/model/rfb-event-attendance.model';
import { RfbEventAttendanceService } from './rfb-event-attendance.service';

@Component({
    selector: 'jhi-rfb-event-attendance-update',
    templateUrl: './rfb-event-attendance-update.component.html'
})
export class RfbEventAttendanceUpdateComponent implements OnInit {
    private _rfbEventAttendance: IRfbEventAttendance;
    isSaving: boolean;
    attendanceDayDp: any;

    constructor(private rfbEventAttendanceService: RfbEventAttendanceService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rfbEventAttendance }) => {
            this.rfbEventAttendance = rfbEventAttendance;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.rfbEventAttendance.id !== undefined) {
            this.subscribeToSaveResponse(this.rfbEventAttendanceService.update(this.rfbEventAttendance));
        } else {
            this.subscribeToSaveResponse(this.rfbEventAttendanceService.create(this.rfbEventAttendance));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRfbEventAttendance>>) {
        result.subscribe((res: HttpResponse<IRfbEventAttendance>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get rfbEventAttendance() {
        return this._rfbEventAttendance;
    }

    set rfbEventAttendance(rfbEventAttendance: IRfbEventAttendance) {
        this._rfbEventAttendance = rfbEventAttendance;
    }
}
