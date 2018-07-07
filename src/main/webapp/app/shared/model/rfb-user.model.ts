export interface IRfbUser {
    id?: number;
    userName?: string;
    rfbLocationId?: number;
}

export class RfbUser implements IRfbUser {
    constructor(public id?: number, public userName?: string, public rfbLocationId?: number) {}
}
