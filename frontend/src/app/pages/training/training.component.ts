import {MatTableDataSource, MatSort} from '@angular/material';
import {Component, OnInit} from '@angular/core';
import {TrainingService} from '../../services/training.service';
import {Training} from '../../model/Training';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-training',
  templateUrl: './training.component.html',
  styleUrls: ['./training.component.css']
})
export class TrainingComponent implements OnInit {
    allTrainings: Array<Training>;  
    myTrainings: Array<Training>;  

    displayedColumns = ['name', 'control'];
    allTrainingsDataSource = new MatTableDataSource<Training>(this.allTrainings);
    myTrainingsDataSource = new MatTableDataSource<Training>(this.myTrainings);

    constructor(
        private snackbar: MatSnackBar, 
        private trainingService: TrainingService) { 
    }

    ngOnInit(): void {
        this.loadTrainings();
    }

    applyAllTrainingsFilter(filterValue: string): void {
        filterValue = filterValue.trim(); 
        filterValue = filterValue.toLowerCase();
        this.allTrainingsDataSource.filter = filterValue;
    }

    applyMyTrainingsFilter(filterValue: string): void {
        filterValue = filterValue.trim(); 
        filterValue = filterValue.toLowerCase();
        this.myTrainingsDataSource.filter = filterValue;
    }

    registerTraining(training: Training): void {
        this.trainingService.register(training)
            .subscribe(resp => {
                if(resp == true) {
                    this.loadTrainings();
                    this.snackbar.open(training.name + ' succesfully registered', "Close", {
                        duration: 3000
                    });
                }                   
        });
    }

    unregisterTraining(training: Training): void {
        this.trainingService.unregister(training)
            .subscribe(resp => {
                if(resp == true) {
                    this.loadTrainings();
                    this.snackbar.open(training.name + ' succesfully unregistered', "Close", {
                        duration: 3000
                    });
                }                
            });
    }

    loadTrainings(): void {
        this.trainingService.findAll()
            .subscribe(resp => {
                this.allTrainings = resp.filter(n => !n.registered); 
                this.myTrainings = resp.filter(n => n.registered); 

                this.allTrainingsDataSource = new MatTableDataSource<Training>(this.allTrainings);
                this.myTrainingsDataSource = new MatTableDataSource<Training>(this.myTrainings);
        });
    }
}
