import { Component } from '@angular/core';
import { Router } from '@angular/router';
import '@angular/material/prebuilt-themes/indigo-pink.css';
import 'material-design-icons/iconfont/material-icons.css'
import '../../public/css/styles.css';

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {

    constructor(private router: Router) { }

    gotoMenu(url: string): void {
        this.router.navigate([url]);
    }
}
