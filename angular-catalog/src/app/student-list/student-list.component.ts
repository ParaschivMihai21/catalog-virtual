import {Component, OnInit} from '@angular/core';
import {StudentService} from "../service/student.service";
import {Router} from "@angular/router";
import {StudentDTO} from "../class/student-dto";
import {CourseDTO} from "../class/course-dto";
import {CoursesService} from "../service/courses.service";


@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']

})
export class StudentListComponent implements OnInit {
  // @ts-ignore
  students: StudentDTO[];
  courses: CourseDTO[] | undefined;
  selectedCourseId: number | undefined;
  noStudentsFound = false;
  name: string = '';

  // @ts-ignore
  id: number;

  constructor(private studentService: StudentService, private coursesService: CoursesService, private router: Router) {
  }

  ngOnInit(): void {
    this.getStudents();
    this.fetchCourses();
  }

  private getStudents() {
    this.studentService.getStudentsList().subscribe(data => {
      this.students = data;
    });
  }

  deleteStudent(studentId: number | undefined) {
    const confirmation = confirm('Are you sure you want to delete the student?');

    if (confirmation) {
      this.studentService.deleteStudentId(studentId).subscribe(
        data => {
          console.log(data);
          console.log('Student deleted');
          this.getStudents();
        },
        error => {
          console.error('Error deleting student:', error);
        }
      );
    } else {
      console.log('Deletion cancelled.');
    }
  }

  studentDetails(studentId: number | undefined) {
    this.router.navigate(['student-details', studentId]);
  }


  fetchCourses(): void {
    this.coursesService.getCourseList().subscribe(
      data => {
        this.courses = data;
      },
      error => {
        console.error('Error fetching courses:', error);
      }
    );
  }

  findStudent() {
    console.log('Searching for:', this.name);
    this.studentService.searchStudent(this.name).subscribe(
      data => {
        console.log('Search results:', data)
        this.students = data;
        this.noStudentsFound = this.students.length === 0;


      },
      error => {
        console.error('Error fetching student:', error);
        if (error.status !== 404) {
          console.log('An error occurred');
        }
        this.noStudentsFound = false;
      }
    );
  }

  createStudent() {
    this.router.navigate(['create-student']);
  }
}

