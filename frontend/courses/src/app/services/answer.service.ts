import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { BASE_ENDPOINT } from '../config/app';
import { Answer } from '../models/answer';
import { Observable } from 'rxjs';
import { Student } from '../models/student';
import { Exam } from '../models/exam';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  private headers: HttpHeaders = new HttpHeaders({ 'Content-Type' : 'application/json' });

  private baseEnpoint =  BASE_ENDPOINT + '/answers';

  constructor(private http: HttpClient) { }

  saveAll(answers: Answer[]) : Observable<Answer[]> {
    return this.http.post<Answer[]>(this.baseEnpoint, answers, { headers: this.headers });
  }

  findAnswersByStudentIdAndExamId(student: Student, exam: Exam) : Observable<Answer[]> {
    return this.http.get<Answer[]>(`${this.baseEnpoint}/students/${student.id}/exams/${exam.id}`);
  }


}
