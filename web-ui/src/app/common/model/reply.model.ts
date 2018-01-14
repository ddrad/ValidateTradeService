import { Trade } from "./trade.model";

export class Reply {

    public name: string;
    public trades: Trade[];

    constructor(name:string, trades: Trade[]) {
        this.name = name;
        this.trades = trades;
    }
}