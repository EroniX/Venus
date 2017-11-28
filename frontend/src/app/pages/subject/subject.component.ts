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
    subjects: Array<Subject>
    displayedColumns = ['name', 'code'];
    dataSource = new MatTableDataSource<Subject>(this.subjects);

    @ViewChild(MatSort) sort: MatSort;

    constructor(
        private snackbar: MatSnackBar, 
        private subjectService: SubjectService) { 
    }

    applyFilter(filterValue: string) {
        filterValue = filterValue.trim(); 
        filterValue = filterValue.toLowerCase();
        this.dataSource.filter = filterValue;
    }

    ngAfterViewInit() {
        this.dataSource.sort = this.sort;
    }

    ngOnInit() {
        this.loadSubjects();
    }

    loadSubjects() {
      this.subjectService.list()
         .subscribe(resp => {
              this.subjects = resp; 
              this.dataSource = new MatTableDataSource<Subject>(this.subjects);
          });
    }
}
