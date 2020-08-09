import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {LoginRequest} from '../components/login/LoginRequest';
import {RegistrationRequest} from '../components/register/RegistrationRequest';
import {Router} from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    private readonly LOGIN_PATH: string = '/login';
    private readonly REGISTRATION_PATH: string = '/register';

    constructor(private apiService: ApiService, private router: Router) {
    }

    navigateToDashboardPageIfLoggedIn() {

        const loggedIn = this.isLoggedIn();

        if (loggedIn) {
            this.router.navigate(['dashboard']);
        }

    }

    getLoggedInUserName(): string {
        return sessionStorage.getItem('loggedInUserName');
    }

    getToken(): string {
        return sessionStorage.getItem('token');
    }

    isLoggedIn(): boolean {
        return !!this.getToken();
    }

    login(loginRequest: LoginRequest) {
        return this.apiService.post(this.LOGIN_PATH, loginRequest).subscribe(resp => {
            sessionStorage.setItem('token', resp.token);
            sessionStorage.setItem('loggedInUserName', resp.name);
            this.router.navigate(['dashboard']);
        });
    }

    registration(registrationRequest: RegistrationRequest) {
        return this.apiService.post(this.REGISTRATION_PATH, registrationRequest).subscribe(resp => {
            this.router.navigate(['login']);
        });
    }

    logout() {
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('loggedInUserName');
        this.router.navigate(['']);
    }
}
