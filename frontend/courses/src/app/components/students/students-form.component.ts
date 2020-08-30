import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonFormComponent } from '../common-form.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-students-form',
  templateUrl: './students-form.component.html',
  styleUrls: ['./students-form.component.css']
})
export class StudentsFormComponent
  extends CommonFormComponent<Student, StudentService>
  implements OnInit {

    private photoSelected : File;

  constructor(
    service: StudentService,
    router: Router,
    route: ActivatedRoute
  ) {
    super(service, router, route);
    this.title = 'Crear Alumnos';
    this.model = new Student();
    this.redirect = '/students';
    this.nameModel = 'Alumno';
  }

  public selectPhoto(event) : void {
    this.photoSelected = event.target.files[0];
    console.info(this.photoSelected);

    if (this.photoSelected.type.indexOf('image') < 0) {
      this.photoSelected = null;
      Swal.fire(
        'Error al seleccionar foto',
        'El archivo debe ser de tipo imagen',
        'error'
      );
    }
  }

  public create() : void {
    if (!this.photoSelected) {
      super.create();
    } else {
      this.createWithPhoto();
    }
  }

  public createWithPhoto() : void {
    this.service.createWithPhoto(this.model, this.photoSelected)
      .subscribe(student => {
        Swal.fire('Nuevo:', `${this.nameModel} ${student.name} creado con éxito`, 'success');
        this.router.navigate([this.redirect]);
      }, err => {
        if (err.status === 400) {
          this.error = err.error;
          console.log(this.error);
        }
      });
  }

  public update() : void {
    if (!this.photoSelected) {
      super.update();
    } else {
      this.updateWithPhoto();
    }
  }

  public updateWithPhoto() : void {
    this.service.updateWithPhoto(this.model, this.photoSelected)
      .subscribe(student => {
        Swal.fire('Editar:', `${this.nameModel} ${student.name} actualizado con éxito`, 'success');
        this.router.navigate([this.redirect]);
      }, err => {
        if (err.status === 400) {
          this.error = err.error;
          console.log(this.error);
        }
      });
  }

}
