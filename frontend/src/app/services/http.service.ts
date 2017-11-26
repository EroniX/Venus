import { Server } from '../routes/server-routes';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { VenusRequestOptions } from '../utils/venus-request-options';

@Injectable()
export class HttpService {
    constructor(private http: Http) {
    }

    /*post(route, params) {
        return this.http.post(
            Server.routeTo(route),
            JSON.stringify(params),
            new VenusRequestOptions());
    }

    get(route : String, params : any, isLoggedIn : boolean) {
        return this.http.get(
            Server.routeTo(route) + "/" +  params,
            new VenusRequestOptions());
    }*/
}
