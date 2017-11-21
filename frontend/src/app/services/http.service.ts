import { Server } from '../utils/ServerRoutes';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { VenusRequestOptions } from '../utils/venus-request-options';

@Injectable()
export class HttpService {
    public isLoggedIn: boolean
    constructor(private http: Http) {
    }

    post(route : String, params : any, isLoggedIn : boolean) {
        return this.http.post(
            Server.routeTo(route), 
            JSON.stringify(params),
            new VenusRequestOptions(isLoggedIn));
    }

    get(route : String, params : any, isLoggedIn : boolean) {
        return this.http.get(
            Server.routeTo(route) + "/" +  params,
            new VenusRequestOptions(isLoggedIn));
    }
}