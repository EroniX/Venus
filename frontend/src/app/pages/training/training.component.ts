import { Component, OnInit } from '@angular/core';
import { TrainingService } from '../../services/training.service';
import { Training } from '../../model/Training';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-training',
  templateUrl: './training.component.html',
  styleUrls: ['./training.component.css']
})
export class TrainingComponent implements OnInit {
    unregisteredTrainings: Array<Training>;  
    registeredTrainings: Array<Training>;  

    constructor(private snackbar: MatSnackBar, private trainingService: TrainingService) { 
    }

    ngOnInit() {
        this.refresh();
    }

    refresh() {
        this.listRegisteredTrainings();
        this.listUnregisteredTrainings();
    }

    registerTraining(training: Training) {
      this.trainingService.register(training)
          .subscribe(resp => {
            if(resp == true) {
                this.refresh();
                this.snackbar.open(training.name + ' succesfully registered', "Close", {
                  duration: 3000
                });
            }                
        });
    }

    unregisterTraining(training: Training) {
      this.trainingService.unregister(training)
          .subscribe(resp => {
              if(resp == true) {
                  this.refresh();
                  this.snackbar.open(training.name + ' succesfully unregistered', "Close", {
                    duration: 3000
                  });
              }                
          });
    }

    listRegisteredTrainings() {
      this.trainingService.listRegistered()
         .subscribe(resp => this.registeredTrainings = resp);
    }

    listUnregisteredTrainings() {
      this.trainingService.listUnregistered()
         .subscribe(resp => this.unregisteredTrainings = resp);
    }
}
