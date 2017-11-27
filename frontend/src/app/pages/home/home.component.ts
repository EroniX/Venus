import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';
import {Component, OnInit} from '@angular/core';
import {AccountCredentials} from "../../model/account-credentials";
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor() {
  }
}
