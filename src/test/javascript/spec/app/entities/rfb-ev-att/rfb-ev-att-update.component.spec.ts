/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { RfbloyaltyTestModule } from '../../../test.module';
import { RfbEvAttUpdateComponent } from 'app/entities/rfb-ev-att/rfb-ev-att-update.component';
import { RfbEvAttService } from 'app/entities/rfb-ev-att/rfb-ev-att.service';
import { RfbEvAtt } from 'app/shared/model/rfb-ev-att.model';

describe('Component Tests', () => {
    describe('RfbEvAtt Management Update Component', () => {
        let comp: RfbEvAttUpdateComponent;
        let fixture: ComponentFixture<RfbEvAttUpdateComponent>;
        let service: RfbEvAttService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [RfbloyaltyTestModule],
                declarations: [RfbEvAttUpdateComponent]
            })
                .overrideTemplate(RfbEvAttUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RfbEvAttUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RfbEvAttService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RfbEvAtt(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.rfbEvAtt = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RfbEvAtt();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.rfbEvAtt = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
