import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
// import { map } from 'rxjs/operators';
import { Student } from '../models/student';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';

@Injectable({
  providedIn: 'root'
})
export class StudentService extends CommonService<Student> {

  protected baseEndpoint = BASE_ENDPOINT +  '/users';

  constructor(http: HttpClient) {
    super(http);
  }

  public createWithPhoto(student: Student, file: File) : Observable<Student> {
    const formData = this.buildFormData(student, file);
    return this.http.post<Student>(this.baseEndpoint + '/photos', formData);
  }

  public updateWithPhoto(student: Student, file: File) : Observable<Student> {
    const formData = this.buildFormData(student, file);
    return this.http.put<Student>(`${this.baseEndpoint}/photos/${student.id}`, formData);
  }

  private buildFormData(student: Student, file: File) : FormData {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('name', student.name);
    formData.append('lastName', student.lastName);
    formData.append('email', student.email);
    return formData;
  }

  public filterByName(name: string) : Observable<Student[]> {
    return this.http.get<Student[]>(`${this.baseEndpoint}/filters/${name}`);
  }

}
