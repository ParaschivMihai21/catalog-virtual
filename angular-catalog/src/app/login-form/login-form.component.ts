import {Component} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {UserDto} from "../class/user-dto";
import {Router} from "@angular/router";
import {CredentialsDto} from "../class/credentials-dto";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  user: UserDto | undefined;

  errorMessage: string ='';
  roleName: string | undefined;
  userName: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {
  }

  onSubmit(): void {
    const credentialsDto: CredentialsDto = {
      userName: this.userName,
      password: this.password,

    };

    this.authService.signIn(credentialsDto).subscribe(
      (response) => {
        console.log('Login successful:', response);
        this.router.navigate(['students']);
      },
      error => {
        console.error('Login error:', error);
        this.errorMessage =error.error;
      }
    );
  }


}
