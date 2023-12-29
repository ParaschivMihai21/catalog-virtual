import {Component, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {StudentCourseDTO} from "../class/student-course-dto";
import {StudentService} from "../service/student.service";
import {ModalService} from "../service/modal.service";
import {StudentDTO} from "../class/student-dto";
import {CourseDTO} from "../class/course-dto";
import {CoursesService} from "../service/courses.service";

@Component({
    selector: 'app-assign',
    templateUrl: './assign.component.html',
    styleUrls: ['./assign.component.css']
})
export class AssignComponent implements OnInit {
    titleModule: string | undefined;
    content: any;
    selectedCourseId: number | undefined;
    studentId: number | undefined;
    students: StudentDTO | undefined;
    courses: CourseDTO[] | undefined;
  id: number | undefined;
    constructor(private activeModal: NgbActiveModal, private studentService: StudentService, private modalService: ModalService, private coursesService: CoursesService) {
    }

    ngOnInit(): void {
        this.coursesService.getCourseList().subscribe(data => {
            this.courses = data;
        });
      this.studentService.getStudentById(this.id).subscribe(data => {
        this.students = data;
      });
    }

    public onCancel(): void {
        this.activeModal.close()
    }

  enrollStudent(selectedCourseId: number | undefined) {
    if (this.selectedCourseId && this.id) {
      const studentCourseDTO: StudentCourseDTO = {
        studentId: this.id,
        courseId: this.selectedCourseId,
        grades: 0
      };

      this.studentService.assignCourse(studentCourseDTO).subscribe(
        response => {
          console.log('Enrollment successful:', response);
          window.location.reload();
        },
        error => {
          console.error('Error enrolling student:', error);
          this.modalService.openModal('Enrollment error', 'You are already enrolled in this course');
        }
      );
    } else {
      console.log('Please select a course to enroll.');
    }
  }




}
