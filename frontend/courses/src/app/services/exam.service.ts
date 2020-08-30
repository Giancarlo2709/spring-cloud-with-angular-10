import { Injectable } from '@angular/core';
import { Exam } from '../models/exam';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subject } from '../models/subject';

@Injectable({
  providedIn: 'root'
})
export class ExamService extends CommonService<Exam> {

  protected baseEndpoint = BASE_ENDPOINT +  '/exams';

  constructor(http: HttpClient) {
    super(http);
  }

  public findAllSubject() : Observable<Subject[]>  {
    return this.http.get<Subject[]>(`${this.baseEndpoint}/subjects`);
  }

  public filterByName(name: string) : Observable<Exam[]> {
    return this.http.get<Exam[]>(`${this.baseEndpoint}/filters/${name}`);
  }


}
