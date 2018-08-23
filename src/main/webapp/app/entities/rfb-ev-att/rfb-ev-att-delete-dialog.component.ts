import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRfbEvAtt } from 'app/shared/model/rfb-ev-att.model';
import { RfbEvAttService } from './rfb-ev-att.service';

@Component({
    selector: 'jhi-rfb-ev-att-delete-dialog',
    templateUrl: './rfb-ev-att-delete-dialog.component.html'
})
export class RfbEvAttDeleteDialogComponent {
    rfbEvAtt: IRfbEvAtt;

    constructor(private rfbEvAttService: RfbEvAttService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.rfbEvAttService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'rfbEvAttListModification',
                content: 'Deleted an rfbEvAtt'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-rfb-ev-att-delete-popup',
    template: ''
})
export class RfbEvAttDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rfbEvAtt }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RfbEvAttDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.rfbEvAtt = rfbEvAtt;
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
