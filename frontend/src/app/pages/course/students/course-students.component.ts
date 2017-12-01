import {Course} from '../../../model/course';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {MatSnackBar, MatTableDataSource} from '@angular/material';
import { CourseService } from '../../../services/course.service';
import { UserCourse } from '../../../model/user-course';

@Component({
  selector: 'app-course-students',
  templateUrl: './course-students.component.html',
  styleUrls: ['./course-students.component.css']
})
export class CourseStudentsComponent implements OnInit {
    students: Array<UserCourse>;
    courseId: number;
    isTeaching: boolean;

    displayedColumns = ['subject', 'courseId', 'username', 'email', 'mark', 'control'];
    studentsDataSource = new MatTableDataSource<UserCourse>(this.students);

    constructor(
        private snackbar: MatSnackBar,
        private courseService: CourseService,
        private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.activatedRoute.paramMap
            .subscribe(n => {
                this.courseId = parseInt(n.get('id'));
                this.loadStudents();
            });
    }

    applyStudentsFilter(filterValue: string) {
        filterValue = filterValue.trim();
        filterValue = filterValue.toLowerCase();
        this.studentsDataSource.filter = filterValue;
    }

    loadStudents() {
        this.courseService.listStudents(this.courseId)
            .subscribe(resp => {
                this.students = resp;
                this.studentsDataSource = new MatTableDataSource<UserCourse>(this.students);
            });
    }

    loadIsTeaching() {
        
    }
}
