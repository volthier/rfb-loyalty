import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRfbEventAttendance } from 'app/shared/model/rfb-event-attendance.model';
import { RfbEventAttendanceService } from './rfb-event-attendance.service';

@Component({
    selector: 'jhi-rfb-event-attendance-delete-dialog',
    templateUrl: './rfb-event-attendance-delete-dialog.component.html'
})
export class RfbEventAttendanceDeleteDialogComponent {
    rfbEventAttendance: IRfbEventAttendance;

    constructor(
        private rfbEventAttendanceService: RfbEventAttendanceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.rfbEventAttendanceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'rfbEventAttendanceListModification',
                content: 'Deleted an rfbEventAttendance'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-rfb-event-attendance-delete-popup',
    template: ''
})
export class RfbEventAttendanceDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rfbEventAttendance }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RfbEventAttendanceDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.rfbEventAttendance = rfbEventAttendance;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
