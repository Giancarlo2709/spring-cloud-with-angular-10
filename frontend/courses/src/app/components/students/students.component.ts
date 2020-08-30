import { Component, OnInit, ViewChild } from '@angular/core';
import { StudentService } from '../../services/student.service';
import { Student } from 'src/app/models/student';
import { CommonListComponent } from '../common-list.component';
import { MatPaginator } from '@angular/material/paginator';
import { BASE_ENDPOINT } from '../../config/app';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent 
  extends CommonListComponent<Student, StudentService> implements OnInit {  

  @ViewChild(MatPaginator) paginator: MatPaginator;

  baseEndpoint = BASE_ENDPOINT + "/users";

  constructor(service: StudentService) {
    super(service);
    this.title = 'Listado de Alumnos';
    this.nameModel = 'Alumno';
  }

}
