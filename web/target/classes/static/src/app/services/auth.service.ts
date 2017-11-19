import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {User} from "../model/User";
import {Routes, Server} from "../utils/ServerRoutes";
import {Observable} from "rxjs/Observable";
import { Headers } from '@angular/http';

import {Response} from "@angular/http";
import "rxjs/Rx";

@Injectable()
export class AuthService {
  user: User;
  isLoggedIn: boolean = false;

  constructor(private http: Http) {
    this.user = new User();
  }

  /*login(user: User) {
    return this.http.post(Server.routeTo(Routes.LOGIN), user)
      .map(res => {
        this.isLoggedIn = true;
        //this.user = res.json();
        return this.user;
      })
  }*/
  login(user: User) : void {
    let loginRequest = JSON.stringify({username: user.username, password: user.password});
    let headers = new Headers({'Content-Type': 'application/json', 'Accept': 'application/json'});
    //user.email = "vaczi8@gmail.com";
    console.log("asd");
    this.http.post('http://localhost:8080/api/user/login', user);
      /*.do(resp => {
        console.log("2");
        this.isLoggedIn = true;
        localStorage.setItem('jwt', resp.headers.get('x-auth-token'));
      });*/
  }

  register(user: User) {
    return this.http.post(Server.routeTo(Routes.REGISTER), user)
      .map(res => {
        this.isLoggedIn = true;
        this.user = res.json();
        return this.user;
      })
  }

  logout() {
    return this.http.get(Server.routeTo(Routes.LOGOUT))
      .map(res => {
        this.user = null;
        this.isLoggedIn = false;
      })
  }
}
