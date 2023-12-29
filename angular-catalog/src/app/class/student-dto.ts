import {CourseDTO} from "./course-dto";

export class StudentDTO {
  studentId: number | undefined;
  firstName: string | undefined;
  lastName: string | undefined;
  date: string| undefined;
  studentGender:string| undefined;
  studentEmail:string| undefined;
  courses:CourseDTO[]| undefined;


}
