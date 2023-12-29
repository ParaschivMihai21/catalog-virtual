export class StudentCourseDTO {
    studentId: number | undefined;
    courseId: number | undefined;
    grades: number | undefined;


    constructor(studentId: number | undefined, courseId: number | undefined, grades: number | undefined) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grades = grades;
    }
}
