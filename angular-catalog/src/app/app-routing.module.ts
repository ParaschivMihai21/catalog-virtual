import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {StudentListComponent} from "./student-list/student-list.component";
import {CreateStudentComponent} from "./create-student/create-student.component";
import {StudentDetailsComponent} from "./student-details/student-details.component";
import {CoursesComponent} from "./courses/courses.component";
import {LoginFormComponent} from "./login-form/login-form.component";
import {RegisterComponent} from "./register/register.component";
import {AuthGuard} from "./guard/auth.guard";


const routes: Routes = [
  {path:'login',component:LoginFormComponent},
  {path:'register',component:RegisterComponent},
  {path:'students',component:StudentListComponent,canActivate:[AuthGuard]},
  {path:'',redirectTo:'login',pathMatch:'full'},
  {path:'create-student',component:CreateStudentComponent,canActivate:[AuthGuard]},
  {path:'student-details/:studentId',component:StudentDetailsComponent,canActivate:[AuthGuard]},
  {path:'courses',component:CoursesComponent,canActivate:[AuthGuard]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
