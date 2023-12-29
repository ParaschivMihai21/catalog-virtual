import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {StudentDTO} from "../class/student-dto";
import {CourseDTO} from "../class/course-dto";
import {StudentCourseDTO} from "../class/student-course-dto";
import {AuthService} from "./auth.service";

@Injectable({
    providedIn: 'root'
})
export class CoursesService {
    private api = "http://localhost:8080/api/course";
    private courseApi="http://localhost:8080/api/enrollment";

    constructor(private httpClient: HttpClient,private authService:AuthService) {
    }

    getCourseList(): Observable<CourseDTO[]> {
        return this.httpClient.get<CourseDTO[]>(`${this.api}`,this.authService.httpOptions);
    }

    deleteCourse(courseId: number | undefined): Observable<Object> {
        return this.httpClient.delete(`${this.api}/${courseId}`,this.authService.httpOptions)
    }

    addCourse(courseDTO: CourseDTO | undefined): Observable<Object> {
        return this.httpClient.put(`${this.api}`,courseDTO,this.authService.httpOptions);
    }

    showStudentEnrolled(courseId: number | undefined):Observable<StudentCourseDTO[]>{
        return this.httpClient.get<StudentCourseDTO[]>(`${this.courseApi}/${courseId}`,this.authService.httpOptions)
    }
}
