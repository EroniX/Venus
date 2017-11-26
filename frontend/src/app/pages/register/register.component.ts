import {Router} from '@angular/router';
import {AccountCredentials} from '../../model/AccountCredentials';
import {Component, OnInit} from '@angular/core';
import {User} from "../../model/User";
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../../services/auth.service";
import {UserService} from "../../services/user.service";
import { Event } from '@angular/router/src/events';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
    registerForm: FormGroup = new FormGroup({
        username: new FormControl('', [Validators.required]),
        password: new FormControl('', [Validators.required]),
        email: new FormControl('', [Validators.required, Validators.email]),
    });

    usernameValidation: Boolean;
    emailValidation: Boolean;

    constructor(
        private userService: UserService,
        private router: Router) {
    }

    ngOnInit() {
        this.usernameValidation = false;
        this.emailValidation = false;
    }

    submit() {
        this.userService.register(new User(this.username.value, this.password.value, this.email.value))
            .subscribe(resp => {
                this.userService.login(new AccountCredentials(this.username.value, this.password.value))
                    .subscribe(resp => 
                        this.router.navigateByUrl(''));  
            }); 
    }

    get username(): AbstractControl {
        return this.registerForm.get('username');
    }

    get password(): AbstractControl {
        return this.registerForm.get('password');
    }

    get confirmPassword(): AbstractControl {
        return this.registerForm.get('confirmPassword');
    }

    get email(): AbstractControl {
        return this.registerForm.get('email');
    }

    validateUsername(username: string) {
        return this.userService.validateUsername(username)
            .subscribe(resp => this.usernameValidation = resp);
    }

    validateEmail(email: string) {
        return this.userService.validateEmail(email)
            .subscribe(resp => this.emailValidation = resp);
    }
}
