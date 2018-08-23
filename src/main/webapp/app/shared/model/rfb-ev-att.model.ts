import {Moment} from 'moment';

export interface IRfbEvAtt {
    id?: number;
    attendanceDay?: Moment;
    rfbUserId?: number;
    rfbEventId?: number;
}

export class RfbEvAtt implements IRfbEvAtt {
    constructor(public id?: number, public attendanceDay?: Moment, public rfbUserId?: number, public rfbEventId?: number) {}
}
