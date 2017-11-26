import {Injectable} from '@angular/core';
import {RequestOptions, Http} from '@angular/http';
import {AccountCredentials} from "../model/AccountCredentials";
import {User} from "../model/User";

import "rxjs/Rx";
import {Routes, Server} from "../routes/server-routes";
import { VenusRequestOptions } from '../utils/venus-request-options';

@Injectable()
export class AuthService {
    constructor(private http: Http) {
    }

    login(accountCredentials: AccountCredentials) {
        return this.http.post(
            Server.routeTo(Routes.LOGIN),
            JSON.stringify(accountCredentials))
                .do(resp => {
                    localStorage.setItem('jwt', resp.headers.get('Authorization'));
        });
    }

    logout(): void {
        // @TODO: Tell server to delete my datas
        localStorage.removeItem('jwt');
    }

    isLoggedIn() : boolean {
        return localStorage.getItem('jwt') != undefined;
    }

    register(user: User) {

        return this.http.post(
            Server.routeTo(Routes.REGISTER),
            JSON.stringify(user),
            new VenusRequestOptions());
            //.map(resp => resp.json());
        /*return this.httpService.post(
            Routes.REGISTER,
            user,
            this.isLoggedIn());*/
    }
}
