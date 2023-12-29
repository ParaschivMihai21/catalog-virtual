import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {StudentDTO} from "../class/student-dto";
import {StudentCourseDTO} from "../class/student-course-dto";
import {AuthService} from "./auth.service";
import { jwtDecode } from 'jwt-decode';


@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private apiUrl = "http://localhost:8080/api/students";
  private studentCourseUrl = "http://localhost:8080/api/enrollment";

  decodedToken ?: any;




  constructor(private httpClient: HttpClient,private authService:AuthService) {

    const token = this.authService.getToken();
    if (typeof token === "string") {
      this.decodedToken = jwtDecode(token)
    }
  }

  getStudentsList(): Observable<StudentDTO[]> {
    return this.httpClient.get<StudentDTO[]>(`${this.apiUrl}`,this.authService.httpOptions);
  }

  createStudent(studentDTO: StudentDTO): Observable<Object> {
    return this.httpClient.put(`${this.apiUrl}`, studentDTO,this.authService.httpOptions);
  }

  deleteStudentId(studentId: number | undefined): Observable<Object> {
    return this.httpClient.delete(`${this.apiUrl}/${studentId}`,this.authService.httpOptions);
  }

  getStudentById(studentId: number | undefined): Observable<StudentDTO> {
    return this.httpClient.get<StudentDTO>(`${this.apiUrl}/${studentId}`,this.authService.httpOptions);
  }

  assignCourse(studentCourseDTO: StudentCourseDTO): Observable<String> {

    return this.httpClient.post<String>(`${this.studentCourseUrl}`, studentCourseDTO,this.authService.httpOptions);
  }

  updateStudentCourseGrades(studentCourseDTO: StudentCourseDTO): Observable<String> {
    return this.httpClient.put<String>(`${this.studentCourseUrl}`, studentCourseDTO,this.authService.httpOptions);
  }

  searchStudent(name: string): Observable<StudentDTO[]> {
    return this.httpClient.get<StudentDTO[]>(`${this.apiUrl}/search/${name}`,this.authService.httpOptions)
  }


}
