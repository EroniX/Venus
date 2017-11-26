import {AuthService} from './auth.service';
import {Server} from '../config/routes.config';
import { Injectable } from '@angular/core';
import {RequestOptions, Http} from '@angular/http';
import { HeaderHelper } from '../helpers/header.helper';

@Injectable()
export class HttpService {
    constructor(private http: Http, private authService: AuthService) {
    }

    post(route: string, params: any, needHeader = true) {
        return this.http.post(
            Server.routeTo(route),
            JSON.stringify(params),
            this.getRequestOptions(needHeader));
    }

    get(route: string, params: any, needHeader = true) {
        return this.http.get(
            Server.routeTo(route) + "/" +  params,
            this.getRequestOptions(needHeader));
    }

    private getRequestOptions(needHeader: boolean): RequestOptions {
        if(!needHeader) {
            return HeaderHelper.createEmpty();
        }
        return this.authService.isLoggedIn()
            ? HeaderHelper.createAuthorized(this.authService.getToken())
            : HeaderHelper.createUnauthorized();
    }
}
