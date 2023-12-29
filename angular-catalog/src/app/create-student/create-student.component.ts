import {Component, OnInit} from '@angular/core';
import {StudentService} from "../service/student.service";
import {Router} from "@angular/router";
import {StudentDTO} from "../class/student-dto";

@Component({
  selector: 'app-create-student',
  templateUrl: './create-student.component.html',
  styleUrls: ['./create-student.component.css']
})
export class CreateStudentComponent implements OnInit {
 student:StudentDTO=new StudentDTO();
 constructor(private studentService:StudentService,private router:Router){}
  ngOnInit(): void {
  }

addStudent(){
     this.studentService.createStudent(this.student).subscribe(data=>{
         console.log(data);
         this.goToStudentList();
     },
         error => console.log(error));
 }
goToStudentList(){
    this.router.navigate(['/students']);

    }

  onSubmit() {
console.log(this.student);
this.addStudent();
  }
}
