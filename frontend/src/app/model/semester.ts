export class Semester {
    from: Date;
    to: Date
    current: boolean;
    registered: boolean;

    constructor(from?: Date, to?: Date, current?: boolean, registered?: boolean) {
        this.from = from || new Date();
        this.to = to || new Date();
        this.current = current || false;
        this.registered = registered || false;
    }
}