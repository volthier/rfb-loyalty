import { Moment } from 'moment';

export interface IRfbEvent {
    id?: number;
    eventDate?: Moment;
    eventCode?: string;
    rfbEventAttendanceId?: number;
}

export class RfbEvent implements IRfbEvent {
    constructor(public id?: number, public eventDate?: Moment, public eventCode?: string, public rfbEventAttendanceId?: number) {}
}
