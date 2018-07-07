/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RfbloyaltyTestModule } from '../../../test.module';
import { RfbEvAttDetailComponent } from 'app/entities/rfb-ev-att/rfb-ev-att-detail.component';
import { RfbEvAtt } from 'app/shared/model/rfb-ev-att.model';

describe('Component Tests', () => {
    describe('RfbEvAtt Management Detail Component', () => {
        let comp: RfbEvAttDetailComponent;
        let fixture: ComponentFixture<RfbEvAttDetailComponent>;
        const route = ({ data: of({ rfbEvAtt: new RfbEvAtt(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [RfbloyaltyTestModule],
                declarations: [RfbEvAttDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RfbEvAttDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RfbEvAttDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.rfbEvAtt).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
