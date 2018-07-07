import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRfbEvAtt } from 'app/shared/model/rfb-ev-att.model';

type EntityResponseType = HttpResponse<IRfbEvAtt>;
type EntityArrayResponseType = HttpResponse<IRfbEvAtt[]>;

@Injectable({ providedIn: 'root' })
export class RfbEvAttService {
    private resourceUrl = SERVER_API_URL + 'api/rfb-ev-atts';

    constructor(private http: HttpClient) {}

    create(rfbEvAtt: IRfbEvAtt): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rfbEvAtt);
        return this.http
            .post<IRfbEvAtt>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(rfbEvAtt: IRfbEvAtt): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rfbEvAtt);
        return this.http
            .put<IRfbEvAtt>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRfbEvAtt>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRfbEvAtt[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(rfbEvAtt: IRfbEvAtt): IRfbEvAtt {
        const copy: IRfbEvAtt = Object.assign({}, rfbEvAtt, {
            attendanceDay:
                rfbEvAtt.attendanceDay != null && rfbEvAtt.attendanceDay.isValid() ? rfbEvAtt.attendanceDay.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.attendanceDay = res.body.attendanceDay != null ? moment(res.body.attendanceDay) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((rfbEvAtt: IRfbEvAtt) => {
            rfbEvAtt.attendanceDay = rfbEvAtt.attendanceDay != null ? moment(rfbEvAtt.attendanceDay) : null;
        });
        return res;
    }
}
