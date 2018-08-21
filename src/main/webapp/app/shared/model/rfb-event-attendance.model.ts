import { Moment } from 'moment';

export interface IRfbEventAttendance {
    id?: number;
    attendanceDay?: Moment;
}

export class RfbEventAttendance implements IRfbEventAttendance {
    constructor(public id?: number, public attendanceDay?: Moment) {}
}
