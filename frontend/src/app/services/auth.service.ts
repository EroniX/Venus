import {Injectable} from '@angular/core';
import {RequestOptions, Http} from '@angular/http';
import {AccountCredentials} from "../model/account-credetials";
import {User} from "../model/User";

import "rxjs/Rx";
import {Routes, Server} from "../config/routes.config";
import {Config} from '../config/config';

@Injectable()
export class AuthService {
    login(token: string) : void {
        localStorage.setItem(Config.TOKEN, token);
    }

    logout(): void {
        localStorage.removeItem(Config.TOKEN);
    }

    isLoggedIn() : boolean {
        return this.getToken() != null;
    }

    getToken() : string {
        return localStorage.getItem(Config.TOKEN);
    }
}
