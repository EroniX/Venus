import { ActivatedRoute, ParamMap } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { TrainingService } from '../../services/training.service';
import { Training } from '../../model/Training';
import { MatSnackBar, MatTableDataSource } from '@angular/material';
import { Course } from '../../model/course';
import { CourseService } from '../../services/course.service';
import { Role } from '../../helpers/role';
import { UserCourseService } from '../../services/user-course.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
    courses: Array<Course>;
    subjectId: number;

    displayedColumns = ['id', 'name', 'code', 'teacher', 'capacity', 'control'];
    coursesDataSource = new MatTableDataSource<Course>(this.courses);

    constructor(
        private snackbar: MatSnackBar,
        private courseService: CourseService,
        private userCourseService: UserCourseService,
        private activatedRoute: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.activatedRoute.paramMap
            .subscribe(n => {
                this.subjectId = parseInt(n.get('id'));
                this.loadCourses();
            });
    }

    applyCoursesFilter(filterValue: string): void {
        filterValue = filterValue.trim();
        filterValue = filterValue.toLowerCase();
        this.coursesDataSource.filter = filterValue;
    }

    register(courseId: number): void {
        this.userCourseService.create(courseId)
            .subscribe(resp => {
                if(resp == true) {
                    this.loadCourses();
                    this.snackbar.open('Course succesfully registered', "Close", {
                        duration: 3000
                    });
            }
        });
    }

    unregister(courseId: number): void {
        this.userCourseService.delete(courseId)
            .subscribe(resp => {
                if(resp == true) {
                    this.loadCourses();
                    this.snackbar.open('Course succesfully unregistered', "Close", {
                        duration: 3000
                    });
                }
            });
    }

    loadCourses() {
        this.courseService.findBySubjectId(this.subjectId)
            .subscribe(resp => {
                this.courses = resp
                this.coursesDataSource = new MatTableDataSource<Course>(this.courses);
            });
    }

    courseCreateRole(): string {
        return Role.COURSE_CREATE;
    }
}
