import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RfbloyaltySharedModule } from 'app/shared';
import {
    RfbEvAttComponent,
    RfbEvAttDetailComponent,
    RfbEvAttUpdateComponent,
    RfbEvAttDeletePopupComponent,
    RfbEvAttDeleteDialogComponent,
    rfbEvAttRoute,
    rfbEvAttPopupRoute
} from './';

const ENTITY_STATES = [...rfbEvAttRoute, ...rfbEvAttPopupRoute];

@NgModule({
    imports: [RfbloyaltySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RfbEvAttComponent,
        RfbEvAttDetailComponent,
        RfbEvAttUpdateComponent,
        RfbEvAttDeleteDialogComponent,
        RfbEvAttDeletePopupComponent
    ],
    entryComponents: [RfbEvAttComponent, RfbEvAttUpdateComponent, RfbEvAttDeleteDialogComponent, RfbEvAttDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RfbloyaltyRfbEvAttModule {}
