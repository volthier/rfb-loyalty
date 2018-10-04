import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { IRfbLocation, RfbLocation } from 'app/shared/model/rfb-location.model';
import { RfbLocationService } from './rfb-location.service';
import { RfbLocationComponent } from './rfb-location.component';
import { RfbLocationDetailComponent } from './rfb-location-detail.component';
import { RfbLocationUpdateComponent } from './rfb-location-update.component';
import { RfbLocationDeletePopupComponent } from './rfb-location-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class RfbLocationResolve implements Resolve<IRfbLocation> {
    constructor(private service: RfbLocationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((rfbLocation: HttpResponse<RfbLocation>) => rfbLocation.body);
        }
        return Observable.of(new RfbLocation());
    }
}

export const rfbLocationRoute: Routes = [
    {
        path: 'rfb-location',
        component: RfbLocationComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            defaultSort: 'id,asc',
            pageTitle: 'RfbLocations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rfb-location/:id/view',
        component: RfbLocationDetailComponent,
        resolve: {
            rfbLocation: RfbLocationResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'RfbLocations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rfb-location/new',
        component: RfbLocationUpdateComponent,
        resolve: {
            rfbLocation: RfbLocationResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'RfbLocations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rfb-location/:id/edit',
        component: RfbLocationUpdateComponent,
        resolve: {
            rfbLocation: RfbLocationResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'RfbLocations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rfbLocationPopupRoute: Routes = [
    {
        path: 'rfb-location/:id/delete',
        component: RfbLocationDeletePopupComponent,
        resolve: {
            rfbLocation: RfbLocationResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'RfbLocations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
