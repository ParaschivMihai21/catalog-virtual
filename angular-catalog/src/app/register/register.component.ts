import {Component} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {UserDto} from "../class/user-dto";

import {ModalService} from "../service/modal.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registration: boolean = false;
  serverMessage: String = "";


  constructor(private authService: AuthService, private modalService: ModalService) {
  }

  registerForm = new FormGroup({
    userName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required, Validators.minLength(3)]),
    roleName: new FormControl('', [Validators.required])
  });

  onSubmit() {
    if (this.registerForm.valid) {
      const user: UserDto = {
        userName: this.registerForm.value.userName ?? "",
        password: this.registerForm.value.password ?? "",
        roleName: this.registerForm.value.roleName ?? ""
      };
      this.authService.registerUser(user).subscribe(response => {
          console.log("User successfully registered ", response);
          this.modalService.openModalRegister("Registration", "any")
        },

        error => {
          console.error('Registration error', error);
          this.serverMessage =error.error;
          this.registration = true;
        }
      )
    }
  }
//
//         if (this.password !== this.confirmPassword) {
//             this.message = 'Passwords do not match.';
//             return false;
//         }
//
//
//
//         return true;
//     }
}
