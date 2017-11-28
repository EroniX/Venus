import {UserService} from './services/user.service';
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class RouteGuard implements CanActivate, CanActivateChild {

    constructor(private userService: UserService) {
    }

    canActivate(next: ActivatedRouteSnapshot,
                state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        console.log(this.userService.user.username);
        //console.log(this.userService.user.roles.includes(next.data.roles));
        return next.data.roles == null 
            ? true 
            : this.userService.user.roles.includes(next.data.roles);
    }

    canActivateChild(next: ActivatedRouteSnapshot,
                        state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        return this.canActivate(next, state)
    }
}