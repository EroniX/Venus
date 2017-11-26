import {Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {HelpComponent} from "./pages/help/help.component";
import {ErrorComponent} from "./pages/error/error.component";

export const appRoutes: Routes = [
  {path: '',            redirectTo: 'help',         pathMatch: 'full'},
  {path: 'login',       component: LoginComponent},
  {path: 'register',    component: RegisterComponent},
  {path: 'help',        component: HelpComponent},
  {path: '**',          component: ErrorComponent},
];
