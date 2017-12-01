export class UserCourse {
    subjectName: string;
    courseId: number;
    username: string;
    email: string;
    mark: number;

    constructor(
        subjectName?: string,
        courseId?: number,
        username?: string,
        email?: string,
        mark?: number) {
        
        this.subjectName = subjectName || "";
        this.courseId = courseId || 0;
        this.username = username || "";
        this.email = email || "";
        this.mark = mark || 0;
    }
}