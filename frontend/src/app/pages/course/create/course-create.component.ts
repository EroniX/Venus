import { Component, OnInit, Input } from '@angular/core';
import { Training } from '../../../model/Training';
import { MatSnackBar, MatTableDataSource } from '@angular/material';
import { Course } from '../../../model/course';
import { CourseService } from '../../../services/course.service';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { SubjectService } from '../../../services/subject.service';
import { Subject } from '../../../model/subject';

@Component({
  selector: 'app-course-create',
  templateUrl: './course-create.component.html',
  styleUrls: ['./course-create.component.css']
})
export class CourseCreateComponent implements OnInit {
    courseCreateForm: FormGroup = new FormGroup({
        subject: new FormControl('', [Validators.required]),
        capacity: new FormControl('', [Validators.required])
    });

    subjects: Array<Subject>;

    constructor(
        private snackbar: MatSnackBar,
        private courseService: CourseService,
        private subjectService: SubjectService) {
    }

    ngOnInit(): void {
        this.loadSubjects();
    }

    create() {
        this.courseService.create(this.course)
          .subscribe(resp => {
            if(resp == true) {
                this.snackbar.open('Course succesfully created', "Close", {
                    duration: 3000
                });
            }
        });
    }

    get course(): Course {
        let course = new Course();
        course.subjectId = this.subject.value;
        course.capacity = this.capacity.value;
        return course;
    }

    get subject(): AbstractControl {
        return this.courseCreateForm.get('subject');
    }
    
    get capacity(): AbstractControl {
        return this.courseCreateForm.get('capacity');
    }

    loadSubjects() {
        this.subjectService.findAll()
            .subscribe(n => this.subjects = n);
    }
}
