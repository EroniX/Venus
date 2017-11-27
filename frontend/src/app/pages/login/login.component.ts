import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';
import {Component, OnInit} from '@angular/core';
import {AccountCredentials} from "../../model/account-credetials";
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  constructor(
      private userService: UserService,
      private router: Router) {
  }

  submit() {
    this.userService.login(new AccountCredentials(this.username.value, this.password.value))
        .subscribe(resp => 
            this.router.navigateByUrl(''));
  }

  get username(): AbstractControl {
    return this.loginForm.get('username');
  }

  get password(): AbstractControl {
    return this.loginForm.get('password');
  }
}
