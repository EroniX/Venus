import {Component, ViewChild, OnInit} from '@angular/core';
import {MatTableDataSource, MatSort} from '@angular/material';
import {MatSnackBar} from '@angular/material';
import {Subject} from '../../model/subject';
import {SubjectService} from '../../services/subject.service';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {
    allSubjects: Array<Subject>
    mySubjects: Array<Subject>

    displayedColumns = ['name', 'code', 'control'];
    allSubjectsDataSource = new MatTableDataSource<Subject>(this.allSubjects);
    mySubjectsDataSource = new MatTableDataSource<Subject>(this.mySubjects);

    constructor(
        private snackbar: MatSnackBar, 
        private subjectService: SubjectService) { 
    }

    applyAllSubjectFilter(filterValue: string) {
        filterValue = filterValue.trim(); 
        filterValue = filterValue.toLowerCase();
        this.allSubjectsDataSource.filter = filterValue;
    }

    applyMySubjectFilter(filterValue: string) {
        filterValue = filterValue.trim(); 
        filterValue = filterValue.toLowerCase();
        this.mySubjectsDataSource.filter = filterValue;
    }

    ngOnInit() {
        this.loadSubjects();
    }

    loadSubjects() {
      this.subjectService.list()
         .subscribe(resp => {
                this.allSubjects = resp.filter(n => !n.registered); 
                this.mySubjects = resp.filter(n => n.registered); 

                this.allSubjectsDataSource = new MatTableDataSource<Subject>(this.allSubjects);
                this.mySubjectsDataSource = new MatTableDataSource<Subject>(this.mySubjects);
          });
    }
}
