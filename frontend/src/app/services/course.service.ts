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
import { Course } from '../model/course';
import { UserCourse } from '../model/user-course';

@Injectable()
export class CourseService {
    constructor(private http: HttpService) {
    }

    list(id: number): Observable<Array<Course>> {
        return this.http.get(Routes.COURSE_LIST, id)
            .map(resp => resp.json());
    }

    getTeacher(id: number): Observable<User> {
        return this.http.get(Routes.COURSE_STUDENTS_LIST, id)
            .map(resp => resp.json());
    }

    listStudents(id: number): Observable<Array<UserCourse>> {
        return this.http.get(Routes.COURSE_STUDENTS_LIST, id)
            .map(resp => resp.json());
    }

    create(course: Course): Observable<boolean>  {
        return this.http.post(Routes.COURSE_CREATE, course)
            .map(resp => resp.text() == "true");
    }

    register(course: Course): Observable<boolean> {
        return this.http.post(Routes.COURSE_REGISTER, course.id)
            .map(resp => resp.text() == "true");
    }

    unregister(course: Course) {
        return this.http.post(Routes.COURSE_UNREGISTER, course.id)
            .map(resp => resp.text() == "true");
    }
}
