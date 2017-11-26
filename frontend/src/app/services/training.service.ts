import {HttpService} from './http.service';
import {Injectable} from '@angular/core';
import {RequestOptions, Http} from '@angular/http';
import {AccountCredentials} from "../model/AccountCredentials";
import {User} from "../model/User";

import "rxjs/Rx";
import {Routes, Server} from "../config/routes.config";
import { Config } from '../config/config';
import { Training } from '../model/Training';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class TrainingService {
    constructor(private http: HttpService) {
    }

    listRegistered(): Observable<Array<Training>> {
        return this.http.get(Routes.TRAINING_LIST_REGISTERED)
            .map(resp => resp.json());
    }

    listUnregistered(): Observable<Array<Training>> {
        return this.http.get(Routes.TRAINING_LIST_UNREGISTERED)
            .map(resp => resp.json());
    }

    register(training: Training): Observable<boolean> {
        return this.http.post(Routes.TRAINING_REGISTER, training.id)
            .map(resp => resp.text() == "true");
    }

    unregister(training: Training) {
        console.log("1");
        let id = training.id;
        return this.http.post(Routes.TRAINING_UNREGISTER, id)
            .map(resp => resp.text() == "true");
    }
}
