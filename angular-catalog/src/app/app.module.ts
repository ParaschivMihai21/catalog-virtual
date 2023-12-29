import {NgModule} from '@angular/core';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {StudentListComponent} from './student-list/student-list.component';
import {CreateStudentComponent} from './create-student/create-student.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {StudentDetailsComponent} from './student-details/student-details.component';
import {CoursesComponent} from './courses/courses.component';
import {ModalComponent} from './modals/modal/modal.component';
import {EnrolledStudentsComponent} from './modals/enrolled-students/enrolled-students.component';
import {AssignComponent} from './assign/assign.component';
import {LoginFormComponent} from './login-form/login-form.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import { RegisterComponent } from './register/register.component';
import { RegisterModalComponent } from './modals/register-modal/register-modal.component';
import { JwtModule, JwtModuleOptions} from '@auth0/angular-jwt';



const JWT_Module_Options: JwtModuleOptions = {
  config: {
    tokenGetter: () => {
      return localStorage.getItem("token")
    },
  }
};
@NgModule({
  declarations: [
    AppComponent,
    StudentListComponent,
    CreateStudentComponent,
    StudentDetailsComponent,
    CoursesComponent,
    ModalComponent,
    EnrolledStudentsComponent,
    AssignComponent,
    LoginFormComponent,
    RegisterComponent,
    RegisterModalComponent,



  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgbModule,
        BrowserAnimationsModule,
        FlexLayoutModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        MatCardModule,
        MatToolbarModule,
        ReactiveFormsModule,
      JwtModule.forRoot(JWT_Module_Options),

    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
