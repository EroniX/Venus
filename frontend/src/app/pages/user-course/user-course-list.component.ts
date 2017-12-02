import {Course} from '../../model/course';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {MatSnackBar, MatTableDataSource} from '@angular/material';
import { CourseService } from '../../services/course.service';
import { UserCourse } from '../../model/user-course';
import { UserService } from '../../services/user.service';
import { UserCourseService } from '../../services/user-course.service';
import { UserCourseMark } from '../../model/user-course-mark';

@Component({
  selector: 'app-user-course-list',
  templateUrl: './user-course-list.component.html',
  styleUrls: ['./user-course-list.component.css']
})
export class UserCourseListComponent implements OnInit {
    students: Array<UserCourse>;
    course: Course;
    courseId: number;

    displayedColumns = ['username', 'email', 'mark', 'control'];
    studentsDataSource = new MatTableDataSource<UserCourse>(this.students);

    constructor(
        private snackbar: MatSnackBar,
        private courseService: CourseService,
        private userService: UserService,
        private userCourseService: UserCourseService,
        private activatedRoute: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.activatedRoute.paramMap
            .subscribe(n => {
                this.courseId = parseInt(n.get('id'));
                this.loadStudents();
                this.loadCourse();
            });
    }

    applyStudentsFilter(filterValue: string): void {
        filterValue = filterValue.trim();
        filterValue = filterValue.toLowerCase();
        this.studentsDataSource.filter = filterValue;
    }

    loadStudents(): void {
        this.userCourseService.findByCourseId(this.courseId)
            .subscribe(resp => {
                this.students = resp;
                this.studentsDataSource = new MatTableDataSource<UserCourse>(this.students);
            });
    }

    saveMark(userId: number, mark: number): void {
        console.log("asd" + this.courseId);
        let userCourseMark = new UserCourseMark(
            this.courseId, 
            userId, 
            mark);
        
        this.userCourseService.mark(userCourseMark)
            .subscribe(resp => {
                if(resp == true) {
                    this.snackbar.open('Succesfully marked', "Close", {
                        duration: 3000
                    });
                }
            });
    }

    deleteUserCourse(userId: number) {
        
    }

    loadCourse(): void {
        this.courseService.findById(this.courseId)
            .subscribe(resp => this.course = resp);
    }

    canWriteMark(): boolean {
        return this.course != null
            ? this.course.teacherName == this.userService.user.email
            : false;
    }
}
