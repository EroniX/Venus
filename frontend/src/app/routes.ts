import {Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {HomeComponent} from "./pages/home/home.component";
import {ErrorComponent} from "./pages/error/error.component";
import {TrainingComponent} from './pages/training/training.component';
import {SemesterComponent} from './pages/semester/semester.component';
import {SubjectComponent} from './pages/subject/subject.component';
import {CourseComponent} from './pages/course/course.component';

export const appRoutes: Routes = [
  {path: '',           		 redirectTo: 'home',         pathMatch: 'full'},
  {path: 'login',      		 component: LoginComponent},
  {path: 'register',   		 component: RegisterComponent},
  {path: 'training',   		 component: TrainingComponent},
  {path: 'semester',   		 component: SemesterComponent},
  {path: 'subject',    		 component: SubjectComponent},
  {path: 'course/:id', 		 component: CourseComponent},
  {path: 'home',       		 component: HomeComponent},
  {path: '**',         		 component: ErrorComponent},
];
