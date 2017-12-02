export class UserCourse {
    userId: number;
    username: string;
    email: string;
    mark: number;

    constructor(
        userId?: number,
        username?: string,
        email?: string,
        mark?: number) {

        this.userId = userId || 0;
        this.username = username || "";
        this.email = email || "";
        this.mark = mark || 0;
    }
}