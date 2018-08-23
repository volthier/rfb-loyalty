/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RfbloyaltyTestModule } from '../../../test.module';
import { RfbEvAttDeleteDialogComponent } from 'app/entities/rfb-ev-att/rfb-ev-att-delete-dialog.component';
import { RfbEvAttService } from 'app/entities/rfb-ev-att/rfb-ev-att.service';

describe('Component Tests', () => {
    describe('RfbEvAtt Management Delete Component', () => {
        let comp: RfbEvAttDeleteDialogComponent;
        let fixture: ComponentFixture<RfbEvAttDeleteDialogComponent>;
        let service: RfbEvAttService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [RfbloyaltyTestModule],
                declarations: [RfbEvAttDeleteDialogComponent]
            })
                .overrideTemplate(RfbEvAttDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RfbEvAttDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RfbEvAttService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
