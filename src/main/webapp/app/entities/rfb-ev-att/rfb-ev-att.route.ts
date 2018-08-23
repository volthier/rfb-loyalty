import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { RfbEvAtt } from 'app/shared/model/rfb-ev-att.model';
import { RfbEvAttService } from './rfb-ev-att.service';
import { RfbEvAttComponent } from './rfb-ev-att.component';
import { RfbEvAttDetailComponent } from './rfb-ev-att-detail.component';
import { RfbEvAttUpdateComponent } from './rfb-ev-att-update.component';
import { RfbEvAttDeletePopupComponent } from './rfb-ev-att-delete-dialog.component';
import { IRfbEvAtt } from 'app/shared/model/rfb-ev-att.model';

@Injectable({ providedIn: 'root' })
export class RfbEvAttResolve implements Resolve<IRfbEvAtt> {
    constructor(private service: RfbEvAttService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((rfbEvAtt: HttpResponse<RfbEvAtt>) => rfbEvAtt.body);
        }
        return Observable.of(new RfbEvAtt());
    }
}

export const rfbEvAttRoute: Routes = [
    {
        path: 'rfb-ev-att',
        component: RfbEvAttComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbEvAtts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rfb-ev-att/:id/view',
        component: RfbEvAttDetailComponent,
        resolve: {
            rfbEvAtt: RfbEvAttResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbEvAtts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rfb-ev-att/new',
        component: RfbEvAttUpdateComponent,
        resolve: {
            rfbEvAtt: RfbEvAttResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbEvAtts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rfb-ev-att/:id/edit',
        component: RfbEvAttUpdateComponent,
        resolve: {
            rfbEvAtt: RfbEvAttResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbEvAtts'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rfbEvAttPopupRoute: Routes = [
    {
        path: 'rfb-ev-att/:id/delete',
        component: RfbEvAttDeletePopupComponent,
        resolve: {
            rfbEvAtt: RfbEvAttResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbEvAtts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
