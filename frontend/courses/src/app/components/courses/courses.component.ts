import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonListComponent } from '../common-list.component';
import { Course } from '../../models/course';
import { CourseService } from '../../services/course.service';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent extends CommonListComponent<Course, CourseService> implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(service: CourseService) {
    super(service);
    this.title = 'Listado de cursos';
    this.nameModel = 'Curso';
   }

}
