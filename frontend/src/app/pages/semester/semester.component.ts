import { Component, OnInit } from '@angular/core';
import { TrainingService } from '../../services/training.service';
import { Training } from '../../model/Training';
import { MatSnackBar } from '@angular/material';
import { Semester } from '../../model/semester';
import { SemesterService } from '../../services/semester.service';

@Component({
  selector: 'app-semester',
  templateUrl: './semester.component.html',
  styleUrls: ['./semester.component.css']
})
export class SemesterComponent implements OnInit {
    current: Semester;  

    constructor(private snackbar: MatSnackBar, private semesterService: SemesterService) { 
    }

    ngOnInit() {
        this.loadCurrent();
    }

    register() {
      this.semesterService.register()
          .subscribe(resp => {
            if(resp == true) {
                this.loadCurrent();
                this.snackbar.open('Current semester succesfully registered', "Close", {
                  duration: 3000
                });
            }                
        });
    }

    unregister() {
      this.semesterService.unregister()
          .subscribe(resp => {
              if(resp == true) {
                  this.loadCurrent();
                  this.snackbar.open('Current semester succesfully unregistered', "Close", {
                    duration: 3000
                  });
              }                
          });
    }

    loadCurrent() {
      this.semesterService.current()
         .subscribe(resp => {
             this.current = resp;
             console.log(this.current);
            });
    }
}
