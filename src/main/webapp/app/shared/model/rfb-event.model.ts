import { Moment } from 'moment';
import { IRfbEvAtt } from 'app/shared/model//rfb-ev-att.model';

export interface IRfbEvent {
    id?: number;
    eventDate?: Moment;
    eventCode?: string;
    rfbLocationId?: number;
    rfbEvAtts?: IRfbEvAtt[];
}

export class RfbEvent implements IRfbEvent {
    constructor(
        public id?: number,
        public eventDate?: Moment,
        public eventCode?: string,
        public rfbLocationId?: number,
        public rfbEvAtts?: IRfbEvAtt[]
    ) {}
}
