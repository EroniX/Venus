export class User {
    username: string;
    password: string;
    email: string;
    roles: Array<string>;

    constructor(username?: string, password?: string, email?: string, roles?: Array<string>) {
        this.username = username || "";
        this.password = password || "";
        this.email = email || "";
        this.roles = roles;
    }
}
