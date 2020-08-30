import { Injectable } from '@angular/core';
import { Course } from '../models/course';
import { Student } from '../models/student';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Exam } from '../models/exam';

@Injectable({
  providedIn: 'root'
})
export class CourseService extends CommonService<Course>  {

  protected baseEndpoint = BASE_ENDPOINT + '/courses';

  constructor(http: HttpClient) {
    super(http);
  }

  assignStudents(course: Course, students: Student[]): Observable<Course> {
    return this.http.put<Course>(
      `${this.baseEndpoint}/${course.id}/assign-students`,
      students,
      { headers: this.headers });
  }

  deleteStudent(course: Course, student: Student): Observable<Course> {
    return this.http.put<Course>(`${this.baseEndpoint}/${course.id}/delete-student`, student,
      { headers: this.headers });
  }

  assignExams(course: Course, exams: Exam[]): Observable<Course> {
    return this.http.put<Course>(`${this.baseEndpoint}/${course.id}/assign-exams`, exams,
      { headers: this.headers });
  }

  deleteExam(course: Course, exam: Exam): Observable<Course> {
    return this.http.put<Course>(`${this.baseEndpoint}/${course.id}/delete-exam`, exam,
      { headers: this.headers })
  }

  findCourseByStudentId(student: Student) : Observable<Course> {
    return this.http.get<Course>(`${this.baseEndpoint}/students/${student.id}`);
  } 

}
