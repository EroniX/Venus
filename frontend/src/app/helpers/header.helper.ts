import {RequestOptions} from '@angular/http';
import {Headers} from '@angular/http';

export class HeaderHelper {
    static createAuthorized(token: string): RequestOptions {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', token);
        return new RequestOptions({ headers: headers });
    }

    static createUnauthorized() {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return new RequestOptions({ headers: headers });
    }

    static createEmpty() {
        let headers = new Headers();
        return new RequestOptions({ headers: headers });
    }
}
