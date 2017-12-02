export class UserCourseMark {
    courseId: number;
    studentId: number;
    mark: number;

    constructor(
        courseId?: number,
        studentId?: number,
        mark?: number) {
            
        this.courseId = courseId || 0;
        this.studentId = studentId || 0;
        this.mark = mark || 0;
    }
}