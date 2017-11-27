export class Course {
    id: number;
    teacherName: string;
    subjectName: string;
    subjectCode: string;
    limit: number;
    registered: boolean;

    constructor(
        id?: number,
        teacherName?: string,
        subjectName?: string,
        subjectCode?: string,
        limit?: number,
        registered?: boolean) {

        this.id = id || 0;
        this.teacherName = teacherName || "";
        this.subjectName = subjectName || "";
        this.subjectCode = subjectCode || "";
        this.limit = limit || 0;
        this.registered = registered || false;
    }
}