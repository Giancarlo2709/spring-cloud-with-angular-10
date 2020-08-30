import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';
import { CommonFormComponent } from '../common-form.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-courses-form',
  templateUrl: './courses-form.component.html',
  styleUrls: ['./courses-form.component.css']
})
export class CoursesFormComponent 
extends CommonFormComponent<Course, CourseService>
  implements OnInit {

  constructor(
    service: CourseService,
    router: Router,
    route: ActivatedRoute
  ) {
    super(service, router, route);
    this.title = 'Crear Curso';
    this.model = new Course();
    this.redirect = '/courses';
    this.nameModel = 'Curso';
   }

}
