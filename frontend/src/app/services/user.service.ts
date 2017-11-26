import {Injectable} from '@angular/core';
import {Http} from "@angular/http";

import "rxjs/Rx";
import {Routes, Server} from "../routes/server-routes";
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserService {
    constructor(private http: Http) {
    }

    validateUsername(username: String) : Observable<Boolean> {
        return this.http.get(Server.routeTo(Routes.VALIDATE_USERNAME) + "/" + username)
            .map(resp => resp.text() == "true");
    }

    validateEmail(email: String) : Observable<Boolean> {
        return this.http.get(Server.routeTo(Routes.VALIDATE_EMAIL) + "/" + email)
            .map(resp => resp.text() == "true");
    }
}
