import {Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {HomeComponent} from "./pages/home/home.component";
import {ErrorComponent} from "./pages/error/error.component";
import {TrainingComponent} from './pages/training/training.component';
import {SemesterComponent} from './pages/semester/semester.component';
import {SubjectComponent} from './pages/subject/subject.component';
import {CourseComponent} from './pages/course/course.component';
import {RouteGuard} from './route.guard';
import {Role} from './helpers/role';
import {CourseCreateComponent} from './pages/course/create/course-create.component';
import {UserCourseListComponent} from './pages/user-course/user-course-list.component';

export const appRoutes: Routes = [
    {
        path: '',
        canActivateChild: [RouteGuard],

        children: [
          {path: '',                redirectTo: 'home',           pathMatch: 'full'},
          {path: 'login',           component: LoginComponent,    },
          {path: 'register',        component: RegisterComponent, },
          {path: 'training',        component: TrainingComponent, data: {roles: [Role.USER]}},
          {path: 'semester',        component: SemesterComponent, data: {roles: [Role.USER]}},
          {path: 'subject',         component: SubjectComponent,  data: {roles: [Role.USER]}},
          {path: 'course/:id', 	    component: CourseComponent,   data: {roles: [Role.USER]}},
          {path: 'course-create', 	component: CourseCreateComponent,   data: {roles: [Role.USER]}},
          {path: 'user-course-list/:id', component: UserCourseListComponent,   data: {roles: [Role.USER]}},
          {path: 'home',       	    component: HomeComponent},
          {path: '**',         	    component: ErrorComponent},
        ]
    }
];
