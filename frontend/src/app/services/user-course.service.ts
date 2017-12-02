import {AuthService} from './auth.service';
import {HttpService} from './http.service';
import {User} from '../model/User';
import {AccountCredentials} from '../model/account-credentials';
import {Injectable, OnInit} from '@angular/core';
import {Http} from "@angular/http";

import "rxjs/Rx";
import {Routes, Server} from "../config/routes.config";
import { Observable } from 'rxjs/Observable';
import { Config } from '../config/config';
import { UserCourse } from '../model/user-course';
import { UserCourseMark } from '../model/user-course-mark';

@Injectable()
export class UserCourseService {
    constructor(private http: HttpService) {
    }

    findByCourseId(courseId: number): Observable<Array<UserCourse>> {
        return this.http.get(Routes.USER_COURSE_FIND_BY_COURSE_ID, courseId)
            .map(resp => resp.json());
    }

    create(courseId: number): Observable<boolean> {
        return this.http.post(Routes.USER_COURSE_CREATE, courseId)
            .map(resp => resp.text() == "true");
    }

    delete(courseId: number) {
        return this.http.post(Routes.USER_COURSE_DELETE, courseId)
            .map(resp => resp.text() == "true");
    }

    mark(userCourseMark: UserCourseMark) {
        console.log(userCourseMark);
        return this.http.post(Routes.USER_COURSE_MARK, userCourseMark)
            .map(resp => resp.text() == "true");
    }
}
