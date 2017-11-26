import {RequestOptionsArgs, RequestOptions,  BaseRequestOptions} from '@angular/http';

export class VenusRequestOptions extends BaseRequestOptions {
    constructor() {
        super();

        this.headers.append('Content-Type', 'application/json');
        if(localStorage.getItem('jwt') != null) {
            this.headers.append('Authorization', localStorage.getItem('jwt'));
        }
    }

    merge(options?: RequestOptionsArgs): RequestOptions {
        const newOptions = super.merge(options);
        return newOptions;
    }
}
