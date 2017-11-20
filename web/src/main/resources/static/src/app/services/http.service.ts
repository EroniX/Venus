import { Server } from '../utils/ServerRoutes';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { VenusRequestOptions } from '../utils/venus-request-options';

@Injectable()
export class HttpService {
    public isLoggedIn: boolean
    constructor(private http: Http, ) {
    }

    post(route, params) {
        console.log(JSON.stringify(params));
        return this.http.post(
            Server.routeTo(route), 
            params,//JSON.stringify(params),
            new VenusRequestOptions(this.isLoggedIn));
    }

    get(route, params) {
        return this.http.get(
            Server.routeTo(route) + "/" +  params,
            new VenusRequestOptions(this.isLoggedIn));
    }
}