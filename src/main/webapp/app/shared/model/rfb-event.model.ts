import {Moment} from 'moment';
import {IRfbEvAtt} from 'app/shared/model//rfb-ev-att.model';

export interface IRfbEvent {
    id?: number;
    eventDate?: Moment;
    eventCode?: string;
    rfbEvAtts?: IRfbEvAtt[];
    rfbLocationId?: number;
}

export class RfbEvent implements IRfbEvent {
    constructor(
        public id?: number,
        public eventDate?: Moment,
        public eventCode?: string,
        public rfbEvAtts?: IRfbEvAtt[],
        public rfbLocationId?: number
    ) {}
}
