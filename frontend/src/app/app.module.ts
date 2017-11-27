import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router'

import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AuthService } from "./services/auth.service";
import { HttpService } from "./services/http.service";
import { UserService } from "./services/user.service";
import { SemesterService } from "./services/semester.service";
import { HttpModule } from "@angular/http";
import { RegisterComponent } from './pages/register/register.component';
import  { appRoutes } from './routes';
import { MaterialModule } from "./material.module";
import { MenuComponent } from './components/menu/menu.component';
import { StatsComponent } from './pages/stats/stats.component';
import { HelpComponent } from './pages/help/help.component';
import { ErrorComponent } from './pages/error/error.component';
import { TrainingComponent } from './pages/training/training.component';
import { SemesterComponent } from './pages/semester/semester.component';
import { TrainingService } from './services/training.service';
import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MenuComponent,
    StatsComponent,
    HelpComponent,
    ErrorComponent,
    TrainingComponent,
    SemesterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    MaterialModule,

  ],
  providers: [AuthService, HttpService, UserService, TrainingService, SemesterService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
