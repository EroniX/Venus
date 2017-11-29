import { ActivatedRoute, ParamMap } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { TrainingService } from '../../services/training.service';
import { Training } from '../../model/Training';
import { MatSnackBar, MatTableDataSource } from '@angular/material';
import { Course } from '../../model/course';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
    courses: Array<Course>;
    subjectId?: number;

    displayedColumns = ['id', 'name', 'code', 'teacher', 'limit', 'control'];
    coursesDataSource = new MatTableDataSource<Course>(this.courses);
    
    constructor(
        private snackbar: MatSnackBar, 
        private courseService: CourseService,
        private activatedRoute: ActivatedRoute) { 
    }

    ngOnInit() {
        this.activatedRoute.paramMap
            .subscribe(n => {
                this.subjectId = parseInt(n.get('id'));
                this.loadCourses();
            });
    }

    applyCoursesFilter(filterValue: string) {
        filterValue = filterValue.trim(); 
        filterValue = filterValue.toLowerCase();
        this.coursesDataSource.filter = filterValue;
    }

    register(course: Course) {
      this.courseService.register(course)
          .subscribe(resp => {
            if(resp == true) {
                this.loadCourses();
                this.snackbar.open('Course succesfully registered', "Close", {
                  duration: 3000
                });
            }                
        });
    }

    unregister(course: Course) {
      this.courseService.unregister(course)
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
        this.courseService.list(this.subjectId)
            .subscribe(resp => {
                this.courses = resp
                this.coursesDataSource = new MatTableDataSource<Course>(this.courses);
            });
    }
}
