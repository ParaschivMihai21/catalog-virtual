import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {StudentService} from "../service/student.service";
import {StudentDTO} from "../class/student-dto";
import {StudentCourseDTO} from "../class/student-course-dto";
import {ModalService} from "../service/modal.service";

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {

  id: number | undefined
  studentDetails: StudentDTO | undefined;
  selectedCourseId: number | undefined;


  constructor(private route: ActivatedRoute, private studentService: StudentService, private modalService: ModalService) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['studentId'];
    this.studentService.getStudentById(this.id).subscribe(data => {
      this.studentDetails = data;
    });

  }


  updateStudentGrades(courseId: number | undefined, grades: number | undefined) {
    console.log(courseId, grades);
    let studentCourseGrades: StudentCourseDTO = new StudentCourseDTO(this.id, courseId, grades);

    this.studentService.updateStudentCourseGrades(studentCourseGrades).subscribe();

  }

  enroll() {

    const modalRef = this.modalService.openEnroll('Enroll Student', {
      studentId: this.id,
      courseId: this.selectedCourseId
    }, 'xl');
  }
}
