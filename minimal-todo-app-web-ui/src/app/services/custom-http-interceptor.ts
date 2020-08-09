import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {ApiExceptionResponse} from '../models/api-exception-response';
import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';

@Injectable({
    providedIn: 'root'
})
export class CustomHttpInterceptor implements HttpInterceptor {

    apiExceptionResponse: ApiExceptionResponse;

    constructor(private toastrService: ToastrService) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const token = sessionStorage.getItem('token');

        req = req.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });

        return next.handle(req).pipe(catchError(error => this.handleError(error)));
    }

    handleError(error: HttpErrorResponse) {

        if (error.error instanceof ErrorEvent) {
            this.toastrService.warning('Client side error message', 'Client side error');
        } else {
            this.apiExceptionResponse = Object.assign({}, error.error);
            const message: string = JSON.stringify(this.apiExceptionResponse.message);
            this.toastrService.error(message, this.apiExceptionResponse.status);
        }

        return throwError(error.error);
    }

}
