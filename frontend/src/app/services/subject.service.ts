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
import { Subject } from '../model/subject';

@Injectable()
export class SubjectService {
    constructor(private http: HttpService) {
    }

    list(): Observable<Array<Subject>> {
        return this.http.get(Routes.SUBJECT_LIST)
            .map(resp => resp.json());
    }
}
