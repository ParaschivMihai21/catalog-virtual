import { Component } from '@angular/core';
import {AuthService} from "./service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Virtual-catalog';
  constructor(private authService:AuthService,private router: Router){

  }

  get userLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }
  handleLogout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);

  }
}
