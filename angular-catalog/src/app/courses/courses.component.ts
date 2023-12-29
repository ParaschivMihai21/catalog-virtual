import {Component, OnInit} from '@angular/core';
import {CourseDTO} from "../class/course-dto";
import {CoursesService} from "../service/courses.service";
import {Router} from "@angular/router";
import {ModalService} from "../service/modal.service";



@Component({
    selector: 'app-courses',
    templateUrl: './courses.component.html',
    styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
    courses: CourseDTO[] = [];
    newCourse: CourseDTO = new CourseDTO();


    constructor(private coursesService: CoursesService, private modalService: ModalService) {
    }

    ngOnInit(): void {
        this.getCourses()
    }

    private getCourses() {
        this.coursesService.getCourseList().subscribe(data => {
            this.courses = data;
        });
    }

    deleteCourses(courseId: number | undefined) {
        if (confirm('Are you sure you want to delete the course?')) {
            this.coursesService.deleteCourse(courseId).subscribe(
                data => {
                    console.log(data);
                    console.log("course deleted");
                    this.getCourses();
                },
                error => {
                    console.error('Error deleting student:', error);
                }
            );
        } else {
            console.log('Deletion cancelled.');
        }

    }

    addCourses() {
        this.coursesService.addCourse(this.newCourse).subscribe(data => {
            console.log(data);
            window.location.reload();
        });
    }

    showStudents(courseId: number | undefined) {
        this.coursesService.showStudentEnrolled(courseId).subscribe(enrolledStudents => {

            const modalRef = this.modalService.openModalEnroll('Students enrolled in the course', enrolledStudents, 'xl')
        });
    }
}
