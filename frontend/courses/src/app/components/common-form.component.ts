import { OnInit, Injectable } from '@angular/core';
import { Generic } from '../models/generic';
import { CommonService } from '../services/common.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Injectable()
export abstract class CommonFormComponent<E extends Generic, S extends CommonService<E>> implements OnInit {

  public title = 'Crear Alumnos';
  model: E;
  error: any;
  protected redirect: string;
  protected nameModel: string;

  constructor(
    protected service: S,
    protected router: Router,
    protected route: ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id: number = +params.get('id'); // +(converter string to number)
      if (id) {
        this.service.findById(id).subscribe(m => {
          this.model = m;
          this.title = 'Editar ' + this.nameModel;
        });
      }
    });
  }

  public create() : void {
    this.service.create(this.model)
      .subscribe(m => {
        console.log(m);
        Swal.fire('Nuevo:', `${this.nameModel} ${m.name} creado con éxito`, 'success');
        this.router.navigate([this.redirect]);
      }, err => {
        if (err.status === 400) {
          this.error = err.error;
          console.log(this.error);
        }
      });
  }

  public update() : void {
    this.service.update(this.model)
      .subscribe(m => {
        console.log(m);
        Swal.fire('Editar:', `${this.nameModel} ${m.name} actualizado con éxito`, 'success');
        this.router.navigate([this.redirect]);
      }, err => {
        if (err.status === 400) {
          this.error = err.error;
          console.log(this.error);
        }
      });
  }

}
