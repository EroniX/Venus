import { Component, OnInit } from '@angular/core';
import { TrainingService } from '../../services/training.service';
import { Training } from '../../model/Training';
import { MatSnackBar } from '@angular/material';
import { Subject } from '../../model/subject';
import { SubjectService } from '../../services/subject.service';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {
    subjects: Array<Subject>

    constructor(private snackbar: MatSnackBar, private subjectService: SubjectService) { 
    }

    ngOnInit() {
        this.loadSubjects();
    }

    loadSubjects() {
      this.subjectService.list()
         .subscribe(resp => this.subjects = resp);
    }
}
