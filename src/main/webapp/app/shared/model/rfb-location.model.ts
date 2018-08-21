export interface IRfbLocation {
    id?: number;
    locationName?: string;
    runDayOfWeek?: number;
    rvbEventId?: number;
}

export class RfbLocation implements IRfbLocation {
    constructor(public id?: number, public locationName?: string, public runDayOfWeek?: number, public rvbEventId?: number) {}
}
