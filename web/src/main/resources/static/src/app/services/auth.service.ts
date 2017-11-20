import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {AccountCredentials} from "../model/AccountCredentials";
import {User} from "../model/User";
import {Routes, Server} from "../utils/ServerRoutes";
import {Observable} from "rxjs/Observable";
import { Headers, Response } from '@angular/http';

import "rxjs/Rx";
import { HttpService } from './http.service';

@Injectable()
export class AuthService {
  accountCredentials: AccountCredentials;
  constructor(private http: Http, private httpService: HttpService) {
    this.accountCredentials = new AccountCredentials();
    httpService.isLoggedIn = this.isLoggedIn();
  }

  login(accountCredentials: AccountCredentials) {
      console.log(accountCredentials);
      return this.httpService.post(Routes.LOGIN, accountCredentials)
        .do(resp => {
            console.log("asd");
            localStorage.setItem('jwt', resp.headers.get('Authorization'));
    });
    //let loginRequest = JSON.stringify({username: username, password: password});
    /*return this.http.post(Server.routeTo(Routes.LOGIN), accountCredentials)
      .map(resp => {
        console.log("asdsadad");
        localStorage.setItem('jwt', resp.headers.get('x-auth-token'));
        return resp;
      });*/

  }

  logout(): void {
    // @TODO: Tell server to delete my datas
    localStorage.removeItem('jwt');
  }

  isLoggedIn() : boolean {
    return localStorage.getItem('jwt') != undefined;
}

  register(user: User) {
    return this.httpService.post(Routes.REGISTER, user)
      .do(res => {
        console.log("asd");
        //return this.login(user.username, user.password);
      });
  }
}
