import {AuthService} from './auth.service';
import {HttpService} from './http.service';
import {User} from '../model/User';
import {AccountCredentials} from '../model/account-credentials';
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";

import "rxjs/Rx";
import {Routes, Server} from "../config/routes.config";
import { Observable } from 'rxjs/Observable';
import { Config } from '../config/config';

@Injectable()
export class UserService {
    public user: User;

    constructor(private http: HttpService, private authService: AuthService) {
    }

    login(accountCredentials: AccountCredentials) {
        return this.http.post(Routes.USER_LOGIN, accountCredentials, false)
            .do(resp => {
                this.authService.login(resp.headers.get(Config.TOKEN_NAME))})
            .map(resp => resp.json);        
    }

    register(user: User): Observable<boolean> {
        return this.http.post(Routes.USER_REGISTER, user)
            .map(resp => resp.json());
    }

    validateUsername(username: string) : Observable<boolean> {
        return this.http.get(Routes.VALIDATE_USERNAME, username)
            .map(resp => resp.text() == "true");
    }

    validateEmail(email: string) : Observable<boolean> {
        return this.http.get(Routes.VALIDATE_EMAIL, email)
            .map(resp => resp.text() == "true");
    }

    getUser(): Observable<User> {
        return this.http.get(Routes.USER_GET)
            .map(n => n.json());
    }
}
