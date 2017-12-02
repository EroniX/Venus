import {HttpService} from './http.service';
import {Injectable} from '@angular/core';
import {RequestOptions, Http} from '@angular/http';
import {AccountCredentials} from "../model/account-credentials";
import {User} from "../model/User";

import "rxjs/Rx";
import {Routes, Server} from "../config/routes.config";
import { Config } from '../config/config';
import { Training } from '../model/Training';
import { Observable } from 'rxjs/Observable';
import { Semester } from '../model/semester';

@Injectable()
export class SemesterService {
    constructor(private http: HttpService) {
    }

    getCurrent(): Observable<Semester> {
        return this.http.get(Routes.SEMESTER_FIND_CURRENT)
            .map(resp => resp.json());
    }

    findAll(): Observable<Array<Semester>> {
        return this.http.get(Routes.SEMESTER_FIND_ALL)
            .map(resp => resp.json());
    }

    register(): Observable<boolean> {
        return this.http.post(Routes.SEMESTER_REGISTER, "")
            .map(resp => resp.text() == "true");
    }

    unregister(): Observable<boolean> {
        return this.http.post(Routes.SEMESTER_UNREGISTER, "")
            .map(resp => resp.text() == "true");
    }
}
