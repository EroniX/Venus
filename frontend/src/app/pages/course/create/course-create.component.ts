import { ActivatedRoute, ParamMap } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Training } from '../../../model/Training';
import { MatSnackBar, MatTableDataSource } from '@angular/material';
import { Course } from '../../../model/course';
import { CourseService } from '../../../services/course.service';

@Component({
  selector: 'app-course-create',
  templateUrl: './course-create.component.html',
  styleUrls: ['./course-create.component.css']
})
export class CourseCreateComponent {
    constructor(
        private snackbar: MatSnackBar, 
        private courseService: CourseService) { 
    }

    create(course: Course) {
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
