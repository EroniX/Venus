import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Training } from '../../../model/Training';

@Component({
  selector: 'training-item',
  templateUrl: './training-item.component.html',
  styleUrls: ['./training-item.component.css']
})
export class TrainingItemComponent {
    @Input() training: Training; 
    @Input() description: string
    @Input() information: string
    @Output() click: EventEmitter<Training> = new EventEmitter<Training>();
    
    constructor() { 
    }

    onClick() {
        this.click.emit(this.training);
    }
}
