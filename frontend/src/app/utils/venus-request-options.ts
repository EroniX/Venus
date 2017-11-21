import {RequestOptionsArgs, RequestOptions,  BaseRequestOptions} from '@angular/http';
import { Injectable } from '@angular/core';

export class VenusRequestOptions extends BaseRequestOptions {
    constructor(isLoggedIn: boolean) {
        super();

        //this.headers.append('Content-Type', 'application/json');
        //this.headers.append('Accept', 'application/json');
        /*if(isLoggedIn) {
            this.headers.append('Authorization', localStorage.getItem('jwt'));   
        }*/
    }

    merge(options?: RequestOptionsArgs): RequestOptions {
        const newOptions = super.merge(options);
        return newOptions;
    }
}