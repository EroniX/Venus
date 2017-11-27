export class Subject {
    id: number;
    name: string;
    code: string;
    registered: boolean;

    constructor(
        id?: number, 
        name?: string, 
        code?: string,
        registered?: boolean) {
        
        this.id = id || 0;
        this.name = name || "";
        this.code = code || "";
        this.registered = registered || false;
    }
}