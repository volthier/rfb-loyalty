import { IRfbEvent } from 'app/shared/model//rfb-event.model';

export interface IRfbLocation {
    id?: number;
    locationName?: string;
    runDayOfWeek?: number;
    rfbEvents?: IRfbEvent[];
}

export class RfbLocation implements IRfbLocation {
    constructor(public id?: number, public locationName?: string, public runDayOfWeek?: number, public rfbEvents?: IRfbEvent[]) {}
}
