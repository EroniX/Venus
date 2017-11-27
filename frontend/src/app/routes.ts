import {Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {HelpComponent} from "./pages/help/help.component";
import {ErrorComponent} from "./pages/error/error.component";
import {TrainingComponent} from './pages/training/training.component';
import { SemesterComponent } from './pages/semester/semester.component';

export const appRoutes: Routes = [
  {path: '',            redirectTo: 'help',         pathMatch: 'full'},
  {path: 'login',       component: LoginComponent},
  {path: 'register',    component: RegisterComponent},
  {path: 'training',    component: TrainingComponent},
  {path: 'semester',    component: SemesterComponent},
  {path: 'help',        component: HelpComponent},
  {path: '**',          component: ErrorComponent},
];
