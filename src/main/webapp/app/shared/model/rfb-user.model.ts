import {IRfbEvAtt} from 'app/shared/model//rfb-ev-att.model';

export interface IRfbUser {
    id?: number;
    userName?: string;
    rfbLocationId?: number;
    rfbEvAtts?: IRfbEvAtt[];
}

export class RfbUser implements IRfbUser {
    constructor(public id?: number, public userName?: string, public rfbLocationId?: number, public rfbEvAtts?: IRfbEvAtt[]) {}
}
