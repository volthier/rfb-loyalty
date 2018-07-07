import { Moment } from 'moment';

export interface IRfbEvAtt {
    id?: number;
    attendanceDay?: Moment;
    rfbEventId?: number;
    rfbUserId?: number;
}

export class RfbEvAtt implements IRfbEvAtt {
    constructor(public id?: number, public attendanceDay?: Moment, public rfbEventId?: number, public rfbUserId?: number) {}
}
