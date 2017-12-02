import {HttpService} from './http.service';
import {Injectable} from '@angular/core';
import {RequestOptions, Http} from '@angular/http';
import {AccountCredentials} from "../model/account-credentials";
import {User} from "../model/User";

import "rxjs/Rx";
import {Routes, Server} from "../config/routes.config";
import {Config} from '../config/config';
import {Training} from '../model/Training';
import {Observable} from 'rxjs/Observable';
import {Course} from '../model/course';
import {UserCourse} from '../model/user-course';

@Injectable()
export class CourseService {
    constructor(private http: HttpService) {
    }

    findBySubjectId(subjectId: number): Observable<Array<Course>> {
        return this.http.get(Routes.COURSE_LIST, subjectId)
            .map(resp => resp.json());
    }

    findById(courseId: number): Observable<Course> {
        return this.http.get(Routes.COURSE_FIND_BY_ID, courseId)
            .map(resp => resp.json());
    }

    create(course: Course): Observable<boolean>  {
        return this.http.post(Routes.COURSE_CREATE, course)
            .map(resp => resp.text() == "true");
    }
}
