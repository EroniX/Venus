export class Training {
    id: number;
    name: string;
    registered: boolean;

    constructor(id?: number, name?: string, registered?: boolean) {
        this.id = id || 0;
        this.name = name || "";
        this.registered = registered || false;
    }
}