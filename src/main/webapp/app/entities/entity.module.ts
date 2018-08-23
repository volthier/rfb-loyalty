import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { RfbloyaltyRfbLocationModule } from './rfb-location/rfb-location.module';
import { RfbloyaltyRfbEventModule } from './rfb-event/rfb-event.module';
import { RfbloyaltyRfbEvAttModule } from './rfb-ev-att/rfb-ev-att.module';
import { RfbloyaltyRfbUserModule } from './rfb-user/rfb-user.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        RfbloyaltyRfbLocationModule,
        RfbloyaltyRfbEventModule,
        RfbloyaltyRfbEvAttModule,
        RfbloyaltyRfbUserModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RfbloyaltyEntityModule {}
