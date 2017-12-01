export class Course {
    id: number;
    teacherName: string;
    subjectId: number;
    subjectName: string;
    subjectCode: string;
    capacity: number;
    registered: boolean;

    constructor(
        id?: number,
        teacherName?: string,
        subjectId?: number,
        subjectName?: string,
        subjectCode?: string,
        capacity?: number,
        registered?: boolean) {

        this.id = id || 0;
        this.teacherName = teacherName || "";
        this.subjectId = subjectId || 0;
        this.subjectName = subjectName || "";
        this.subjectCode = subjectCode || "";
        this.capacity = capacity || 0;
        this.registered = registered || false;
    }
}
