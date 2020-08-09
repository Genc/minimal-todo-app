import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {TranslateService} from '@ngx-translate/core';
import {ToastrService} from 'ngx-toastr';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

    userName: string = this.authenticationService.getLoggedInUserName();

    constructor(private authenticationService: AuthenticationService) {
    }

    ngOnInit(): void {
    }

    isMorning(): boolean {
        let hour = new Date().getHours();
        return hour > 5 && hour < 18;
    }

    logout() {
        this.authenticationService.logout();
    }
}
